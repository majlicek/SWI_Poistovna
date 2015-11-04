package sk.upjs.ics.SwiPoistovna.DAO;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Kristian
 */
public class DBPoistenieDAO {

    private JdbcTemplate jdbcTemplate;

    public DBPoistenieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

}
