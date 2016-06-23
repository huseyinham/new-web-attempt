package smiteTroll.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import smiteTroll.classes.God;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GodRepository {


    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public God getNewGod() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM god ORDER BY RAND() LIMIT 1", new GodExtractor());
    }

    public God reRoll(God previousGod) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM god WHERE god_name != ? AND god_type = ? ORDER BY RAND() LIMIT 1", new Object[]{previousGod.getGodName(), previousGod.getGodType()}, new GodExtractor());
    }

    private static God asGod(ResultSet rs) throws SQLException {
        String godName = rs.getString("god_name");
        String godType = rs.getString("god_type");
        return new God(godName, godType);
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