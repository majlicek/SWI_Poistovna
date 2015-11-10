package sk.upjs.ics.SwiPoistovna.DAO;

import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Kristian
 */
public class DBPoistenieDAO implements PoistenieDAO {

    private JdbcTemplate jdbcTemplate;

    public DBPoistenieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean testDb() {

        List<String> testString = jdbcTemplate.queryForList("SELECT meno FROM TestTable WHERE id=1", String.class);

        if (testString.isEmpty()) {
            return false;
        }
        if (testString.get(0).equals("ayy")) {
            return true;
        }
        return false;
    }

}
