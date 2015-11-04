package sk.upjs.ics.SwiPoistovna.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.SwiPoistovna.Zakaznik;

/**
 *
 * @author Kristian
 */
public class DBZakaznikDAO implements ZakaznikDAO {
    
    private JdbcTemplate jdbcTemplate;
    

    public DBZakaznikDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    
    
}
