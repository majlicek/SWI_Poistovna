package sk.upjs.ics.SwiPoistovna.DAO;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Kristian
 */
public class DBPoistovnaDAO implements PoistovnaDAO{

    private JdbcTemplate jdbcTemplate;

    public DBPoistovnaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

}
