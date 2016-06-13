package smiteTroll.repositories;

import smiteTroll.exceptions.ModifyDatabaseException;
import java.sql.*;

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
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");        }
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
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");        }
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
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");        }
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
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");        }
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
            throw new ModifyDatabaseException("Data not modified. Invalid data entered.");        }
    }

    private void close(PreparedStatement prepStmt) throws SQLException {
        if (prepStmt != null) {
            prepStmt.close();
        }
    }

    private void close(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}