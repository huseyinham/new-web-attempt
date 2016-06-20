package smiteTroll.specials;

import org.junit.Assert;
import org.junit.Test;
import smiteTroll.classes.God;
import smiteTroll.classes.Item;

import java.util.ArrayList;
import java.util.List;

public class SpecificItemsForGodsTest {

    @Test
    public void testsSpecificItemsForSpecificGods(){
        God god = new God("Ratatoskr", "physical");
        List<Item> listOfItems = new ArrayList<>();
        listOfItems.add(0, new Item("Item A", "physical"));
        listOfItems.add(1, new Item("Item B", "physical"));
        listOfItems.add(2, new Item("Item C", "physical"));
        listOfItems.add(3, new Item("Item D", "physical"));
        listOfItems.add(4, new Item("Item E", "physical"));
        listOfItems.add(5, new Item("Item F", "physical"));
        SpecificItemsForGods itemCheck = new SpecificItemsForGods(god, listOfItems);
        itemCheck.checkUniqueCircumstance();
        Assert.assertEquals("Acorn of Yggdrasil", itemCheck.getItems().get(0).getItemName());
    }
}
