package sk.upjs.ics.SwiPoistovna;

import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.SwiPoistovna.DAO.DBPoistenieDAO;
import sk.upjs.ics.SwiPoistovna.DAO.DaoFactory;

/**
 * Hello world!
 *
 */
public class App {

    private static DBPoistenieDAO poistenieDao = DaoFactory.INSTANCE.getPoistenieDao();

    public static void main(String[] args) {
        System.out.println(poistenieDao.testDb());
    }

}
