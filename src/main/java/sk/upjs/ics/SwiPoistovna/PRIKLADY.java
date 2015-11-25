package sk.upjs.ics.SwiPoistovna;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.SwiPoistovna.DAO.DBInsurightDAO;
import sk.upjs.ics.SwiPoistovna.DAO.DaoFactory;


public class PRIKLADY {

    private static DBInsurightDAO poistenieDao = DaoFactory.INSTANCE.getInsurightDAO();

    public static void main(String[] args) {
        //heslo na openVpn mam take jak aj na s.ics.upjs...kvrastiak
        if(poistenieDao.testDb())System.out.println("Podarilo sa pripojit k databaze...");
        else System.out.println("NEPODARILO sa pripojit k databaze...");
        /*List<Poistovna> aa = poistenieDao.getPoistovne();
        System.out.println(aa.get(0).getNazov());*/

        /*Priklad ako vypocitat poistenia pre tieto parametre:*/
        Poistenie p  =  new Poistenie(1000);
        Manager.INSTANCE.setPracovnyPomer(Manager.PracovnyPomer.ZAMESTNANY);
        Manager.INSTANCE.setDobaPoistenia(15);;
        Manager.INSTANCE.setMesacnyPrijem(1000);
        Manager.INSTANCE.setTypPoistenia(Manager.TypPoistenia.KLESAJUCA_SUMA);
        Manager.INSTANCE.setRizikovaSkupina(Manager.RizikovaSkupina.PRVA);
        Manager.INSTANCE.setRokNarodenia(1990);
        Manager.INSTANCE.setPripoistenia(0, true);
        Manager.INSTANCE.setPripoistenia(6, true);
        
        List<Poistovna> vyslednePoistovneKtoreBudemePorovnavat = p.vypocitajCeny();
        for(Poistovna poi: vyslednePoistovneKtoreBudemePorovnavat){
            System.out.println("Poistovna "+poi.getNazov()+" rocna cena:");
            System.out.println(poi.getCenaRocna());
        }
        
        
        
        /*System.out.println(Verifier.pridatNevyhnutnaLiecba("1969"));
         Manager.INSTANCE.setDobaPoistenia(55);
         Manager.INSTANCE.setRizikovaSkupina(Manager.RizikovaSkupina.PRVA);
         Manager.INSTANCE.setRokNarodenia(1980);
         Manager.INSTANCE.setPripoistenia(1, true);
         System.out.println(Verifier.pridatPripoistenie(2));*/
    }

}
