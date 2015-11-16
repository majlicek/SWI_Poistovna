package sk.upjs.ics.SwiPoistovna;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.SwiPoistovna.DAO.DaoFactory;
import sk.upjs.ics.SwiPoistovna.DAO.InsurightDAO;

/**
 *
 * @author Kristian
 */
public class Poistenie {

    private int id;
    private String nazov;
    private int dlzkaPoistenia;
    private int zadanaSuma;
    private InsurightDAO insurightDAO = DaoFactory.INSTANCE.getInsurightDAO();
    
    /**
     *
     * Vypocita ceny vsetkych poistovni pre danu sadu pripoisteni. Vysledne
     * sumy je uz potom mozno brat z metod Poistovni, ktore tato metoda vrati.
     * 
     *
     * @return {@code List<Poistovna>} zoznam poistovni, ktore tieto pripoistenia
     * ponukaju. Z prvkov tohto zoznamu sa uz daju vytiahnut konkretne hodnoty.
     */
    public List<Poistovna> vypocitajCeny() {
        //TODO
        List<Poistovna> vsetkyPoistovne = insurightDAO.getPoistovne();
        List<Poistovna> vyhovujucePoistovne = new ArrayList<Poistovna>();
        vyhovujucePoistovne.addAll(vsetkyPoistovne);
        
        iteraciaCezVsetkyPoistovne:
        for (Poistovna poistovna : vsetkyPoistovne) {
            BigDecimal suma = new BigDecimal(BigInteger.ZERO);
            double koef = 0;
            iteracaCezPripoistenia:
            for (int i = 0; i < Manager.INSTANCE.getPripoistenia().length; i++) {
                if (i == 1 || i == 2 || i == 4) {
                    // tabulky pre [1, 2, 4]trvaleNasledky, trvaleNasledkyProg, praceneschopnost este 
                    // nemame, preto aby sme mali aspon 1 poistovnu v koncovom vypocte,
                    // tak tieto pripoistenia neberieme do uvahy
                    continue;
                }
                
                try {
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 0) {
                        koef = insurightDAO.getKoeficientSmrt(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        suma = new BigDecimal((this.zadanaSuma / 1000) * koef).add(suma,MathContext.DECIMAL32);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 6) {
                        koef = insurightDAO.getKoeficientKritickeChoroby(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        suma = new BigDecimal((this.zadanaSuma / 1000) * koef).add(suma,MathContext.DECIMAL32);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 5) {
                        koef = insurightDAO.getKoeficientHospitalizacia(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        suma = new BigDecimal((this.zadanaSuma / 1000) * koef).add(suma,MathContext.DECIMAL32);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 3) {
                        koef = insurightDAO.getKoeficientDenneOdskodnenie29dni(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        suma = new BigDecimal((this.zadanaSuma / 1000) * koef).add(suma,MathContext.DECIMAL32);
                    }
                    
                } catch (RuntimeException e) {
                    vyhovujucePoistovne.remove(poistovna);
                    System.out.println("Poistovna "+poistovna.getNazov()+" sa nebude brat k vypoctom do uvahy. Nesplna pripoistenie:"+e.getMessage());
                    break iteracaCezPripoistenia;
                }
                
            }
            for (Poistovna poistovna2 : vyhovujucePoistovne) {
                if (poistovna.equals(poistovna2)) {
                    poistovna2.setCenaMesacna(suma);
                    poistovna2.setCenaRocna(suma.multiply(new BigDecimal(12)));
                    poistovna2.setCenaPolRocna(suma.multiply(new BigDecimal(6)));
                    poistovna2.setCenaStvrtRocna(suma.multiply(new BigDecimal(3)));
                    
                }
            }
        }
        return vyhovujucePoistovne;
    }
    
    public Poistenie(int id, String nazov, int zadanaSuma) {
        this.id = id;
        this.nazov = nazov;
        this.zadanaSuma = zadanaSuma;
        
    }
    
    public Poistenie(int zadanaSuma) {
        this.zadanaSuma = zadanaSuma;
        
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNazov() {
        return nazov;
    }
    
    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
    
    public int getDlzkaPoistenia() {
        return dlzkaPoistenia;
    }
    
    public void setDlzkaPoistenia(int dlzkaPoistenia) {
        this.dlzkaPoistenia = dlzkaPoistenia;
    }
    
    public int getZadanaSuma() {
        return zadanaSuma;
    }
    
    public void setZadanaSuma(int zadanaSuma) {
        this.zadanaSuma = zadanaSuma;
    }
    
}
