package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.SwiPoistovna.Manager;

public class Insuright extends JFrame {

    private static final Component CENTER_SCREEN = null;

    private static final int SIRKA = 750;

    private static final int VYSKA = 600;

    public enum Stav {

        VYBER_POISTENIA, FORMULAR, VYPIS_POISTENI
    }

    private Stav stav;

    private LogoPanel logoPanel;
    private TlacidlaPanel tlacidlaPanel;
    private VyberPoisteniaPanel vyberPoisteniaPanel;
    private FormularPanel formularPanel;
    private VypisPoistovniPanel vypisPoisteniPanel;

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
        remove(vyberPoisteniaPanel);
        vyberPoisteniaPanel = GuiFactory.INSTANCE.getVyberPoisteniaPanel();
        add(vyberPoisteniaPanel, "cell 0 2");
        setPreferredSize(getSize());
        pack();
        update(getGraphics());
        tlacidlaPanel.FunkcneTlacidla(false, false, false);
        stav = Stav.VYBER_POISTENIA;
        Manager.INSTANCE.reset();
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Insuright poistenie = GuiFactory.INSTANCE.getInsuright();
                poistenie.setVisible(true);
                poistenie.setLocationRelativeTo(CENTER_SCREEN);
            }
        });
    }

}
