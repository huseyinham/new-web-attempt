package smiteTroll.Repositories;

import smiteTroll.Classes.God;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class GodRepository {

    public God getNewGod() throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        Statement stmt = null;

        try {
            Properties prop = new Properties();
            InputStream in = getClass().getResourceAsStream("/jdbc.properties");
            prop.load(in);
            in.close();
            String JDBC_DRIVER = prop.getProperty("jdbc.driver");
            String JDBC_URL = prop.getProperty("jdbc.url");
            String JDBC_USERNAME = prop.getProperty("jdbc.username");
            String JDBC_PASSWORD = prop.getProperty("jdbc.password");

            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
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
}
