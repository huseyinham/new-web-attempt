package smiteTroll.Repositories;

import smiteTroll.Classes.Item;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    public List<Item> getItems(String godType) throws SQLException, ClassNotFoundException, IOException {
        List<Item> itemList = new ArrayList();
        itemList.add(getBootsItem(godType));
        itemList.addAll(getRestOfItems(godType));
        return itemList;
    }

    private Item getBootsItem(String type) throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = connectionCreator.getConnection();
            prepStmt = con.prepareStatement("SELECT * FROM item WHERE item_type = ? ORDER BY RAND() LIMIT 1");
            prepStmt.setString(1, type + "_boots");
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            return toItem(rs);
        } finally {
            close(prepStmt);
            close(con);
        }
    }

    private List<Item> getRestOfItems(String type) throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            List<Item> items = new ArrayList<>();
            con = connectionCreator.getConnection();
            prepStmt = con.prepareStatement("SELECT * FROM item WHERE item_type = ? OR item_type = ? ORDER BY RAND() LIMIT 5");
            prepStmt.setString(1, type);
            prepStmt.setString(2, "neutral");
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                items.add(toItem(rs));
            }
            return items;
        } finally {
            close(prepStmt);
            close(con);
        }
    }

    private Item toItem(ResultSet rs) throws SQLException {
        String itemName = rs.getString("item_name");
        String itemType = rs.getString("item_type");
        return new Item(itemName, itemType);
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