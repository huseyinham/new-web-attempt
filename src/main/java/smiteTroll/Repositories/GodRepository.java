package smiteTroll.Repositories;

import smiteTroll.Classes.God;

import java.io.IOException;
import java.sql.*;

public class GodRepository {

    public God getNewGod() throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        Statement stmt = null;

        try {
            ConnectionCreator connectionCreator = new ConnectionCreator();
            con = connectionCreator.getConnection();
            stmt = con.createStatement();
            PreparedStatement prepStmt = null;
            if(1 == 1){ //clicked reroll button - then enter this SQL query else perform original
                prepStmt = con.prepareStatement("SELECT * FROM god WHERE god_name != ? ORDER BY RAND() LIMIT 1");
                prepStmt.setString(1, godName);
            } else {
                prepStmt = con.prepareStatement("SELECT * FROM god ORDER BY RAND() LIMIT 1");
            }
            ResultSet rs = prepStmt.executeQuery();
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

    public God reRoll(){


        return God;
    }
}
