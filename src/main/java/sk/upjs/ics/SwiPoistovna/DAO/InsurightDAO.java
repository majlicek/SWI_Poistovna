package sk.upjs.ics.SwiPoistovna.DAO;

import java.util.List;
import sk.upjs.ics.SwiPoistovna.Poistovna;

/**
 *
 * @author Kristian
 */
public interface InsurightDAO {
    
    public boolean testDb();
    
    public List<Poistovna> getPoistovne();
    
    public String getNazovPoistovne(int idPoistovna) throws RuntimeException;
    
    public double getKoeficientSmrt(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException;
    
    public double getKoeficientKritickeChoroby(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException;
    
    public double getKoeficientHospitalizacia(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException;
    
    public double getKoeficientDenneOdskodnenie15dni(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException;
    
    public double getKoeficientDenneOdskodnenie29dni(int idPoistovna, int vek, int poistnaDoba, int rizikovaSkupina) throws RuntimeException;
    
}
