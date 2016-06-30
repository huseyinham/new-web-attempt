package smiteTroll.classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GodTest {

    private God god;

    @Before
    public void setup() {
        god = new God("name", "type", "resources/imageHere");
    }

    @Test
    public void testGetGodName() {
        Assert.assertEquals("name", god.getGodName());
    }

    @Test
    public void testGetGodType() {
        Assert.assertEquals("type", god.getGodType());
    }

    @Test
    public void testGetGodImage() {
        Assert.assertEquals("resources/imageHere", god.getGodImage());
    }

}
