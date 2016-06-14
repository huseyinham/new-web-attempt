package smiteTroll;

import org.junit.Test;
import smiteTroll.repositories.DataRepository;

public class DataRepositoryTest {

    private DataRepository dataRepo = new DataRepository();
    private God previousGod = new God("Thanatos", "physical");

    @Test
    public void testGodCanBeAddedToDB()  {
        God selectedGod = godRepo.reRoll(previousGod);
        Assert.assertTrue(!selectedGod.getGodName().equals(previousGod.getGodName()));
    }

    @Test
    public void testSelectedGodIsInTheList()  {
        God selectedGod = godRepo.reRoll(previousGod);
        List<God> listOfGods = getPotentialRerollableGods();
        Boolean found = false;
        for (God god : listOfGods) {
            if (selectedGod.getGodName().equals(god.getGodName())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }

}
