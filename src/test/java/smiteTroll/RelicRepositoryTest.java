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

    @Test
    public void testingOnlyTwoRelicsAreReturned() throws SQLException, IOException, ClassNotFoundException {
        RelicRepository relicRepo = new RelicRepository();
        List<Relic> relicList = relicRepo.getRelic();
        Assert.assertEquals(2, relicList.size());
        Assert.assertTrue(RELIC_NAMES.contains(relicList.get(0).getRelicName()));
        Assert.assertTrue(RELIC_NAMES.contains(relicList.get(1).getRelicName()));
    }
}