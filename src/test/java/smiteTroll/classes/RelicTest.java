package smiteTroll.classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RelicTest {

    private Relic relic;

    @Before
    public void setup() {
        relic = new Relic("name", "resources/imageHere");
    }

    @Test
    public void testGetRelicName() {
        Assert.assertEquals("name", relic.getRelicName());
    }

    @Test
    public void testGetRelicImage() {
        Assert.assertEquals("resources/imageHere", relic.getRelicImage());
    }
}
