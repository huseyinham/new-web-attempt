package smiteTroll.Repositories;

import smiteTroll.Classes.Relic;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelicRepository {

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    public List<Relic> getRelics() {
        try {
            Connection con = null;
            Statement stmt = null;

            try {
                con = connectionCreator.getConnection();

                List<Relic> relicList = new ArrayList<>();
                stmt = con.createStatement();
                String query = "SELECT * FROM relic ORDER BY RAND() LIMIT 2";

                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String relicName = rs.getString("relic_name");
                    relicList.add(new Relic(relicName));
                }
                return relicList;

            } finally {
                close(stmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Relic reRollRelic(List<Relic> relics) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;

            try {
                con = connectionCreator.getConnection();

                prepStmt = con.prepareStatement("SELECT * FROM relic WHERE relic_name NOT IN (?,?) ORDER BY RAND() LIMIT 1");
                prepStmt.setString(1, relics.get(0).getRelicName());
                prepStmt.setString(2, relics.get(1).getRelicName());

                ResultSet rs = prepStmt.executeQuery();
                rs.next();
                String relicName = rs.getString("relic_name");
                return new Relic(relicName);

            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
