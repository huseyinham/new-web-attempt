package smiteTroll.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import smiteTroll.classes.God;
import smiteTroll.repositories.GodRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GodRepositoryTest {

    private GodRepository godRepo = new GodRepository();
    private EmbeddedDatabase db;

    @Before
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("/db/migration/V1__initial_schema.sql", "/db/migration/V2__adding_static_data.sql")
                .build();
        godRepo.setDataSource(db);
    }

    @Test
    public void testGodIsAbleToBeSelectedInitially() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        jdbcTemplate.update("INSERT INTO god (god_name, god_type) VALUES ('initialGod', 'magical')");
        God initialGod = godRepo.getNewGod();
        String dbGodName = jdbcTemplate.queryForObject("SELECT god_name FROM god WHERE god_name = ?", new Object[]{"initialGod"}, String.class);
        Assert.assertTrue(initialGod.getGodName().equals(dbGodName));
        shutdown();
    }

    @Test
    public void testPreviousGodNotReselected()  {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        jdbcTemplate.update("INSERT INTO god (god_name, god_type) VALUES ('initialGod', 'magical')");
        jdbcTemplate.update("INSERT INTO god (god_name, god_type) VALUES ('newGod', 'magical')");
        God dbInitialGod = jdbcTemplate.query("SELECT * FROM god WHERE god_name = ?", new Object[]{"initialGod"}, new GodExtractor());
        God dbNewGod = jdbcTemplate.query("SELECT * FROM god WHERE god_name = ?", new Object[]{"newGod"}, new GodExtractor());
        God selectedGod = godRepo.reRoll(dbInitialGod);
        Assert.assertTrue(selectedGod.getGodName().equals(dbNewGod.getGodName()));
        Assert.assertTrue(!selectedGod.getGodName().equals(dbInitialGod.getGodName()));
        shutdown();
    }

    private void shutdown() {
        db.shutdown();
    }

    private static class GodExtractor implements ResultSetExtractor<God> {
        @Override
        public God extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            resultSet.next();
            return asGod(resultSet);
        }

        private static God asGod(ResultSet rs) throws SQLException {
            String godName = rs.getString("god_name");
            String godType = rs.getString("god_type");
            return new God(godName, godType);
        }
    }
}
