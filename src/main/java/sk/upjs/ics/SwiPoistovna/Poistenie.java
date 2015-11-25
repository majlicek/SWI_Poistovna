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
     * Vypocita ceny vsetkych poistovni pre danu sadu pripoisteni. Vysledne sumy
     * je uz potom mozno brat z metod Poistovni, ktore tato metoda vrati. Pre
     * klesajucu sumu vypocita kolko bude za tie roky platit v priemere.
     *
     * @return {@code List<Poistovna>} zoznam poistovni, ktore tieto
     * pripoistenia ponukaju. Z prvkov tohto zoznamu sa uz daju vytiahnut
     * konkretne hodnoty.
     */
    public List<Poistovna> vypocitajCeny() {
        //TODO

        if (Manager.INSTANCE.getTypPoistenia() != Manager.TypPoistenia.KLESAJUCA_SUMA && Manager.INSTANCE.getTypPoistenia() != Manager.TypPoistenia.FIXNA_SUMA || Manager.INSTANCE.getTypPoistenia() == Manager.TypPoistenia.ZIADNE) {
            System.err.println("Nie je zvoleny typ poistenia! Treba nastavit ci chceme fixnu alebo klesajucu sumu cez setTypPoistenia");
            return new ArrayList<Poistovna>();
        }

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
                        suma = new BigDecimal((this.zadanaSuma / 1000) * koef).add(suma, MathContext.DECIMAL32);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 6) {
                        koef = insurightDAO.getKoeficientKritickeChoroby(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        suma = new BigDecimal((this.zadanaSuma / 1000) * koef).add(suma, MathContext.DECIMAL32);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 5) {
                        koef = insurightDAO.getKoeficientHospitalizacia(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        suma = new BigDecimal((this.zadanaSuma / 1000) * koef).add(suma, MathContext.DECIMAL32);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 3) {
                        koef = insurightDAO.getKoeficientDenneOdskodnenie29dni(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        suma = new BigDecimal((this.zadanaSuma / 1000) * koef).add(suma, MathContext.DECIMAL32);
                    }

                } catch (RuntimeException e) {
                    vyhovujucePoistovne.remove(poistovna);
                    System.out.println("Poistovna " + poistovna.getNazov() + " sa nebude brat k vypoctom do uvahy. Nesplna pripoistenie:" + e.getMessage());
                    break iteracaCezPripoistenia;
                }

            }
            for (Poistovna poistovna2 : vyhovujucePoistovne) {
                if (poistovna.equals(poistovna2)) {
                    if (Manager.INSTANCE.getTypPoistenia() == Manager.TypPoistenia.FIXNA_SUMA) {
                        poistovna2.setCenaMesacna(suma);
                        poistovna2.setCenaRocna(suma.multiply(new BigDecimal(12)));
                        poistovna2.setCenaPolRocna(suma.multiply(new BigDecimal(6)));
                        poistovna2.setCenaStvrtRocna(suma.multiply(new BigDecimal(3)));
                    }

                    if (Manager.INSTANCE.getTypPoistenia() == Manager.TypPoistenia.KLESAJUCA_SUMA) {
                        BigDecimal zatialNeprevedenaRocnaSuma = new BigDecimal(BigInteger.ONE);

                        zatialNeprevedenaRocnaSuma = zatialNeprevedenaRocnaSuma.multiply(suma);
                        zatialNeprevedenaRocnaSuma = zatialNeprevedenaRocnaSuma.multiply(new BigDecimal(12));

                        BigDecimal odcitanec = new BigDecimal(BigInteger.ONE);
                        odcitanec = odcitanec.multiply(zatialNeprevedenaRocnaSuma.divide(new BigDecimal(Manager.INSTANCE.getDobaPoistenia())));

                        BigDecimal vyslednaSuma = new BigDecimal(BigInteger.ZERO);
                        for (int m = 0; m < Manager.INSTANCE.getDobaPoistenia(); m++) {
                            vyslednaSuma = vyslednaSuma.add(zatialNeprevedenaRocnaSuma.subtract(odcitanec.multiply(new BigDecimal(m))));
                        }
                        vyslednaSuma = vyslednaSuma.divide(new BigDecimal(Manager.INSTANCE.getDobaPoistenia()));

                        poistovna2.setCenaMesacna(vyslednaSuma.divide(new BigDecimal(12),6,BigDecimal.ROUND_UP));
                        poistovna2.setCenaRocna(vyslednaSuma);
                        poistovna2.setCenaPolRocna(vyslednaSuma.divide(new BigDecimal(2),6,BigDecimal.ROUND_UP));
                        poistovna2.setCenaStvrtRocna(vyslednaSuma.divide(new BigDecimal(4),6,BigDecimal.ROUND_UP));
                    }

                    break;
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
