package sk.upjs.ics.SwiPoistovna;

import java.math.BigDecimal;

/**
 *
 * @author Kristian
 */
public class Poistovna {
    private int id;
    private String nazov;
    
    public BigDecimal vypocitajPonuku(){
        //TODO
        return BigDecimal.ZERO;
    }
    
    public BigDecimal vypocitajPonukuMesacne(){
        //TODO
        return BigDecimal.ZERO;
    }
    
    public BigDecimal vypocitajStvrtrocne(){
        //TODO
        return BigDecimal.ZERO;
    }
    
    public BigDecimal vypocitajPolrocne(){
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
    
    
}
