package smiteTroll.Repositories;

import smiteTroll.Classes.God;

import java.sql.*;

public class GodRepository {

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    public God getNewGod() {
        try {
            Connection con = null;
            Statement stmt = null;
            try {
                con = connectionCreator.getConnection();
                stmt = con.createStatement();
                String query = "SELECT * FROM god ORDER BY RAND() LIMIT 1";
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                return asGod(rs);

            } finally {
                close(stmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public God reRoll(God previousGod) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("SELECT * FROM god WHERE god_name != ? AND god_type = ? ORDER BY RAND() LIMIT 1");
                prepStmt.setString(1, previousGod.getGodName());
                prepStmt.setString(2, previousGod.getGodType());
                ResultSet rs = prepStmt.executeQuery();
                rs.next();
                return asGod(rs);

            } finally {
                close(prepStmt);
                close(con);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    private God asGod(ResultSet rs) throws SQLException {
        String godName = rs.getString("god_name");
        String godType = rs.getString("god_type");
        return new God(godName, godType);
    }

    private void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
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