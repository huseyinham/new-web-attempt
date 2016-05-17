package smiteTroll.Repositories;

import smiteTroll.Classes.Relic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RelicRepository {

    public List<Relic> getRelic() throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        Statement stmt = null;

        try {
            ConnectionCreator connectionCreator = new ConnectionCreator();
            con = connectionCreator.getConnection();

            List<Relic> relicList = new ArrayList<>();
            stmt = con.createStatement();
            String query = "SELECT * FROM relic ORDER BY RAND() LIMIT 2";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String relicName = rs.getString("relic_name");
                relicList.add(new Relic(relicName));
            }

            return relicList;

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
