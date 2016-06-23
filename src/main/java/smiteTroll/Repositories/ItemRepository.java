package smiteTroll.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import smiteTroll.classes.God;
import smiteTroll.classes.Item;
import smiteTroll.exceptions.AccessingDatabaseException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepository {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Item> getItems(String godType) {
        List<Item> itemList = new ArrayList();
        itemList.add(getBootsItem(godType));
        itemList.addAll(getRestOfItems(godType));
        return itemList;
    }

    public Item reRoll(God god, Item rerolledItem, List<Item> alreadySelectedItems) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        if (rerolledItem.getItemType().contains("boots")) {
            return jdbcTemplate.query("SELECT * FROM item WHERE item_type = ? AND item_name NOT IN (?,?,?,?,?,?) ORDER BY RAND() LIMIT 1",
                    new Object[]{god.getGodType() + "_boots",
                            alreadySelectedItems.get(0),
                            alreadySelectedItems.get(1),
                            alreadySelectedItems.get(2),
                            alreadySelectedItems.get(3),
                            alreadySelectedItems.get(4),
                            alreadySelectedItems.get(5)},
                    new ItemExtractor());
        } else {
            return jdbcTemplate.query("SELECT * FROM item WHERE (item_type = ? OR item_type = 'neutral') AND item_name NOT IN (?,?,?,?,?,?) ORDER BY RAND() LIMIT 1",
                    new Object[]{god.getGodType(),
                            alreadySelectedItems.get(0),
                            alreadySelectedItems.get(1),
                            alreadySelectedItems.get(2),
                            alreadySelectedItems.get(3),
                            alreadySelectedItems.get(4),
                            alreadySelectedItems.get(5)},
                    new ItemExtractor());
        }
    }

    private Item getBootsItem(String type) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM item WHERE item_type = ? ORDER BY RAND() LIMIT 1", new Object[]{type + "_boots"}, new ItemExtractor());
    }

    private List<Item> getRestOfItems(String type) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM item WHERE item_type = ? OR item_type = ? ORDER BY RAND() LIMIT 5", new Object[]{type, "neutral"}, new ItemListRowMapExtractor());
    }

    private static class ItemListRowMapExtractor implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet resultSet, int i) throws SQLException {
            return asItem(resultSet);
        }
    }

    private static class ItemExtractor implements ResultSetExtractor<Item> {
        @Override
        public Item extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            resultSet.next();
            return asItem(resultSet);
        }
    }

    private static Item asItem(ResultSet rs) throws SQLException {
        String itemName = rs.getString("item_name");
        String itemType = rs.getString("item_type");
        return new Item(itemName, itemType);
    }
}