/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.SwiPoistovna.GUI;

import java.util.Calendar;
import javax.swing.JRadioButton;
import junit.framework.TestCase;
import sk.upjs.ics.SwiPoistovna.InputVerifier;

/**
 *
 * @author andrejtetak
 */
public class FormularPanelTest extends TestCase {

    public FormularPanelTest(String testName) {
        super(testName);
    }

    /**
     * Metoda overuje spravne zadany rok
     */
    public void testRokNarodenia() {
        FormularPanel formular = new FormularPanel();
        // tu bude metoda ktora ziska rok z formularu
        // nieco ako formular.getRokNarodeniaText(), teraz len umelo
        String rok = "1993";
        assertTrue("Zadany rok nie je cislo!", InputVerifier.inputIsValidNumber(rok));
    }

    /**
     * Metoda overuje rozsah zadaneho roku
     */
    public void testRozsahuRokaNarodenia() {
        FormularPanel formular = new FormularPanel();
        // tu bude metoda ktora ziska rok z formularu
        // nieco ako formular.getRokNarodeniaText(), teraz len umelo
        String rok = "1990";
        if (!InputVerifier.inputIsValidNumber(rok)) {
            fail("Zadany rok nie je cislo!");
        }
        int rozsahOd = 1800;
        int rozsahPo = Calendar.getInstance().get(Calendar.YEAR);
        assertTrue("Zadany rok nie je v rozsahu od " + rozsahOd + " po " + rozsahPo, InputVerifier.numberIsBetween(Integer.parseInt(rok), rozsahOd, rozsahPo));
    }

    /**
     * Metoda overuje ci zadana doba poistenia je kladne cislo
     */
    public void testDobaPoistenia() {
        FormularPanel formular = new FormularPanel();
        // tu bude metoda ktora ziska dobu poistenia z formularu
        // nieco ako formular.getRokNarodeniaText(), teraz len umelo
        String dobaPoistenia = "10";
        if (!InputVerifier.inputIsValidNumber(dobaPoistenia)) {
            fail("Zadana doba poistenia nie je cislo!");
        }
        assertTrue("Zadana doba poistenia nie je kladne cislo!", InputVerifier.numberIsPositive(Integer.parseInt(dobaPoistenia)));
    }

    /**
     * Overenie ci je zvolena aspon 1 z 3 rizikovych skupin
     */
    public void testRizikovaSkupina() {
        JRadioButton button1 = new JRadioButton();
        JRadioButton button2 = new JRadioButton();
        JRadioButton button3 = new JRadioButton();
        button1.setSelected(true);
        boolean selected = false;
        if (button1.isSelected() || button2.isSelected() || button3.isSelected()) {
            selected = true;
        }
        assertTrue("Nie je zvolena ziadna rizikova skupina!", selected);
    }

    /**
     * Overenie ci je zvoleny aspon 1 z 3 pracovnych pomerov
     */
    public void testPracovnyPomer() {
        JRadioButton zamestnany = new JRadioButton();
        JRadioButton nezamestnany = new JRadioButton();
        JRadioButton szco = new JRadioButton();
        zamestnany.setSelected(true);
        boolean selected = false;
        if (zamestnany.isSelected() || nezamestnany.isSelected() || szco.isSelected()) {
            selected = true;
        }
        assertTrue("Nie je zvoleny ziadny pracovny pomer!", selected);
    }

    /**
     * Ak je zvoleny pracovny pomer ako zamestnany, overi sa ci je zadana dlzka
     */
    public void testDobyZamestnanyia() {
        JRadioButton zamestnany = new JRadioButton();
        zamestnany.setSelected(true);
        if (zamestnany.isSelected()) {
            String dobaZamestnania = "10";
            if (!InputVerifier.inputIsValidNumber(dobaZamestnania)) {
                fail("Zadana doba zamestnania nie je cislo!");
            }
            assertTrue("Zadana doba zamestnania nie je kladne cislo!", InputVerifier.numberIsPositive(Integer.parseInt(dobaZamestnania)));
        }
    }

}
