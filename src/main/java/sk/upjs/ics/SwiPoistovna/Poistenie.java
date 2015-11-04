package sk.upjs.ics.SwiPoistovna;

import java.math.BigDecimal;

/**
 *
 * @author Kristian
 */
public class Poistenie {
    private int id;
    private String nazov;
    private int dlzkaPoistenia;
    private int zadanaSuma;
    private BigDecimal cena;

    public BigDecimal vypocitajCenu(){
        //TODO
        return BigDecimal.ZERO;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getDlzkaPoistenia() {
        return dlzkaPoistenia;
    }

    public void setDlzkaPoistenia(int dlzkaPoistenia) {
        this.dlzkaPoistenia = dlzkaPoistenia;
    }

    public int getZadanaSuma() {
        return zadanaSuma;
    }

    public void setZadanaSuma(int zadanaSuma) {
        this.zadanaSuma = zadanaSuma;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }
    
    
    
}
