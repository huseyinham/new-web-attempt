package smiteTroll.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import smiteTroll.classes.Relic;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RelicRepository {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Relic> getRelics() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM relic ORDER BY RAND() LIMIT 2", new Object[]{}, new RelicListRowMapExtractor());
    }

    public Relic reRollRelic(List<Relic> relics) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM relic WHERE relic_name NOT IN (?,?) ORDER BY RAND() LIMIT 1",
                new Object[]{
                        relics.get(0).getRelicName(),
                        relics.get(1).getRelicName()},
                new RelicExtractor());
    }

    private static class RelicExtractor implements ResultSetExtractor<Relic> {

        @Override
        public Relic extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            resultSet.next();
            return asRelic(resultSet);
        }
    }

    private static class RelicListRowMapExtractor implements RowMapper<Relic> {

        @Override
        public Relic mapRow(ResultSet resultSet, int i) throws SQLException {
            return asRelic(resultSet);
        }
    }

    private static Relic asRelic(ResultSet resultset) throws SQLException {
        String relicName = resultset.getString("relic_name");
        String relicImage = resultset.getString("relic_image");
        return new Relic(relicName, relicImage);
    }
}
