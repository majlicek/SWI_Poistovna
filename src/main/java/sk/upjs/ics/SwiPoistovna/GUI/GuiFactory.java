package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Cursor;

public enum GuiFactory {

    INSTANCE;

    private static int KLASICKY_KURZOR = 0;
    private static int RUKA_KURZOR = 12;

    private Insuright insuright;
    private LogoPanel logoPanel;
    private TlacidlaPanel tlacidlaPanel;
    private VyberPoisteniaPanel vyberPoisteniaPanel;
    private FormularPanel formularPanel;
    private VypisPoisteniPanel vypisPoisteniPanel;

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

    public VypisPoisteniPanel getVypisPoisteniPanel() {
        if (vypisPoisteniPanel == null) {
            vypisPoisteniPanel = new VypisPoisteniPanel();
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
        formularPanel = new FormularPanel();
        vypisPoisteniPanel = new VypisPoisteniPanel();
        insuright.zmenNaVyber();
    }

    public void tlacidloInfo() {
        System.out.println("POMOZ SI SAM! hahahaha...");
    }

    public void tlacidloZrus() {
        formularPanel.zmazFormular();
    }

    public void tlacidloOk() {
        if (formularPanel.potvrd()) {

            System.out.println("treba nahadzat data do vypisu");

            insuright.zmenNaVypis();
        }
    }

}
