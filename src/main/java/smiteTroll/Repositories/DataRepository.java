package smiteTroll.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class DataRepository {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void addNewGodToDB(String name, String type, String image) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO god (god_name, god_type, god_image) VALUES (?,?,?)", name, type, image);
    }

    public void deleteGodFromDB(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM god WHERE god_name = ?", name);
    }

    public void addNewItemToDB(String name, String type, String image) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO item (item_name, item_type, item_image) VALUES (?,?,?)", name, type, image);
    }

    public void deleteItemFromDB(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM item WHERE item_name = ?", name);
    }

    public void addNewRelicToDB(String name, String image) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO relic (relic_name, relic_image) VALUES (?,?)", name, image);
    }

    public void deleteRelicFromDB(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM relic WHERE relic_name = ?", name);
    }

    public List<String> getGodTypes() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<String> godTypeList = jdbcTemplate.query("SELECT * FROM god_types", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("god_type_name");
            }
        });
        return godTypeList;
    }

    public List<String> getItemTypes() {
        JdbcTemplate jdbctemplate = new JdbcTemplate(dataSource);
        List<String> itemTypeList = jdbctemplate.query("SELECT * FROM item_types", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("item_type_name");
            }
        });
        return itemTypeList;
    }
}