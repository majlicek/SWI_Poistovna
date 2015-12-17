package sk.upjs.ics.SwiPoistovna;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sk.upjs.ics.SwiPoistovna.DAO.DaoFactory;
import sk.upjs.ics.SwiPoistovna.DAO.InsurightDAO;

/**
 *
 * @author Kristian
 */
public class Poistenie {

    private int zadanaSuma;
    private static final RoundingMode roundingMode = RoundingMode.HALF_UP;
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
        if (Manager.INSTANCE.getTypPoistenia() != Manager.TypPoistenia.KLESAJUCA_SUMA && Manager.INSTANCE.getTypPoistenia() != Manager.TypPoistenia.FIXNA_SUMA || Manager.INSTANCE.getTypPoistenia() == Manager.TypPoistenia.ZIADNE) {
            System.err.println("Nie je zvoleny typ poistenia! Treba nastavit ci chceme fixnu alebo klesajucu sumu cez setTypPoistenia");
            return Collections.EMPTY_LIST;
        }

        List<Poistovna> vsetkyPoistovne = insurightDAO.getPoistovne();
        List<Poistovna> vyhovujucePoistovne = new ArrayList<Poistovna>();
        vyhovujucePoistovne.addAll(vsetkyPoistovne);

        iteraciaCezVsetkyPoistovne:
        for (Poistovna poistovna : vsetkyPoistovne) {
            double koef = 0;
            try {
                koef = insurightDAO.getKoeficientSmrt(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
            } catch (RuntimeException e) {
                System.out.println("Poistovna " + poistovna.getNazov() + " sa nebude brat k vypoctom do uvahy. Nesplna ZAKLADNE poistenie:" + e.getMessage());
                vyhovujucePoistovne.remove(poistovna);
                continue iteraciaCezVsetkyPoistovne;
            }
            BigDecimal docasnaSumaTotal = new BigDecimal((this.zadanaSuma / 1000) * koef);
            if (Manager.INSTANCE.getTypPoistenia().equals(Manager.TypPoistenia.KLESAJUCA_SUMA)) {
                docasnaSumaTotal = vyratajKlesajucuSumu(docasnaSumaTotal);

            }

            iteracaCezPripoistenia:
            for (int i = 0; i < Manager.INSTANCE.getPripoistenia().length; i++) {
                if (i == 2 || i == 4) {
                    // tabulky pre [2, 4]trvaleNasledkyProg, praceneschopnost este 
                    // nemame, preto aby sme mali aspon 1 poistovnu v koncovom vypocte,
                    // tak tieto pripoistenia neberieme do uvahy
                    //System.out.println("Pre vybrate pripoistenie "+Manager.INSTANCE.getNazvyPripoisteni(i)+" neexistuje tabulka a preto sa nebude brat k vypoctom do uvahy.");
                    continue;
                }

                try {
                    BigDecimal sumaNovehoPripoistenia;

                    if (Manager.INSTANCE.getPripoistenia(i) && i == 0) {
                        koef = insurightDAO.getKoeficientSmrtSposobenaUrazom(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        sumaNovehoPripoistenia = new BigDecimal((this.zadanaSuma / 1000) * koef);
                        docasnaSumaTotal = docasnaSumaTotal.add(sumaNovehoPripoistenia);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 6) {
                        koef = insurightDAO.getKoeficientKritickeChoroby(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        sumaNovehoPripoistenia = new BigDecimal((this.zadanaSuma / 1000) * koef);
                        docasnaSumaTotal = docasnaSumaTotal.add(sumaNovehoPripoistenia);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 5) {
                        koef = insurightDAO.getKoeficientHospitalizacia(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        sumaNovehoPripoistenia = new BigDecimal((this.zadanaSuma / 1000) * koef);
                        docasnaSumaTotal = docasnaSumaTotal.add(sumaNovehoPripoistenia);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 3) {
                        koef = insurightDAO.getKoeficientDenneOdskodnenie29dni(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        sumaNovehoPripoistenia = new BigDecimal((this.zadanaSuma / 1000) * koef);
                        docasnaSumaTotal = docasnaSumaTotal.add(sumaNovehoPripoistenia);
                    }
                    if (Manager.INSTANCE.getPripoistenia(i) && i == 1) {
                        koef = insurightDAO.getKoeficientTrvaleNasledky(poistovna.getId(), Verifier.SUCASNY_ROK - Manager.INSTANCE.getRokNarodenia(), Manager.INSTANCE.getDobaPoistenia(), Manager.INSTANCE.getRizikovaSkupinaCislom());
                        sumaNovehoPripoistenia = new BigDecimal((this.zadanaSuma / 1000) * koef);
                        docasnaSumaTotal = docasnaSumaTotal.add(sumaNovehoPripoistenia);
                    }

                } catch (RuntimeException e) {
                    vyhovujucePoistovne.remove(poistovna);
                    System.out.println("Poistovna " + poistovna.getNazov() + " sa nebude brat k vypoctom do uvahy. Nesplna pripoistenie:" + e.getMessage());
                    break iteracaCezPripoistenia;
                }

            }
            docasnaSumaTotal = docasnaSumaTotal.setScale(2, roundingMode);

            for (Poistovna vysledkovePoistovne : vyhovujucePoistovne) {
                if (poistovna.equals(vysledkovePoistovne)) {
                    vysledkovePoistovne.setCenaMesacna(docasnaSumaTotal.divide(new BigDecimal(12), 2, roundingMode).setScale(2, roundingMode));
                    vysledkovePoistovne.setCenaRocna(docasnaSumaTotal);
                    vysledkovePoistovne.setCenaPolRocna(docasnaSumaTotal.divide(new BigDecimal(2), 2, roundingMode).setScale(2, roundingMode));
                    vysledkovePoistovne.setCenaStvrtRocna(docasnaSumaTotal.divide(new BigDecimal(4), 2, roundingMode).setScale(2, roundingMode));
                    break;
                }
            }
        }

        return vyhovujucePoistovne;
    }

    public Poistenie(int poistnaSuma) {
        this.zadanaSuma = poistnaSuma;

    }

    public int getPoistnaSuma() {
        return zadanaSuma;
    }

    public void setPoistnaSuma(int poistnaSuma) {
        this.zadanaSuma = poistnaSuma;
    }

    public static BigDecimal vyratajKlesajucuSumu(BigDecimal docasSuma) {
        BigDecimal zatialNeprevedenaRocnaSuma = docasSuma;

        BigDecimal odcitanec = new BigDecimal(BigInteger.ONE);
        odcitanec = odcitanec.multiply(zatialNeprevedenaRocnaSuma.divide(new BigDecimal(Manager.INSTANCE.getDobaPoistenia()), 2, roundingMode));

        BigDecimal vyslednaSuma = new BigDecimal(BigInteger.ZERO);
        for (int m = 0; m < Manager.INSTANCE.getDobaPoistenia(); m++) {
            vyslednaSuma = vyslednaSuma.add(zatialNeprevedenaRocnaSuma.subtract(odcitanec.multiply(new BigDecimal(m))));
        }
        vyslednaSuma = vyslednaSuma.divide(new BigDecimal(Manager.INSTANCE.getDobaPoistenia())).setScale(2, roundingMode);
        return vyslednaSuma;

    }

}
