package smiteTroll.Repositories;

import smiteTroll.Classes.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ItemRepository {

    public Item getItems(String godType) throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        PreparedStatement prepStmt = null;

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
            String query = "SELECT * FROM item ORDER BY RAND() LIMIT 6 WHERE item_type = ?";
            prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, godType);
            ResultSet rs = prepStmt.executeQuery(query);

            rs.next();
            String itemName = rs.getString("item_name");
            String itemType = rs.getString("item_type");
            return new Item(itemName, itemType);

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
