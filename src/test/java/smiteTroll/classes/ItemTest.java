package smiteTroll.classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

    private Item item;

    @Before
    public void setup() {
        item = new Item("name", "type", "resources/imageHere");
    }

    @Test
    public void testGetItemName() {
        Assert.assertEquals("name", item.getItemName());
    }

    @Test
    public void testGetItemType() {
        Assert.assertEquals("type", item.getItemType());
    }

    @Test
    public void testGetItemImage() {
        Assert.assertEquals("resources/imageHere", item.getItemImage());
    }
}
