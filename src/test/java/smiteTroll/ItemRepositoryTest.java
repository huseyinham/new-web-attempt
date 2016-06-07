package smiteTroll;

import org.junit.Assert;
import org.junit.Test;
import smiteTroll.Classes.God;
import smiteTroll.Classes.Item;
import smiteTroll.Repositories.ItemRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemRepositoryTest {

    private ItemRepository itemRepo = new ItemRepository();

    @Test
    public void testingValidItemsForPhysicalGod()  {
        assertValidItemsForGod("physical");
    }

    @Test
    public void testingValidItemsForMagicalGod() {
        assertValidItemsForGod("magical");
    }

    @Test
    public void testingRerollWithBoots() {
        God god = new God("Anubis", "magical");
        List<Item> itemList = itemRepo.getItems("magical");
        Item reRolledItem = itemList.get(0);
        Item newItem = itemRepo.reRoll(god, reRolledItem, itemList);
        Assert.assertTrue(!newItem.getItemName().equals(reRolledItem.getItemName()));
        Assert.assertTrue(newItem.getItemType().equals(reRolledItem.getItemType()));
    }

    @Test
    public void testingRerollWithNormalItem() {
        God god = new God("Anubis", "magical");
        List<Item> itemList = itemRepo.getItems("magical");
        Item reRolledItem = itemList.get(1);
        Item newItem = itemRepo.reRoll(god, reRolledItem, itemList);
        Assert.assertTrue(!newItem.getItemName().equals(reRolledItem.getItemName()));
        Assert.assertTrue(newItem.getItemType().equals(god.getGodType()) || newItem.getItemType().equals("neutral"));
        for(Item item : itemList){
            Assert.assertTrue(!newItem.getItemName().equals(item.getItemName()));
        }
    }

    private void assertValidItemsForGod(String type)  {
        List<Item> itemList = itemRepo.getItems(type);
        for(Item item : itemList){
            String actualResult = item.getItemType();
            Assert.assertTrue(actualResult.equals(type) || actualResult.equals(type + "_boots") || actualResult.equals("neutral"));
        }
    }
}