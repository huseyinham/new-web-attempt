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
            String godName = rs.getString("god_name");
            String godType = rs.getString("god_type");
            return new God(godName, godType);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
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
            String godName = rs.getString("god_name");
            String godType = rs.getString("god_type");
            return new God(godName, godType);

        } finally {
            if (prepStmt != null) {
                prepStmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
