/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.SwiPoistovna;

import java.util.Calendar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import junit.framework.TestCase;
import sk.upjs.ics.SwiPoistovna.GUI.FormularPanel;
import sk.upjs.ics.SwiPoistovna.InputVerifier;
import sk.upjs.ics.SwiPoistovna.Manager;

/**
 *
 * @author andrejtetak
 */
public class FormularPanelTest extends TestCase {

    private FormularPanel formularPanel;

    private static final int ROZSAH_ROK_NARODENIA_OD = Calendar.getInstance().get(Calendar.YEAR) - 75;  //maximalne 75 rokov
    private static final int ROZSAH_ROK_NARODENIA_DO = Calendar.getInstance().get(Calendar.YEAR) - 15;  //minimalne 15 rokov
    private static final int MAXIMALNA_DOBA_POISTENIA = 75;
    private static final int MINIMALNA_DOBA_POISTENIA = 15;

    public FormularPanelTest(String testName) {
        super(testName);
    }

    @Override
    public void setUp() {
        //formularPanel = new FormularPanel();
    }

    /**
     * Metoda overuje rozsah zadaneho roku (15-75)
     */
    public void testRozsahuRokaNarodenia() {
        String hodnota = "1993";
        Manager.INSTANCE.setRokNarodenia(Integer.valueOf(hodnota));
        int rokNarodenia = Manager.INSTANCE.getRokNarodenia();
        assertTrue("Zadany rok nie je v rozsahu od " + ROZSAH_ROK_NARODENIA_OD + " po " + ROZSAH_ROK_NARODENIA_DO, InputVerifier.numberIsBetween(rokNarodenia, ROZSAH_ROK_NARODENIA_OD, ROZSAH_ROK_NARODENIA_DO));
    }

    /**
     * Metoda overuje ci zadana doba poistenia je kladne cislo (vacsie ako 0)
     */
    public void testDobaPoistenia() {
        String hodnota = "16";
        Manager.INSTANCE.setDobaPoistenia(Integer.valueOf(hodnota));
        int dobaPoistenia = Manager.INSTANCE.getDobaPoistenia();
        assertTrue("Zadana doba poistenia nie je v rozsahu od " + MINIMALNA_DOBA_POISTENIA + " po " + MAXIMALNA_DOBA_POISTENIA, InputVerifier.numberIsBetween(dobaPoistenia, MINIMALNA_DOBA_POISTENIA, MAXIMALNA_DOBA_POISTENIA));
    }

    /**
     * Overenie ci je zvolena aspon 1 z 3 rizikovych skupin
     */
    public void testRizikovaSkupina() {
        Manager.INSTANCE.setRizikovaSkupina(Manager.RizikovaSkupina.PRVA);
        Manager.RizikovaSkupina rizikovaSkupina = Manager.INSTANCE.getRizikovaSkupina();
        assertTrue("Nie je zvolena spravna rizikova skupina!", rizikovaSkupina == Manager.RizikovaSkupina.PRVA);

        Manager.INSTANCE.setRizikovaSkupina(Manager.RizikovaSkupina.DRUHA);
        rizikovaSkupina = Manager.INSTANCE.getRizikovaSkupina();
        assertTrue("Nie je zvolena spravna rizikova skupina!", rizikovaSkupina == Manager.RizikovaSkupina.DRUHA);
        
        Manager.INSTANCE.setRizikovaSkupina(Manager.RizikovaSkupina.TRETIA);
        rizikovaSkupina = Manager.INSTANCE.getRizikovaSkupina();
        assertTrue("Nie je zvolena spravna rizikova skupina!", rizikovaSkupina == Manager.RizikovaSkupina.TRETIA);
    }

    /**
     * Overenie ci je zvoleny aspon 1 z 3 pracovnych pomerov (deprecated)
     */
    public void testPracovnyPomer() {
        Manager.INSTANCE.setPracovnyPomer(Manager.PracovnyPomer.ZAMESTNANY);
        Manager.PracovnyPomer pracovnyPomer = Manager.INSTANCE.getPracovnyPomer();
        boolean selected = false;
        if (pracovnyPomer == Manager.PracovnyPomer.ZAMESTNANY || pracovnyPomer == Manager.PracovnyPomer.SZCO || pracovnyPomer == Manager.PracovnyPomer.NEZAMESTNANY) {
            selected = true;
        }
        assertTrue("Nie je zvoleny ziadny pracovny pomer!", selected);
    }

    /**
     * Ak je zvoleny pracovny pomer ako zamestnany, overi sa ci je zadany prijem
     */
    public void testPrijem() {
        Manager.INSTANCE.setPracovnyPomer(Manager.PracovnyPomer.ZAMESTNANY);
        Manager.INSTANCE.setMesacnyPrijem(1);
        Manager.PracovnyPomer pracovnyPomer = Manager.INSTANCE.getPracovnyPomer();
        int mesacnyPrijem = Manager.INSTANCE.getMesacnyPrijem();
        if (pracovnyPomer == Manager.PracovnyPomer.ZAMESTNANY) {
            assertTrue("Nie je zvoleny spravny prijem zamestnanca!", mesacnyPrijem > 0);
        } else {
            assertFalse("Je zvoleny prijem zamestnanca napriek tomu ze nie je zamestnany!", mesacnyPrijem > 0);
        }
    }

    /**
     * Test ci sa po staceni tlacitla reset vymazu vsetky polozky
     */
    public void testReset() {
        Manager.INSTANCE.reset();
        //rok narodenia
        int rokNarodenia = Manager.INSTANCE.getRokNarodenia();
        assertTrue("Rok sa nezresetoval!", rokNarodenia == 0);
        //doba poistenia
        int dobaPoistenia = Manager.INSTANCE.getDobaPoistenia();
        assertTrue("Doba poistenia sa nezresetovala!", dobaPoistenia == 0);
        //rizikova skupina
        Manager.RizikovaSkupina rizikovaSkupina = Manager.INSTANCE.getRizikovaSkupina();
        assertTrue("Nie je zvolena spravna rizikova skupina po resetovani!", rizikovaSkupina == Manager.RizikovaSkupina.PRVA);
        //pracovny pomer
        Manager.PracovnyPomer pracovnyPomer = Manager.INSTANCE.getPracovnyPomer();
        assertTrue("Nie je zvoleny spravny pracovny pomer po resetovani!", pracovnyPomer == Manager.PracovnyPomer.ZAMESTNANY);
        //mesacny prijem
        int mesacnyPrijem = Manager.INSTANCE.getMesacnyPrijem();
        assertTrue("Mesacny prijem sa nezresetoval!", mesacnyPrijem == 0);
    }

    /**
     * Ak chceme pripoistenie tak musime vyplnit urcite povinne udaje
     */
    public void testVstupnychUdajovPripoistenia() {
        // TODO
    }

}
