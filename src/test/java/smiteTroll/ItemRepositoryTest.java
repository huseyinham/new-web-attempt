package smiteTroll;

import org.junit.Assert;
import org.junit.Test;
import smiteTroll.Classes.Item;
import smiteTroll.Repositories.ItemRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemRepositoryTest {

    private ItemRepository itemRepo = new ItemRepository();

    @Test
    public void testingValidItemsForPhysicalGod() throws SQLException, IOException, ClassNotFoundException {
        assertValidItemsForGod("physical");
    }

    @Test
    public void testingValidItemsForMagicalGod() throws SQLException, IOException, ClassNotFoundException {
        assertValidItemsForGod("magical");
    }

    @Test
    public void testingRerollWithBoots() throws SQLException, IOException, ClassNotFoundException {
        List<Item> itemList = itemRepo.getItems("magical");
        Item reRolledItem = itemList.get(0);
        Item newItem = itemRepo.reRoll(reRolledItem, itemList);
        Assert.assertTrue(!newItem.getItemName().contains(reRolledItem.getItemName()));
        Assert.assertTrue(newItem.getItemType().equals(reRolledItem.getItemType()));
    }

    @Test
    public void testingRerollWithNormalItem() throws SQLException, IOException, ClassNotFoundException {
        List<Item> itemList = itemRepo.getItems("magical");
        Item reRolledItem = itemList.get(1);
        Item newItem = itemRepo.reRoll(reRolledItem, itemList);
        Assert.assertTrue(!newItem.getItemName().contains(reRolledItem.getItemName()));
        Assert.assertTrue(newItem.getItemType().equals(reRolledItem.getItemType()));
        for(Item item : itemList){
            Assert.assertTrue(!newItem.getItemName().contains(item.getItemName()));
        }
    }

    private void assertValidItemsForGod(String type) throws SQLException, IOException, ClassNotFoundException {
        List<Item> itemList = itemRepo.getItems(type);
        for(Item item : itemList){
            String actualResult = item.getItemType();
            Assert.assertTrue(actualResult.equals(type) || actualResult.equals(type + "_boots") || actualResult.equals("neutral"));
        }
    }

}
