package sk.upjs.ics.SwiPoistovna.DAO;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Kristian
 */
public enum DaoFactory {

    INSTANCE;

    private JdbcTemplate jdbcTemplate;

    private DBPoistenieDAO poistenieDAO;
    private DBZakaznikDAO zakaznikDAO;
    private DBPoistovnaDAO poistovnaDAO;

    public DBPoistovnaDAO getPoistovnaDao() {
        if (this.poistovnaDAO == null) {
            this.poistovnaDAO = new DBPoistovnaDAO(getJdbcTemplate());
        }
        return this.poistovnaDAO;
    }

    public DBZakaznikDAO getZakaznikDao() {
        if (this.zakaznikDAO == null) {
            this.zakaznikDAO = new DBZakaznikDAO(getJdbcTemplate());
        }
        return this.zakaznikDAO;
    }

    public DBPoistenieDAO getPoistenieDao() {
        if (this.poistenieDAO == null) {
            this.poistenieDAO = new DBPoistenieDAO(getJdbcTemplate());
        }
        return this.poistenieDAO;
    }

    public JdbcTemplate getJdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(getDataSource());
        }
        return this.jdbcTemplate;
    }

    public DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();

        //normal run
        dataSource.setURL("jdbc:mysql://s.ics.upjs.sk:3306/swi_poistovna");
        dataSource.setUser("swi_poistovna");
        dataSource.setPassword("Milan2000");

        /* TEST RUN
         *
         * todo
         *
         */
        return dataSource;
    }

}
