package smiteTroll.Repositories;

import smiteTroll.Classes.Relic;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelicRepository {

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    public List<Relic> getRelics() throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        Statement stmt = null;

        try {
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
            close(stmt);
            close(con);
        }
    }

    public Relic reRollRelic(Relic rerolledRelic, Relic otherRelic) throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        PreparedStatement prepStmt = null;

        try {
            con = connectionCreator.getConnection();

            prepStmt = con.prepareStatement("SELECT * FROM relic WHERE relic_name != ? AND relic_name != ? ORDER BY RAND() LIMIT 1");
            prepStmt.setString(1, rerolledRelic.getRelicName());
            prepStmt.setString(2, otherRelic.getRelicName());

            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            String relicName = rs.getString("relic_name");
            return new Relic(relicName);

        } finally {
            close(prepStmt);
            close(con);
        }
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
