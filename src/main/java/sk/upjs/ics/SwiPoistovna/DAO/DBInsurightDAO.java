package sk.upjs.ics.SwiPoistovna.DAO;

import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.SwiPoistovna.Poistovna;

/**
 *
 * @author Kristian
 */
public class DBInsurightDAO implements InsurightDAO {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Poistovna> poistovnaMapper = new BeanPropertyRowMapper(Poistovna.class);

    public DBInsurightDAO(JdbcTemplate jdbcTemplate) {
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
    
    @Override
    public List<Poistovna> getPoistovne() {
        List<Poistovna> poistovne = jdbcTemplate.query("SELECT * FROM Poistovne", poistovnaMapper);
        return poistovne;
    }

    @Override
    public String getNazovPoistovne(int idPoistovne) throws RuntimeException {
        List<String> nazvyPoistovne = jdbcTemplate.queryForList("SELECT Nazov FROM Poistovne WHERE id=" + idPoistovne, String.class);
        if (nazvyPoistovne.size() == 0) {
            throw new RuntimeException("Poistovna s id " + idPoistovne + " neexistuje!");
        }
        return nazvyPoistovne.get(0);

    }
    
    @Override
    public double getKoeficientSmrt(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM Smrt WHERE Poistovna=" + idPoistovna + " AND VEK=" + vek + " AND PoistnaDoba=" + poistnaDoba + " AND RizikovaSkupina=" + rizikovaSkupina, Double.class);
        if (koeficienty.size() == 0) {
            throw new RuntimeException("Neexistuje v tabulke Smrt koeficient pre poistovnu s id " + idPoistovna + ", vekom " + vek + ", poistnou dobou " + poistnaDoba + ", rizikovou skupinou " + rizikovaSkupina + ".");
        }
        return koeficienty.get(0);
    }

    @Override
    public double getKoeficientSmrtSposobenaUrazom(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM SmrtUrazom WHERE Poistovna=" + idPoistovna + " AND VEK=" + vek + " AND PoistnaDoba=" + poistnaDoba + " AND RizikovaSkupina=" + rizikovaSkupina, Double.class);
        if (koeficienty.size() == 0) {
            throw new RuntimeException("Neexistuje v tabulke SmrtUrazom koeficient pre poistovnu s id " + idPoistovna + ", vekom " + vek + ", poistnou dobou " + poistnaDoba + ", rizikovou skupinou " + rizikovaSkupina + ".");
        }
        return koeficienty.get(0);
    }
    
    @Override
    public double getKoeficientTrvaleNasledky(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM TrvaleNasledky WHERE Poistovna=" + idPoistovna + " AND VEK=" + vek + " AND PoistnaDoba=" + poistnaDoba + " AND RizikovaSkupina=" + rizikovaSkupina, Double.class);
        if (koeficienty.size() == 0) {
            throw new RuntimeException("Neexistuje v tabulke TrvaleNasledky koeficient pre poistovnu s id " + idPoistovna + ", vekom " + vek + ", poistnou dobou " + poistnaDoba + ", rizikovou skupinou " + rizikovaSkupina + ".");
        }
        return koeficienty.get(0);
    }

    @Override
    public double getKoeficientKritickeChoroby(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM KritickeChoroby WHERE Poistovna=" + idPoistovna + " AND VEK=" + vek + " AND PoistnaDoba=" + poistnaDoba + " AND RizikovaSkupina=" + rizikovaSkupina, Double.class);
        if (koeficienty.size() == 0) {
            throw new RuntimeException("Neexistuje v tabulke KritickeChoroby koeficient pre poistovnu s id " + idPoistovna + ", vekom " + vek + ", poistnou dobou " + poistnaDoba + ", rizikovou skupinou " + rizikovaSkupina + ".");
        }
        return koeficienty.get(0);
    }

    @Override
    public double getKoeficientHospitalizacia(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM Hospitalizacia WHERE Poistovna=" + idPoistovna + " AND VEK=" + vek + " AND PoistnaDoba=" + poistnaDoba + " AND RizikovaSkupina=" + rizikovaSkupina, Double.class);
        if (koeficienty.size() == 0) {
            throw new RuntimeException("Neexistuje v tabulke Hospitalizacia koeficient pre poistovnu s id " + idPoistovna + ", vekom " + vek + ", poistnou dobou " + poistnaDoba + ", rizikovou skupinou " + rizikovaSkupina + ".");
        }
        return koeficienty.get(0);
    }
    
    @Override
    public double getKoeficientDenneOdskodnenie15dni(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM DenneOdskodnenie15dni WHERE Poistovna=" + idPoistovna + " AND VEK=" + vek + " AND PoistnaDoba=" + poistnaDoba + " AND RizikovaSkupina=" + rizikovaSkupina, Double.class);
        if (koeficienty.size() == 0) {
            throw new RuntimeException("Neexistuje v tabulke DenneOdskodnenie15dni koeficient pre poistovnu s id " + idPoistovna + ", vekom " + vek + ", poistnou dobou " + poistnaDoba + ", rizikovou skupinou " + rizikovaSkupina + ".");
        }
        return koeficienty.get(0);
    }
    @Override
    public double getKoeficientDenneOdskodnenie29dni(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException {
        List<Double> koeficienty = jdbcTemplate.queryForList("SELECT Koeficient FROM DenneOdskodnenie29dni WHERE Poistovna=" + idPoistovna + " AND VEK=" + vek + " AND PoistnaDoba=" + poistnaDoba + " AND RizikovaSkupina=" + rizikovaSkupina, Double.class);
        if (koeficienty.size() == 0) {
            throw new RuntimeException("Neexistuje v tabulke DenneOdskodnenie29dni koeficient pre poistovnu s id " + idPoistovna + ", vekom " + vek + ", poistnou dobou " + poistnaDoba + ", rizikovou skupinou " + rizikovaSkupina + ".");
        }
        return koeficienty.get(0);
    }

    
}
