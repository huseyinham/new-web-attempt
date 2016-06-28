package smiteTroll.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import smiteTroll.classes.God;
import smiteTroll.classes.Item;
import smiteTroll.classes.Relic;

public class DataRepositoryTest {

    private DataRepository dataRepo = new DataRepository();
    private EmbeddedDatabase db;

    @Before
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("/db/migration/V1__initial_schema.sql", "/db/migration/V2__adding_static_data.sql")
                .build();
        dataRepo.setDataSource(db);
    }

    @Test
    public void testValidGodCanBeAddedToDB() {
        God god = new God("God", "magical", "");
        dataRepo.addNewGodToDB(god.getGodName(), god.getGodType(), god.getGodImage());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM god WHERE god_name = ?", new Object[]{"God"}, Integer.class);
        Assert.assertEquals(1, count);
        shutdown();
    }

    @Test
    public void testInvalidGodTypeCannotBeAddedToDB() {
        God god = new God("God", "invalid", "");
        boolean exceptionThrown = false;
        try {
            dataRepo.addNewGodToDB(god.getGodName(), god.getGodType(), god.getGodImage());
        } catch (DataIntegrityViolationException e) {
            exceptionThrown = true;
        } finally {
            Assert.assertTrue(exceptionThrown);
            shutdown();
        }
    }

    @Test
    public void testInvalidGodTypeLengthCannotBeAddedToDB() {
        God god = new God("God", "invalidType", "");
        boolean exceptionThrown = false;
        try {
            dataRepo.addNewGodToDB(god.getGodName(), god.getGodType(), god.getGodImage());
        } catch (Exception e) {
            exceptionThrown = true;
        } finally {
            Assert.assertTrue(exceptionThrown);
            shutdown();
        }
    }

    @Test
    public void testValidItemCanBeAddedToDB() {
        Item item = new Item("Item", "magical", "");
        dataRepo.addNewItemToDB(item.getItemName(), item.getItemType(), item.getItemImage());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM item WHERE item_name = ?", new Object[]{"Item"}, Integer.class);
        Assert.assertEquals(1, count);
        shutdown();
    }

    @Test
    public void testInvalidItemTypeCannotBeAddedToDB() {
        Item item = new Item("Item", "invalid", "");
        boolean exceptionThrown = false;
        try {
            dataRepo.addNewItemToDB(item.getItemName(), item.getItemType(), item.getItemImage());
        } catch (DataIntegrityViolationException e) {
            exceptionThrown = true;
        } finally {
            Assert.assertTrue(exceptionThrown);
            shutdown();
        }
    }

    @Test
    public void testInvalidItemTypeLengthCannotBeAddedToDB() {
        Item item = new Item("Item", "invalidType", "");
        boolean exceptionThrown = false;
        try {
            dataRepo.addNewItemToDB(item.getItemName(), item.getItemType(), item.getItemImage());
        } catch (Exception e) {
            exceptionThrown = true;
        } finally {
            Assert.assertTrue(exceptionThrown);
            shutdown();
        }
    }

    @Test
    public void testValidRelicCanBeAddedToDB() {
        Relic relic = new Relic("Relic", "");
        dataRepo.addNewRelicToDB(relic.getRelicName(), relic.getRelicImage());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM relic WHERE relic_name = ?", new Object[]{"Relic"}, Integer.class);
        Assert.assertEquals(1, count);
        shutdown();
    }

    private void shutdown() {
        db.shutdown();
    }
}
