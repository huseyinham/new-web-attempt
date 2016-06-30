package smiteTroll.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import smiteTroll.classes.God;
import smiteTroll.classes.Item;
import smiteTroll.specials.SpecificItemsForGods;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Item> getItems(God god) {
        List<Item> itemList = new ArrayList();
        itemList.add(getBootsItem(god.getGodType()));
        itemList.addAll(getRestOfItems(god.getGodType()));
        new SpecificItemsForGods(god, itemList).checkUniqueCircumstance();
        return itemList;
    }

    public Item reRoll(God god, Item rerolledItem, List<Item> alreadySelectedItems) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        if (rerolledItem.getItemType().contains("boots")) {
            return jdbcTemplate.query("SELECT * FROM item WHERE item_type = ? AND item_name NOT IN (?,?,?,?,?,?) ORDER BY RAND() LIMIT 1",
                    new Object[]{god.getGodType() + "_boots",
                            alreadySelectedItems.get(0).getItemName(),
                            alreadySelectedItems.get(1).getItemName(),
                            alreadySelectedItems.get(2).getItemName(),
                            alreadySelectedItems.get(3).getItemName(),
                            alreadySelectedItems.get(4).getItemName(),
                            alreadySelectedItems.get(5).getItemName()},
                    new ItemExtractor());
        } else {
            return jdbcTemplate.query("SELECT * FROM item WHERE (item_type = ? OR item_type = 'neutral') AND item_name NOT IN (?,?,?,?,?,?) ORDER BY RAND() LIMIT 1",
                    new Object[]{god.getGodType(),
                            alreadySelectedItems.get(0).getItemName(),
                            alreadySelectedItems.get(1).getItemName(),
                            alreadySelectedItems.get(2).getItemName(),
                            alreadySelectedItems.get(3).getItemName(),
                            alreadySelectedItems.get(4).getItemName(),
                            alreadySelectedItems.get(5).getItemName()},
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

    public List<Item> checkForSpecificItemsForGods(God god, List<Item> items) {
        Item firstItem = items.get(0);
        if (firstItem.getItemName().equals("Acorn of Yggdrasil")) {
            Item newFirstItem = reRoll(god, firstItem, items);
            items.set(0, newFirstItem);
        }
        return items;
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
        String itemImage = rs.getString("item_image");
        return new Item(itemName, itemType, itemImage);
    }
}