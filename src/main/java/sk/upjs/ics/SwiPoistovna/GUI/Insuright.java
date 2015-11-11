package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;

public class Insuright extends JFrame {

    public enum Stav {

        VYBER_POISTENIA, FORMULAR, VYPIS_POISTENI
    }

    private Stav stav;

    private static final Component CENTER_SCREEN = null;

    private LogoPanel logoPanel;
    private TlacidlaPanel tlacidlaPanel;
    private VyberPoisteniaPanel vyberPoisteniaPanel;
    private FormularPanel formularPanel;
    private VypisPoisteniPanel vypisPoisteniPanel;

    public Insuright() {
        setLayout(new MigLayout("", "[fill, grow]", "[][][fill, grow][]"));
        getContentPane().setBackground(Color.white);
        setPreferredSize(new Dimension(600, 550));
        setTitle("Insuright");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        logoPanel = GuiFactory.INSTANCE.getLogoPanel();
        tlacidlaPanel = GuiFactory.INSTANCE.getTlacidlaPanel();
        vyberPoisteniaPanel = GuiFactory.INSTANCE.getVyberPoisteniaPanel();

        add(logoPanel, "wrap");
        add(tlacidlaPanel, "wrap");
        add(vyberPoisteniaPanel, "wrap");

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
        add(vyberPoisteniaPanel);
        setPreferredSize(getSize());
        pack();
        update(getGraphics());
        stav = Stav.VYBER_POISTENIA;
    }

    public void zmenNaFormular() {
        if (stav == Stav.VYBER_POISTENIA) {
            remove(vyberPoisteniaPanel);
        }
        if (stav == Stav.VYPIS_POISTENI) {
            remove(vypisPoisteniPanel);
        }
        formularPanel = GuiFactory.INSTANCE.getFormularPanel();
        add(formularPanel);
        setPreferredSize(getSize());
        pack();
        update(getGraphics());
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
        add(vypisPoisteniPanel);
        setPreferredSize(getSize());
        pack();
        update(getGraphics());
        stav = Stav.VYPIS_POISTENI;
    }
    
    public static void main(String[] args) {
        Insuright poistenie = GuiFactory.INSTANCE.getPoistenie();
        poistenie.setVisible(true);
        poistenie.setLocationRelativeTo(CENTER_SCREEN);
    }

}
