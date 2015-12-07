package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.SwiPoistovna.Manager;

public class Insuright extends JFrame {

    private static final Component CENTER_SCREEN = null;

    private static final int SIRKA = 720;

    private static final int VYSKA = 576;

    public enum Stav {

        VYBER_POISTENIA, FORMULAR, VYPIS_POISTENI
    }

    private Stav stav;

    private LogoPanel logoPanel;
    private TlacidlaPanel tlacidlaPanel;
    private VyberPoisteniaPanel vyberPoisteniaPanel;
    private FormularPanel formularPanel;
    private VypisPoisteniPanel vypisPoisteniPanel;

    public Insuright() {
        setLayout(new MigLayout("", "[fill, grow]", "[][][fill, grow][]"));
        getContentPane().setBackground(Color.white);
        setPreferredSize(new Dimension(SIRKA, VYSKA));
        setTitle("Insuright");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        logoPanel = GuiFactory.INSTANCE.getLogoPanel();
        tlacidlaPanel = GuiFactory.INSTANCE.getTlacidlaPanel();
        vyberPoisteniaPanel = GuiFactory.INSTANCE.getVyberPoisteniaPanel();
        vypisPoisteniPanel = GuiFactory.INSTANCE.getVypisPoisteniPanel();

        add(logoPanel, "cell 0 0");
        add(tlacidlaPanel, "cell 0 1");
        add(vyberPoisteniaPanel, "cell 0 2");

        pack();
        stav = Stav.VYBER_POISTENIA;
    }

    public void zmenNaVyber() {
        if (stav == Stav.FORMULAR) {
            remove(formularPanel);
        }
        if (stav == Stav.VYPIS_POISTENI) {
            remove(vypisPoisteniPanel);
        }
        vyberPoisteniaPanel = GuiFactory.INSTANCE.getVyberPoisteniaPanel();
        add(vyberPoisteniaPanel, "cell 0 2");
        setPreferredSize(getSize());
        pack();
        update(getGraphics());
        vyberPoisteniaPanel.reset();
        tlacidlaPanel.FunkcneTlacidla(false, false, false);
        stav = Stav.VYBER_POISTENIA;
        Manager.INSTANCE.setTypPoistenia(Manager.TypPoistenia.ZIADNE);
    }

    public void zmenNaFormular() {
        if (stav == Stav.VYBER_POISTENIA) {
            remove(vyberPoisteniaPanel);
        }
        if (stav == Stav.VYPIS_POISTENI) {
            remove(vypisPoisteniPanel);
        }
        formularPanel = GuiFactory.INSTANCE.getFormularPanel();
        add(formularPanel, "cell 0 2");
        setPreferredSize(getSize());
        pack();
        update(getGraphics());
        tlacidlaPanel.FunkcneTlacidla(true, true, true);
        stav = Stav.FORMULAR;
    }

    public void zmenNaVypis() {
        if (stav == Stav.VYBER_POISTENIA) {
            remove(vyberPoisteniaPanel);
        }
        if (stav == Stav.FORMULAR) {
            remove(formularPanel);
        }
        vypisPoisteniPanel = GuiFactory.INSTANCE.getVypisPoisteniPanel();
        add(vypisPoisteniPanel, "cell 0 2");
        setPreferredSize(getSize());
        pack();
        update(getGraphics());
        tlacidlaPanel.FunkcneTlacidla(true, false, false);
        stav = Stav.VYPIS_POISTENI;
    }

    public static void main(String[] args) {
        Insuright poistenie = GuiFactory.INSTANCE.getInsuright();
        poistenie.setVisible(true);
        poistenie.setLocationRelativeTo(CENTER_SCREEN);
    }

}
