package smiteTroll.Repositories;

import smiteTroll.Classes.God;

import java.io.IOException;
import java.sql.*;

public class GodRepository {

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    public God getNewGod() throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        Statement stmt = null;

        try {
            con = connectionCreator.getConnection();
            stmt = con.createStatement();
            String query = "SELECT * FROM god ORDER BY RAND() LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return toGod(rs);

        } finally {
            close(stmt);
            close(con);
        }
    }

    public God reRoll(God previousGod) throws SQLException, IOException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement prepStmt = null;

        try {
            con = connectionCreator.getConnection();
            prepStmt = con.prepareStatement("SELECT * FROM god WHERE god_name != ? ORDER BY RAND() LIMIT 1");
            prepStmt.setString(1, previousGod.getGodName());
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            return toGod(rs);

        } finally {
            close(prepStmt);
            close(con);
        }
    }

    private God toGod(ResultSet rs) throws SQLException {
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