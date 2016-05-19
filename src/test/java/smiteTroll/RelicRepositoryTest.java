package smiteTroll;

import org.junit.Assert;
import org.junit.Test;
import smiteTroll.Classes.Relic;
import smiteTroll.Repositories.RelicRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class RelicRepositoryTest {

    private static final List<String> RELIC_NAMES = Arrays.asList("Blink", "Curse", "Frenzy", "Meditation", "Purification", "Sanctuary", "Scout", "Shell", "Sprint", "Sunder", "Teleport", "Wrath");
    private RelicRepository relicRepo = new RelicRepository();

    @Test
    public void testingOnlyTwoRelicsAreReturned() throws SQLException, IOException, ClassNotFoundException {
        List<Relic> relicList = relicRepo.getRelics();
        Assert.assertEquals(2, relicList.size());
        Assert.assertTrue(RELIC_NAMES.contains(relicList.get(0).getRelicName()));
        Assert.assertTrue(RELIC_NAMES.contains(relicList.get(1).getRelicName()));
    }

    @Test
    public void testingRelicReroll() throws SQLException, IOException, ClassNotFoundException {
        Relic rerolledRelic = new Relic("Blink");
        Relic newRelic = relicRepo.reRollRelic(rerolledRelic);
        Assert.assertTrue(!newRelic.getRelicName().equals(rerolledRelic.getRelicName()));
    }

}