package smiteTroll.repositories;

import smiteTroll.classes.God;
import smiteTroll.exceptions.AccessingDatabaseException;
import smiteTroll.exceptions.ModifyDatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    public void addNewGodToDB(String name, String type) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("INSERT INTO god (god_name, god_type) VALUES (?,?)");
                prepStmt.setString(1, name);
                prepStmt.setString(2, type);
                prepStmt.executeUpdate();
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");
        }
    }

    public void deleteGodFromDB(String name) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("DELETE FROM god WHERE god_name = ?");
                prepStmt.setString(1, name);
                prepStmt.executeUpdate();
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");
        }
    }

    public void addNewItemToDB(String name, String type) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("INSERT INTO item (item_name, item_type) VALUES (?,?)");
                prepStmt.setString(1, name);
                prepStmt.setString(2, type);
                prepStmt.executeUpdate();
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");
        }
    }

    public void deleteItemFromDB(String name) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("DELETE FROM item WHERE item_name = ?");
                prepStmt.setString(1, name);
                prepStmt.executeUpdate();
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");
        }
    }

    public void addNewRelicToDB(String name) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("INSERT INTO relic (relic_name) VALUES (?)");
                prepStmt.setString(1, name);
                prepStmt.executeUpdate();
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");
        }
    }

    public void deleteRelicFromDB(String name) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("DELETE FROM relic WHERE relic_name = ?");
                prepStmt.setString(1, name);
                prepStmt.executeUpdate();
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");
        }
    }

    public List<String> getGodTypes() {
        try {
            Connection con = null;
            Statement stmt = null;
            try {
                con = connectionCreator.getConnection();
                List<String> godTypes = new ArrayList<>();
                stmt = con.createStatement();
                String query = "SELECT * FROM god_types";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String godTypeName = rs.getString("god_type_name");
                    godTypes.add(godTypeName);
                }
                return godTypes;

            } finally {
                close(stmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new AccessingDatabaseException("Cannot read god types from database.");
        }
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