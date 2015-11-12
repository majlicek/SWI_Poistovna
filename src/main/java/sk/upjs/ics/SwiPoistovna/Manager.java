package sk.upjs.ics.SwiPoistovna;

public enum Manager {

    INSTANCE;

    public enum RizikovaSkupina {

        PRVA, DRUHA, TRETIA
    }

    public enum PracovnyPomer {

        ZAMESTNANY, NEZAMESTNANY, SZCO
    }

    private int rokNarodenia;
    private int dobaPoistenia;
    private RizikovaSkupina rizikovaSkupina;
    private PracovnyPomer pracovnyPomer;
    private int mesacnyPrijem;
    private boolean[] pripoistenia = new boolean[7];

    public void reset() {
        rokNarodenia = 0;
        dobaPoistenia = 0;
        rizikovaSkupina = RizikovaSkupina.PRVA;
        pracovnyPomer = PracovnyPomer.ZAMESTNANY;
        mesacnyPrijem = 0;
    }

    public int getRokNarodenia() {
        return rokNarodenia;
    }

    public void setRokNarodenia(int rokNarodenia) {
        this.rokNarodenia = rokNarodenia;
    }

    public int getDobaPoistenia() {
        return dobaPoistenia;
    }

    public void setDobaPoistenia(int dobaPoistenia) {
        this.dobaPoistenia = dobaPoistenia;
    }

    public RizikovaSkupina getRizikovaSkupina() {
        return rizikovaSkupina;
    }

    public void setRizikovaSkupina(RizikovaSkupina rizikovaSkupina) {
        this.rizikovaSkupina = rizikovaSkupina;
    }

    public PracovnyPomer getPracovnyPomer() {
        return pracovnyPomer;
    }

    public void setPracovnyPomer(PracovnyPomer pracovnyPomer) {
        this.pracovnyPomer = pracovnyPomer;
    }

    public int getMesacnyPrijem() {
        return mesacnyPrijem;
    }

    public void setMesacnyPrijem(int mesacnyPrijem) {
        this.mesacnyPrijem = mesacnyPrijem;
    }

}
