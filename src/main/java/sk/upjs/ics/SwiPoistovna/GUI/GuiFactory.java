package sk.upjs.ics.SwiPoistovna.GUI;

public enum GuiFactory {

    INSTANCE;

    private Poistenie poistenie;
    private LogoPanel logoPanel;
    private TlacidlaPanel tlacidlaPanel;
    private VyberPoisteniaPanel vyberPoisteniaPanel;
    private FormularPanel formularPanel;
    private VypisPoisteniPanel vypisPoisteniPanel;

    public Poistenie getPoistenie() {
        if (poistenie == null) {
            poistenie = new Poistenie();
        }
        return poistenie;
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

    public void tlacidloDomov() {
        formularPanel = new FormularPanel();
        vypisPoisteniPanel = new VypisPoisteniPanel();
        poistenie.zmenNaVyber();
    }

    public void tlacidloZrus() {
        formularPanel.zmazFormular();
    }

    public void tlacidloOk() {
        System.out.println("klik na ok");
    }

}
