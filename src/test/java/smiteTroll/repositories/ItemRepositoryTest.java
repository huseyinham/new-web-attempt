package smiteTroll.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import smiteTroll.classes.God;
import smiteTroll.classes.Item;

import java.util.List;

public class ItemRepositoryTest {

    private ItemRepository itemRepo = new ItemRepository();
    private EmbeddedDatabase db;
    private static final God physicalGod = new God("PhysicalGod", "physical");
    private static final God magicalGod = new God("MagicalGod", "magical");

    @Before
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("/db/migration/V1__initial_schema.sql", "/db/migration/V2__adding_static_data.sql")
                .build();
        itemRepo.setDataSource(db);
    }

    @Test
    public void testingValidItemsForPhysicalGod() {
        createDbItemsForPhysicalTesting();
        assertValidItemsForGod("physical");
        shutdown();
    }

    @Test
    public void testingValidItemsForMagicalGod() {
        createDbItemsForMagicalTesting();
        assertValidItemsForGod("magical");
        shutdown();
    }

    @Test
    public void testingRerollWithPhysicalBoots() {
        createDbItemsForPhysicalTesting();
        List<Item> itemList = itemRepo.getItems("physical");
        Item reRolledItem = itemList.get(0);
        Item newItem = itemRepo.reRoll(physicalGod, reRolledItem, itemList);
        Assert.assertTrue(!newItem.getItemName().equals(reRolledItem.getItemName()));
        Assert.assertTrue(newItem.getItemType().equals(reRolledItem.getItemType()));
        shutdown();
    }

    @Test
    public void testingRerollWithMagicalBoots() {
        createDbItemsForMagicalTesting();
        List<Item> itemList = itemRepo.getItems("magical");
        Item reRolledItem = itemList.get(0);
        Item newItem = itemRepo.reRoll(magicalGod, reRolledItem, itemList);
        Assert.assertTrue(!newItem.getItemName().equals(reRolledItem.getItemName()));
        Assert.assertTrue(newItem.getItemType().equals(reRolledItem.getItemType()));
        shutdown();
    }

    @Test
    public void testingRerollWithPhysicalGod() {
        createDbItemsForPhysicalTesting();
        List<Item> itemList = itemRepo.getItems("physical");
        Item reRolledItem = itemList.get(1);
        Item newItem = itemRepo.reRoll(physicalGod, reRolledItem, itemList);
        Assert.assertTrue(!newItem.getItemName().equals(reRolledItem.getItemName()));
        Assert.assertTrue(newItem.getItemType().equals(physicalGod.getGodType()) || newItem.getItemType().equals("neutral"));
        shutdown();
    }

    @Test
    public void testingRerollWithMagicalGod() {
        createDbItemsForMagicalTesting();
        List<Item> itemList = itemRepo.getItems("magical");
        Item reRolledItem = itemList.get(1);
        Item newItem = itemRepo.reRoll(magicalGod, reRolledItem, itemList);
        Assert.assertTrue(!newItem.getItemName().equals(reRolledItem.getItemName()));
        Assert.assertTrue(newItem.getItemType().equals(magicalGod.getGodType()) || newItem.getItemType().equals("neutral"));
        shutdown();
    }

    private void assertValidItemsForGod(String type) {
        List<Item> itemList = itemRepo.getItems(type);
        for (Item item : itemList) {
            String actualResult = item.getItemType();
            Assert.assertTrue(actualResult.equals(type) || actualResult.equals(type + "_boots") || actualResult.equals("neutral"));
        }
    }

    private void createDbItemsForMagicalTesting() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemOne', 'magical')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemTwo', 'magical')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemThree', 'magical')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemFour', 'neutral')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemFive', 'neutral')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemSix', 'magical')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemSeven', 'neutral')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemEight', 'magical_boots')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemNine', 'magical_boots')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('nonValid', 'physical_boots')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('nonValidTwo', 'physical')");
    }

    private void createDbItemsForPhysicalTesting() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemOne', 'physical')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemTwo', 'neutral')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemThree', 'neutral')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemFour', 'physical')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemFive', 'physical')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemSix', 'physical')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemSeven', 'neutral')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemEight', 'physical_boots')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('itemNine', 'physical_boots')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('nonValid', 'magical_boots')");
        jdbcTemplate.update("INSERT INTO item (item_name, item_type) VALUES ('nonValidTwo', 'magical')");
    }

    private void shutdown() {
        db.shutdown();
    }

}