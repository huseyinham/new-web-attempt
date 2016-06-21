package smiteTroll.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import smiteTroll.classes.God;
import smiteTroll.exceptions.AccessingDatabaseException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    @Autowired
    private DataSource dataSource;

    public void addNewGodToDB(String name, String type) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO god (god_name, god_type) VALUES (?,?)", name, type);
    }

    public void deleteGodFromDB(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM god WHERE god_name = ?", name);
    }

    public void addNewItemToDB(String name, String type) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES (?,?)", name, type);
    }

    public void deleteItemFromDB(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM item WHERE item_name = ?", name);
    }

    public void addNewRelicToDB(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO relic (relic_name) VALUES (?)", name);
    }

    public void deleteRelicFromDB(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM relic WHERE relic_name = ?", name);
    }

    public List<String> getGodTypes() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<String> godsList = jdbcTemplate.query("SELECT * FROM god_types", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return null;
            }
        });
        return godsList;
    }

    public List<String> getItemTypes() {
        try {
            Connection con = null;
            Statement stmt = null;
            try {
                con = connectionCreator.getConnection();
                List<String> itemTypes = new ArrayList<>();
                stmt = con.createStatement();
                String query = "SELECT * FROM item_types";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String itemTypeName = rs.getString("item_type_name");
                    itemTypes.add(itemTypeName);
                }
                return itemTypes;

            } finally {
                close(stmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new AccessingDatabaseException("Cannot read item types from database.");
        }
    }

    private void close(PreparedStatement prepStmt) throws SQLException {
        if (prepStmt != null) {
            prepStmt.close();
        }
    }

    private void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    private void close(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}