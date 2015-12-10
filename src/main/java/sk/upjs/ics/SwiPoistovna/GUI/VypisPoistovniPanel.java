package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;
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
            // [text][suma][fill, grow][text][suma][fill, grow]
            setLayout(new MigLayout("", "[fill, grow][fill, grow][fill, grow][fill, grow][fill, grow][fill, grow]"));
            setBackground(Color.white);

            JLabel nazov = new JLabel(poistovna.getNazov());

            JTextArea textMesacna = new JTextArea("mesacna suma:");
            JTextArea textStvrtrocna = new JTextArea("stvrtrocna suma:");
            JTextArea textPolrocna = new JTextArea("polrocna suma:");
            JTextArea textRocna = new JTextArea("rocna suma:");

            JTextArea cenaMesacna = new JTextArea(poistovna.getCenaMesacna().toString());
            JTextArea cenaStvrtrocna = new JTextArea(poistovna.getCenaStvrtRocna().toString());
            JTextArea cenaPolrocna = new JTextArea(poistovna.getCenaPolRocna().toString());
            JTextArea cenaRocna = new JTextArea(poistovna.getCenaRocna().toString());

            add(nazov, "cell 0 0");

            add(textMesacna, "cell  0 1");
            add(cenaMesacna, "cell  1 1");
            add(new Panel(), "cell 2 1");

            add(textStvrtrocna, "cell  3 1");
            add(cenaStvrtrocna, "cell  4 1");
            add(new Panel(), "cell 5 1");

            add(textPolrocna, "cell  0 2");
            add(cenaPolrocna, "cell  1 2");
            add(new Panel(), "cell 2 2");

            add(textRocna, "cell  3 2");
            add(cenaRocna, "cell  4 2");
            add(new Panel(), "cell 5 2");
        }

    }

    private JPanel panel = new JPanel();

    private List<Poistovna> poistovne = new ArrayList<>();

    public VypisPoistovniPanel() {
        panel.setLayout(new MigLayout("", "[fill, grow]"));
        panel.setBackground(Color.white);

        poistovne = Manager.INSTANCE.getPoistovne();

        for (Poistovna poistovna : poistovne) {
            VypisPoistovne vypisPoistovne = new VypisPoistovne(poistovna);
            panel.add(vypisPoistovne, "wrap");
        }

        setViewportView(panel);
        setBorder(null);
        getVerticalScrollBar().setUnitIncrement(4);
    }

}
