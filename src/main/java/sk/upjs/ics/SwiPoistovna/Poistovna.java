package sk.upjs.ics.SwiPoistovna;

import java.math.BigDecimal;

/**
 *
 * @author Kristian
 */
public class Poistovna {
    private int id;
    private String Nazov;
    private BigDecimal cenaRocna;
    private BigDecimal cenaStvrtRocna;
    private BigDecimal cenaPolRocna;
    private BigDecimal cenaMesacna;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazov() {
        return Nazov;
    }

    public void setNazov(String nazov) {
        this.Nazov = nazov;
    }

    
    /**
     *
     * Vrati rocny poplatok vypocitany pre dane Poistenie.
     * 
     * @return {@code BigDecimal} , hodnota zaokruhlena na 6 desatinych miest
     */
    public BigDecimal getCenaRocna() {
        return cenaRocna;
    }

    protected void setCenaRocna(BigDecimal cenaRocna) {
        this.cenaRocna = cenaRocna;
    }

    /**
     *
     * Vrati stvrtrocny poplatok vypocitany pre dane Poistenie.
     * 
     * @return {@code BigDecimal} , hodnota zaokruhlena na 6 desatinych miest
     */
    public BigDecimal getCenaStvrtRocna() {
        return cenaStvrtRocna;
    }

    protected void setCenaStvrtRocna(BigDecimal cenaStvrtRocna) {
        this.cenaStvrtRocna = cenaStvrtRocna;
    }

    /**
     *
     * Vrati polrocny poplatok vypocitany pre dane Poistenie.
     * 
     * @return {@code BigDecimal} , hodnota zaokruhlena na 6 desatinych miest
     */
    public BigDecimal getCenaPolRocna() {
        return cenaPolRocna;
    }

    protected void setCenaPolRocna(BigDecimal cenaPolRocna) {
        this.cenaPolRocna = cenaPolRocna;
    }

    /**
     *
     * Vrati mesacny poplatok vypocitany pre dane Poistenie.
     * 
     * @return {@code BigDecimal} , hodnota zaokruhlena na 6 desatinych miest
     */
    public BigDecimal getCenaMesacna() {
        return cenaMesacna;
    }

    protected void setCenaMesacna(BigDecimal cenaMesacna) {
        this.cenaMesacna = cenaMesacna;
    }
    
    
    
}
