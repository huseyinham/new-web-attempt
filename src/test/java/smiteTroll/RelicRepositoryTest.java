package smiteTroll;

import org.junit.Assert;
import org.junit.Test;
import smiteTroll.classes.Relic;
import smiteTroll.repositories.RelicRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RelicRepositoryTest {

    private static final List<String> RELIC_NAMES = Arrays.asList("Blink", "Curse", "Frenzy", "Meditation", "Purification", "Sanctuary", "Scout", "Shell", "Sprint", "Sunder", "Teleport", "Wrath");
    private RelicRepository relicRepo = new RelicRepository();

    @Test
    public void testingOnlyTwoRelicsAreReturned()  {
        List<Relic> relicList = relicRepo.getRelics();
        Assert.assertEquals(2, relicList.size());
        Assert.assertTrue(RELIC_NAMES.contains(relicList.get(0).getRelicName()));
        Assert.assertTrue(RELIC_NAMES.contains(relicList.get(1).getRelicName()));
    }

    @Test
    public void testingRelicReroll() {
        List<Relic> relics = new ArrayList<>();
        relics.add(new Relic("Blink"));
        relics.add(new Relic("Teleport"));
        Relic newRelic = relicRepo.reRollRelic(relics);
        for(Relic relic : relics){
            Assert.assertTrue(!newRelic.getRelicName().equals(relic.getRelicName()));
        }
    }
}