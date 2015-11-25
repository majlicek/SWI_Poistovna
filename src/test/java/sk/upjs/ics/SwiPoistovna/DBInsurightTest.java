/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.SwiPoistovna;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.List;
import junit.framework.TestCase;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author andrejtetak
 */
public class DBInsurightTest extends TestCase {

    private static final String DB_PRIHLASOVCIE_MENO = "swi_poistovna";
    private static final String DB_PRIHLASOVCIE_HESLO = "Milan2000";
    private static final String DB_ADRESA = "jdbc:mysql://s.ics.upjs.sk:3306/swi_poistovna";

    private JdbcTemplate jdbcTemplate;

    public DBInsurightTest(String testName) {
        super(testName);
    }

    @Override
    public void setUp() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(DB_ADRESA);
        dataSource.setUser(DB_PRIHLASOVCIE_MENO);
        dataSource.setPassword(DB_PRIHLASOVCIE_HESLO);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void testPristupKDatabaze() {

        // so spravnymi prihlasovacimi udajmi
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(DB_ADRESA);
        dataSource.setUser(DB_PRIHLASOVCIE_MENO);
        dataSource.setPassword(DB_PRIHLASOVCIE_HESLO);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String testString1 = jdbcTemplate.queryForObject("SELECT meno FROM TestTable WHERE id=1", String.class);
        assertEquals("Neviem sa pripojit k databaze!", "ayy", testString1);

        //s nespravnymi prihlasovacimi udajmi
        boolean pristupKDatabaze = true;
        try {
            dataSource = new MysqlDataSource();
            dataSource.setURL(DB_ADRESA);
            dataSource.setUser(DB_PRIHLASOVCIE_MENO);
            dataSource.setPassword("nbusr123");
            jdbcTemplate = new JdbcTemplate(dataSource);
            testString1 = jdbcTemplate.queryForObject("SELECT meno FROM TestTable WHERE id=1", String.class);
        } catch (CannotGetJdbcConnectionException ex) {
            pristupKDatabaze = false;
        }
        assertFalse(pristupKDatabaze);
    }

    public void testPridanieRiadkaVDatabaze() {
        String pocet = "SELECT count(*) FROM TestTable";
        int pocetRiadkov = jdbcTemplate.queryForObject(pocet, Integer.class);
        String query = "INSERT INTO `TestTable` (`id`, `meno`, `cislo`) VALUES (99,'janko','123')";
        jdbcTemplate.update(query);
        int pocetRiadkovNovy = jdbcTemplate.queryForObject(pocet, Integer.class);
        assertEquals(pocetRiadkov + 1, pocetRiadkovNovy);
    }

    public void testOdstranenieRiadkaVDatabaze() {
        String pocet = "SELECT count(*) FROM TestTable";
        int pocetRiadkov = jdbcTemplate.queryForObject(pocet, Integer.class);
        String query = "DELETE FROM `swi_poistovna`.`TestTable` WHERE `TestTable`.`id` = 99";
        jdbcTemplate.execute(query);
        int pocetRiadkovNovy = jdbcTemplate.queryForObject(pocet, Integer.class);
        assertEquals(pocetRiadkov - 1, pocetRiadkovNovy);
    }

    public void testGetPoistovne() {
        List<String> poistovne = jdbcTemplate.queryForList("SELECT Nazov FROM Poistovne", String.class);
        assertEquals("Pocet poistovni v databaze nesedi!", 2, poistovne.size());
        assertEquals("Wustenrot", poistovne.get(0));
        assertEquals("AXA", poistovne.get(1));
    }

    public void testGetKoeficientSmrt() {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM Smrt WHERE Poistovna = 1 AND VEK = 15  AND PoistnaDoba = 1 AND RizikovaSkupina = 1", Double.class);
        assertEquals(1.03, koeficienty.get(0));
        koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM Smrt", Double.class);
        assertEquals(4795, koeficienty.size());
    }

    public void testGetKoeficientKritickeChoroby() {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM KritickeChoroby WHERE Poistovna = 1 AND VEK= 0 AND PoistnaDoba = 1 AND RizikovaSkupina = 1", Double.class);
        assertEquals(0.75, koeficienty.get(0));
        koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM KritickeChoroby", Double.class);
        assertEquals(2675, koeficienty.size());
    }

    public void testGetKoeficientHospitalizacia() {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM Hospitalizacia WHERE Poistovna = 2 AND VEK = 15 AND PoistnaDoba = 1 AND RizikovaSkupina = 1", Double.class);
        assertEquals(1.7, koeficienty.get(0));
        koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM Hospitalizacia", Double.class);
        assertEquals(1825, koeficienty.size());
    }

    public void testGetKoeficientDenneOdskodnenie15dni() {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM DenneOdskodnenie15dni WHERE Poistovna = 1 AND VEK = 15 AND PoistnaDoba = 1 AND RizikovaSkupina = 1", Double.class);
        assertEquals(12.0, koeficienty.get(0));
        koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM DenneOdskodnenie15dni", Double.class);
        assertEquals(2045, koeficienty.size());
    }

    public void testGetKoeficientDenneOdskodnenie29dni() {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM DenneOdskodnenie29dni WHERE Poistovna = 1 AND VEK = 15 AND PoistnaDoba = 1 AND RizikovaSkupina = 1", Double.class);
        assertEquals(6.9, koeficienty.get(0));
        koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM DenneOdskodnenie29dni", Double.class);
        assertEquals(2045, koeficienty.size());
    }

}
