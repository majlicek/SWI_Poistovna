package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.SwiPoistovna.Manager;

public class VyberPoisteniaPanel extends JScrollPane {

    String lorem = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna. ";

    private class SmrtSFixnouSumouMoznost extends JPanel {
        
        private String nadpisText = "POISTENIE S FIXNOU SUMOU";
        private String obsahText = lorem;

        public SmrtSFixnouSumouMoznost() {
            setLayout(new MigLayout("", "[fill, grow]"));
            setBackground(Color.white);
            setBorder(new BevelBorder(2));

            JLabel nadpis = new JLabel(nadpisText);
            JLabel obsah = new JLabel("<html><body style='width: " + 500 + "px'>" + obsahText + "</body></lorem>");

            add(nadpis, "cell 0 0");
            add(obsah, "cell  0 1 ");
        }

        public void in() {
            setBorder(new BevelBorder(1));
        }

        public void out() {
            setBorder(new BevelBorder(2));
        }
    }

    private class SmrtSKlesajucouSumouMoznost extends JPanel {

        private String nadpisText = "POISTENIE S KLESAJUCOU SUMOU";
        private String obsahText = lorem;

        public SmrtSKlesajucouSumouMoznost() {
            setLayout(new MigLayout("", "[fill, grow]"));
            setBackground(Color.white);
            setBorder(new BevelBorder(2));

            JLabel nadpis = new JLabel(nadpisText);
            JLabel obsah = new JLabel("<html><body style='width: " + 500 + "px'>" + obsahText + "</body></lorem>");

            add(nadpis, "cell 0 0");
            add(obsah, "cell  0 1 ");
        }

        public void in() {
            setBorder(new BevelBorder(1));
        }

        public void out() {
            setBorder(new BevelBorder(2));
        }
    }

    private JPanel panel = new JPanel();

    SmrtSFixnouSumouMoznost fixnaSumaMoznost = new SmrtSFixnouSumouMoznost();
    SmrtSKlesajucouSumouMoznost klesajucaSumaMoznost = new SmrtSKlesajucouSumouMoznost();

    public VyberPoisteniaPanel() {
        panel.setLayout(new MigLayout("", "[fill, grow]"));
        panel.setBackground(Color.white);

        panel.add(fixnaSumaMoznost, "cell 0 0");
        panel.add(klesajucaSumaMoznost, "cell 0 1");

        MouseListener fixnaSumaMoznostListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                GuiFactory.INSTANCE.getInsuright().zmenNaFormular();
                Manager.INSTANCE.setPoistenie(Manager.Poistenie.FIXNA_SUMA);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // nepotrebna metoda
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // nepotrebna metoda
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fixnaSumaMoznost.in();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fixnaSumaMoznost.out();
            }
        };
        fixnaSumaMoznost.addMouseListener(fixnaSumaMoznostListener);
        for (int i = 0; i < fixnaSumaMoznost.getComponentCount(); i++) {
            fixnaSumaMoznost.getComponent(i).addMouseListener(fixnaSumaMoznostListener);
        }

        MouseListener klesajucaSumaMoznostListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                GuiFactory.INSTANCE.getInsuright().zmenNaFormular();
                Manager.INSTANCE.setPoistenie(Manager.Poistenie.KLESAJUCA_SUMA);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // nepotrebna metoda
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // nepotrebna metoda
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                klesajucaSumaMoznost.in();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                klesajucaSumaMoznost.out();
            }
        };
        klesajucaSumaMoznost.addMouseListener(klesajucaSumaMoznostListener);
        for (int i = 0; i < klesajucaSumaMoznost.getComponentCount(); i++) {
            klesajucaSumaMoznost.getComponent(i).addMouseListener(klesajucaSumaMoznostListener);
        }

        setViewportView(panel);
        setBorder(null);
        getVerticalScrollBar().setUnitIncrement(4);
    }

}
