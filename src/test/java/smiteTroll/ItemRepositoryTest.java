package smiteTroll;

import org.junit.Assert;
import org.junit.Before;
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

    private void assertValidItemsForGod(String type) throws SQLException, IOException, ClassNotFoundException {
        List<Item> itemList = itemRepo.getItems(type);
        for(Item item : itemList){
            String actualResult = item.getItemType();
            Assert.assertTrue(actualResult.equals(type) || actualResult.equals(type + "_boots") || actualResult.equals("neutral"));
        }
    }

}
