package smiteTroll.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import smiteTroll.classes.Relic;

import java.util.List;

public class RelicRepositoryTest {

    private RelicRepository relicRepo = new RelicRepository();
    private EmbeddedDatabase db;

    @Before
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("/db/migration/V1__initial_schema.sql", "/db/migration/V2__adding_static_data.sql")
                .build();
        relicRepo.setDataSource(db);
    }

    @Test
    public void testingOnlyTwoRelicsAreReturnedAndTheyAreDifferentRelics() {
        createDbRelicsForTesting();
        List<Relic> relics = relicRepo.getRelics();
        Assert.assertEquals(2, relics.size());
        Assert.assertTrue(!relics.get(0).getRelicName().equals(relics.get(1).getRelicName()));
        shutdown();
    }

    @Test
    public void testingRelicReroll() {
        createDbRelicsForTesting();
        List<Relic> relics = relicRepo.getRelics();
        Relic newRelic = relicRepo.reRollRelic(relics);
        for (Relic relic : relics) {
            Assert.assertTrue(!newRelic.getRelicName().equals(relic.getRelicName()));
        }
        shutdown();
    }

    private void createDbRelicsForTesting() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        jdbcTemplate.update("INSERT INTO relic (relic_name) VALUES ('relicOne')");
        jdbcTemplate.update("INSERT INTO relic (relic_name) VALUES ('relicTwo')");
        jdbcTemplate.update("INSERT INTO relic (relic_name) VALUES ('relicThree')");
        jdbcTemplate.update("INSERT INTO relic (relic_name) VALUES ('relicFour')");
    }

    private void shutdown() {
        db.shutdown();
    }
}