package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.SwiPoistovna.Manager;
import sk.upjs.ics.SwiPoistovna.Poistovna;

public class VypisPoistovniPanel extends JScrollPane {

    private class VypisPoistovne extends JPanel {

        public VypisPoistovne(Poistovna poistovna) {
            // [logo][fill, grow][text][suma][fill, grow][text][suma][fill, grow]
            setLayout(new MigLayout("", "[][fill, grow][fill, grow][fill, grow][fill, grow][fill, grow][fill, grow][fill, grow]", "[fill, grow][fill, grow][fill, grow]"));
            setBackground(Color.white);

            JLabel logo = new JLabel();
            try {
                BufferedImage imageLogo = ImageIO.read(new File("src//main//java//sk//upjs//ics//SwiPoistovna//GUI//obrazky//poistovne//" + poistovna.getNazov().toLowerCase() + ".png"));
                logo = new JLabel(new ImageIcon(imageLogo));
            } catch (Exception ex) {
                //Logger.getLogger(LogoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            JTextArea textMesacna = new JTextArea("mesačná suma:");
            JTextArea textStvrtrocna = new JTextArea("štvrťročná suma:");
            JTextArea textPolrocna = new JTextArea("polročná suma:");
            JTextArea textRocna = new JTextArea("ročná suma:");

            JLabel nazov;
            JTextArea cenaMesacna;
            JTextArea cenaStvrtrocna;
            JTextArea cenaPolrocna;
            JTextArea cenaRocna;

            nazov = new JLabel(poistovna.getNazov());

            cenaMesacna = new JTextArea(poistovna.getCenaMesacna().toString() + " €");
            cenaStvrtrocna = new JTextArea(poistovna.getCenaStvrtRocna().toString() + " €");
            cenaPolrocna = new JTextArea(poistovna.getCenaPolRocna().toString() + " €");
            cenaRocna = new JTextArea(poistovna.getCenaRocna().toString() + " €");

            cenaMesacna.setFocusable(false);
            cenaMesacna.setEditable(false);
            cenaStvrtrocna.setFocusable(false);
            cenaStvrtrocna.setEditable(false);
            cenaPolrocna.setFocusable(false);
            cenaPolrocna.setEditable(false);
            cenaRocna.setFocusable(false);
            cenaRocna.setEditable(false);

            textMesacna.setFocusable(false);
            textMesacna.setEditable(false);
            textStvrtrocna.setFocusable(false);
            textStvrtrocna.setEditable(false);
            textPolrocna.setFocusable(false);
            textPolrocna.setEditable(false);
            textRocna.setFocusable(false);
            textRocna.setEditable(false);

            add(logo, "cell 0 0 1 3");
            add(nazov, "cell 2 0");

            add(textMesacna, "cell  2 1");
            add(cenaMesacna, "cell  3 1");
            add(new Panel(), "cell 4 1");

            add(textStvrtrocna, "cell  5 1");
            add(cenaStvrtrocna, "cell  6 1");
            add(new Panel(), "cell 7 1");

            add(textPolrocna, "cell  2 2");
            add(cenaPolrocna, "cell  3 2");
            add(new Panel(), "cell 4 2");

            add(textRocna, "cell  5 2");
            add(cenaRocna, "cell  6 2");
            add(new Panel(), "cell 7 2");
        }

    }

    private class Nacitavanie extends JPanel {

        public Nacitavanie() {
            //[fill, grow][nacitavanie][fill, grow]
            setLayout(new MigLayout("", "[fill, grow][][fill, grow]", "[fill, grow][][fill, grow]"));
            setBackground(Color.white);

            JLabel image = new JLabel("ČAKAJTE PROSÍM...");
            try {
                ImageIcon imageIcon = new ImageIcon("src//main//java//sk//upjs//ics//SwiPoistovna//GUI//obrazky//nacitavanie.gif");
                image = new JLabel(imageIcon);
            } catch (Exception ex) {
                //Logger.getLogger(LogoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            add(image, "cell 1 1");
        }
    }

    private class ZiadnaPoistovna extends JPanel {

        public ZiadnaPoistovna() {
            // [fill, grow][text 1/2][text 2/2][fill, grow]
            setLayout(new MigLayout("", "[fill, grow][][][fill, grow]", "[fill, grow][fill, grow]"));
            setBackground(Color.white);

            JLabel lutujeme = new JLabel("ĽUTUJEME");
            JTextArea text = new JTextArea("Vašim požiadavkám nevyhovuje žiadna poisťovňa.");
            text.setFocusable(false);
            text.setEditable(false);

            add(lutujeme, "cell 1 0 2 1");
            add(text, "cell 1 1 2 1");
        }

    }

    private JPanel panel = new JPanel();

    private Nacitavanie nacitavanie;

    private ZiadnaPoistovna ziadnaPoistovna = new ZiadnaPoistovna();

    private List<Poistovna> poistovne = new ArrayList<>();

    public VypisPoistovniPanel() {
        panel.setLayout(new MigLayout("", "[fill, grow]"));
        panel.setBackground(Color.white);

        nacitavanie = new Nacitavanie();
        panel.add(nacitavanie);

        setViewportView(panel);
        setBorder(null);
        getVerticalScrollBar().setUnitIncrement(4);
    }

    public void vypisVysledok() {
        panel.remove(nacitavanie);

        poistovne = Manager.INSTANCE.getPoistovne();

        if (poistovne.isEmpty()) {
            panel.add(ziadnaPoistovna);
        } else {
            for (Poistovna poistovna : poistovne) {
                VypisPoistovne vypisPoistovne = new VypisPoistovne(poistovna);
                panel.add(vypisPoistovne, "wrap");
            }
        }
        panel.updateUI();
    }

}
