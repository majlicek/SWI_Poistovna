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

    private JdbcTemplate jdbc;

    private DBInsurightDAO insurightDAO;

    public DBInsurightDAO getInsurightDAO() {
        if (this.insurightDAO == null) {
            this.insurightDAO = new DBInsurightDAO(getJdbcTemplate());
        }
        return this.insurightDAO;
    }

    public JdbcTemplate getJdbcTemplate() {
        if (this.jdbc == null) {
            this.jdbc = new JdbcTemplate();
            jdbc.setDataSource(getDataSource());
        }
        return this.jdbc;
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
