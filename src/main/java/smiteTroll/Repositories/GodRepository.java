package smiteTroll.repositories;

import smiteTroll.classes.God;
import smiteTroll.exceptions.AccessingDatabaseException;

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
            throw new AccessingDatabaseException("Cannot select god from database.");
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
            throw new AccessingDatabaseException("Cannot select god from database.");
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