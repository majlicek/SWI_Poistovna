package sk.upjs.ics.SwiPoistovna;

import java.util.ArrayList;
import java.util.List;

public enum Manager {

    INSTANCE;

    public enum TypPoistenia {

        ZIADNE, FIXNA_SUMA, KLESAJUCA_SUMA
    }

    public enum RizikovaSkupina {

        PRVA, DRUHA, TRETIA
    }

    public enum PracovnyPomer {

        ZAMESTNANY, NEZAMESTNANY, SZCO
    }

    private TypPoistenia poistenie;
    private int rokNarodenia;
    private int dobaPoistenia;
    private RizikovaSkupina rizikovaSkupina;
    private int rizikovaSkupinaCislom;
    private PracovnyPomer pracovnyPomer;
    private int mesacnyPrijem;
    private int poistnaSuma;
    /*
     * [0smrtUrazom, 1trvaleNasledky, 2trvaleNasledkyProg, 3nevyhnutnaLiecba, 
     *   4praceneschopnost, 5hospitalizacia, 6kritickeChoroby]
     */
    private boolean[] pripoistenia = new boolean[7];
    private String[] nazvyPripoisteni = {"Smrť spôsobená úrazom","Trvalé následky","Trvalé následky s progresívnym zhoršovaním","Nevyhnutná liečba","Práceneschopnosť","Hospitalizácia","Kritické choroby"};
    

    private List<Poistovna> poistovne = new ArrayList<>();

    public void reset() {
        rokNarodenia = 0;
        dobaPoistenia = 0;
        rizikovaSkupina = RizikovaSkupina.PRVA;
        rizikovaSkupinaCislom = 0;
        pracovnyPomer = PracovnyPomer.ZAMESTNANY;
        mesacnyPrijem = 0;
        pripoistenia = new boolean[7];
        poistovne = new ArrayList<>();
        poistenie = TypPoistenia.ZIADNE;
    }

    public TypPoistenia getTypPoistenia() {
        return poistenie;
    }

    public void setTypPoistenia(TypPoistenia poistenie) {
        this.poistenie = poistenie;
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

    public int getRizikovaSkupinaCislom() {
        return rizikovaSkupinaCislom;
    }

    public void setRizikovaSkupina(RizikovaSkupina rizikovaSkupina) {
        if (rizikovaSkupina == RizikovaSkupina.PRVA) {
            this.rizikovaSkupinaCislom = 1;
        }
        if (rizikovaSkupina == RizikovaSkupina.DRUHA) {
            this.rizikovaSkupinaCislom = 2;
        }
        if (rizikovaSkupina == RizikovaSkupina.TRETIA) {
            this.rizikovaSkupinaCislom = 3;
        }
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

    public int getPoistnaSuma() {
        return poistnaSuma;
    }

    public void setPoistnaSuma(int suma) {
        this.poistnaSuma = suma;
    }

    public boolean[] getPripoistenia() {
        return pripoistenia;
    }

    public boolean getPripoistenia(int index) {
        return pripoistenia[index];
    }
    
    public String getNazvyPripoisteni(int index){
        if(index >= nazvyPripoisteni.length)
            return "N/A";
        return nazvyPripoisteni[index];
    }

    public void setPripoistenia(boolean[] pripoistenia) {
        this.pripoistenia = pripoistenia;
    }

    public void setPripoistenia(int index, boolean stav) {
        this.pripoistenia[index] = stav;
    }

    public List<Poistovna> getPoistovne() {
        return poistovne;
    }

    public void setPoistovne(List<Poistovna> poistovne) {
        this.poistovne = poistovne;
    }

}
