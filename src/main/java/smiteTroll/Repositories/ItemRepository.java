package smiteTroll.repositories;

import smiteTroll.classes.God;
import smiteTroll.classes.Item;
import smiteTroll.exceptions.AccessingDatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    public List<Item> getItems(String godType) {
        List<Item> itemList = new ArrayList();
        itemList.add(getBootsItem(godType));
        itemList.addAll(getRestOfItems(godType));
        return itemList;
    }

    private Item getBootsItem(String type) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("SELECT * FROM item WHERE item_type = ? ORDER BY RAND() LIMIT 1");
                prepStmt.setString(1, type + "_boots");
                ResultSet rs = prepStmt.executeQuery();
                rs.next();
                return asItem(rs);
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new AccessingDatabaseException("Cannot select boots item from the database.");
        }
    }

    private List<Item> getRestOfItems(String type) {
        try {
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
                    items.add(asItem(rs));
                }
                return items;
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new AccessingDatabaseException("Cannot select items from the database.");
        }
    }

    public Item reRoll(God god, Item rerolledItem, List<Item> alreadySelectedItems) {
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                if (rerolledItem.getItemType().contains("boots")) {
                    prepStmt = con.prepareStatement("SELECT * FROM item WHERE item_type = ? AND item_name NOT IN (?,?,?,?,?,?) ORDER BY RAND() LIMIT 1");
                    prepStmt.setString(1, god.getGodType() + "_boots");
                } else {
                    prepStmt = con.prepareStatement("SELECT * FROM item WHERE (item_type = ? OR item_type = 'neutral') AND item_name NOT IN (?,?,?,?,?,?) ORDER BY RAND() LIMIT 1");
                    prepStmt.setString(1, god.getGodType());
                }

                int i = 2;
                for (Item item : alreadySelectedItems) {
                    prepStmt.setString(i, item.getItemName());
                    i++;
                }
                ResultSet rs = prepStmt.executeQuery();
                rs.next();
                return asItem(rs);
            } finally {
                close(prepStmt);
                close(con);
            }
        } catch (SQLException e) {
            throw new AccessingDatabaseException("Cannot retrieve specific item from the database.");
        }
    }

    private Item asItem(ResultSet rs) throws SQLException {
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