package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Cursor;
import java.awt.Desktop;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.SwiPoistovna.Manager;
import sk.upjs.ics.SwiPoistovna.Poistenie;

public enum GuiFactory {

    INSTANCE;

    public static final String ODKAZ_NA_STRANKU = "http://s.ics.upjs.sk/~swi_poistovna/";

    private static int KLASICKY_KURZOR = 0;
    private static int RUKA_KURZOR = 12;

    private Insuright insuright;
    private LogoPanel logoPanel;
    private TlacidlaPanel tlacidlaPanel;
    private VyberPoisteniaPanel vyberPoisteniaPanel;
    private FormularPanel formularPanel;
    private VypisPoistovniPanel vypisPoisteniPanel;

    public Insuright getInsuright() {
        if (insuright == null) {
            insuright = new Insuright();
        }
        return insuright;
    }

    public LogoPanel getLogoPanel() {
        if (logoPanel == null) {
            logoPanel = new LogoPanel();
        }
        return logoPanel;
    }

    public TlacidlaPanel getTlacidlaPanel() {
        if (tlacidlaPanel == null) {
            tlacidlaPanel = new TlacidlaPanel();
        }
        return tlacidlaPanel;
    }

    public VyberPoisteniaPanel getVyberPoisteniaPanel() {
        if (vyberPoisteniaPanel == null) {
            vyberPoisteniaPanel = new VyberPoisteniaPanel();
        }
        return vyberPoisteniaPanel;
    }

    public FormularPanel getFormularPanel() {
        if (formularPanel == null) {
            formularPanel = new FormularPanel();
        }
        return formularPanel;
    }

    public VypisPoistovniPanel getVypisPoisteniPanel() {
        if (vypisPoisteniPanel == null) {
            vypisPoisteniPanel = new VypisPoistovniPanel();
        }
        return vypisPoisteniPanel;
    }

    public void zmenKurzor(boolean stav) {
        if (stav) {
            insuright.setCursor(Cursor.getPredefinedCursor(RUKA_KURZOR));
        } else {
            insuright.setCursor(Cursor.getPredefinedCursor(KLASICKY_KURZOR));
        }
    }

    public void tlacidloDomov() {
        vyberPoisteniaPanel = new VyberPoisteniaPanel();
        formularPanel = new FormularPanel();
        vypisPoisteniPanel = new VypisPoistovniPanel();
        insuright.zmenNaVyber();
    }

    public void tlacidloInfo() {
        //System.out.println("POMOZ SI SAM! hahahaha...");

        try {
            Desktop.getDesktop().browse(new URL(ODKAZ_NA_STRANKU).toURI());
        } catch (Exception ex) {
            //Logger.getLogger(GuiFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("nepodarilo sa otvorit stranku");
        }
    }

    public void tlacidloZrus() {
        formularPanel.zmazFormular();
    }

    public void tlacidloOk() {
        if (formularPanel.potvrd()) {

            System.out.println("test pred tahanim z databazy");
            
            Poistenie poistenie = new Poistenie(100);
            Manager.INSTANCE.setPoistovne(poistenie.vypocitajCeny());
            vypisPoisteniPanel = new VypisPoistovniPanel();
            
            System.out.println("test po tahani z databazy");
            
            insuright.zmenNaVypis();
        }
    }

}
