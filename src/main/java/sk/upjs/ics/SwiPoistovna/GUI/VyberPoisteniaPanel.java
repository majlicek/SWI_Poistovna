package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.SwiPoistovna.Manager;

public class VyberPoisteniaPanel extends JScrollPane {

    String lorem = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna. ";

    private class SmrtSFixnouSumouMoznost extends JPanel {

        private String nadpisText = "POISTENIE S FIXNOU SUMOU";
        private String obsahText = "Suma bude vyplatená v prípade smrti počas trvania poistnej zmluvy.";

        public SmrtSFixnouSumouMoznost() {
            setLayout(new MigLayout("", "[fill, grow]"));
            setBackground(Color.white);
            setBorder(new BevelBorder(2));

            JLabel nadpis = new JLabel(nadpisText);
            JTextArea obsah = new JTextArea(obsahText);
            obsah.setWrapStyleWord(true);
            obsah.setLineWrap(true);
            obsah.setEditable(false);
            obsah.setFocusable(false);

            add(nadpis, "cell 0 0");
            add(obsah, "cell  0 1");
        }

        public void in() {
            setBorder(new BevelBorder(1));
        }

        public void out() {
            setBorder(new BevelBorder(2));
        }
    }

    private class SmrtSKlesajucouSumouMoznost extends JPanel {

        private String nadpisText = "POISTENIE S KLESAJÚCOU SUMOU";
        private String obsahText = "Suma bude rovnomerne klesať počas celej doby poistenia, ale výška mesačného poistného sa nemení. Klesajúca suma je vhodná, ak máte hypotéku a chcete, aby poistka kryla len zostatok hypotéky a iných úverov.";

        public SmrtSKlesajucouSumouMoznost() {
            setLayout(new MigLayout("", "[fill, grow]"));
            setBackground(Color.white);
            setBorder(new BevelBorder(2));

            JLabel nadpis = new JLabel(nadpisText);
            JTextArea obsah = new JTextArea(obsahText);
            obsah.setWrapStyleWord(true);
            obsah.setLineWrap(true);
            obsah.setEditable(false);
            obsah.setFocusable(false);

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

    private class PodporovanePoistovne extends JPanel {

        public PodporovanePoistovne() {
            setLayout(new MigLayout("", "[fill, grow][fill, grow][fill, grow]"));
            setBackground(Color.white);

            JLabel text = new JLabel("Podporované poisťovne:");

            add(text, "cell 0 0");

            String[] poistovne = GuiFactory.INSTANCE.getPodporovanePoistovne();
            int x = 3;
            int y = 0;

            for (String s : poistovne) {

                JLabel label = new JLabel(s);
                try {
                    BufferedImage imageLogo = ImageIO.read(new File("src//main//java//sk//upjs//ics//SwiPoistovna//GUI//obrazky//poistovne//" + s.toLowerCase() + ".png"));
                    label = new JLabel(new ImageIcon(imageLogo));
                } catch (Exception ex) {
                    //Logger.getLogger(LogoPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                add(label, "cell " + y % 3 + " " + x / 3);
                x++;
                y++;

            }

        }

    }

    private JPanel panel = new JPanel();

    SmrtSFixnouSumouMoznost fixnaSumaMoznost = new SmrtSFixnouSumouMoznost();
    SmrtSKlesajucouSumouMoznost klesajucaSumaMoznost = new SmrtSKlesajucouSumouMoznost();

    public VyberPoisteniaPanel() {
        panel.setLayout(new MigLayout("", "[fill, grow]", "[][][fill, grow][]"));
        panel.setBackground(Color.white);

        panel.add(fixnaSumaMoznost, "cell 0 0");
        panel.add(klesajucaSumaMoznost, "cell 0 1");

        JPanel pomocny = new JPanel();
        pomocny.setBackground(Color.white);

        panel.add(pomocny, "cell 0 2");
        panel.add(new PodporovanePoistovne(), "cell 0 3");

        MouseListener fixnaSumaMoznostListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                GuiFactory.INSTANCE.zmenKurzor(false);
                GuiFactory.INSTANCE.getInsuright().zmenNaFormular();
                Manager.INSTANCE.setTypPoistenia(Manager.TypPoistenia.FIXNA_SUMA);
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
                GuiFactory.INSTANCE.zmenKurzor(true);
                fixnaSumaMoznost.in();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                GuiFactory.INSTANCE.zmenKurzor(false);
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
                GuiFactory.INSTANCE.zmenKurzor(false);
                GuiFactory.INSTANCE.getInsuright().zmenNaFormular();
                Manager.INSTANCE.setTypPoistenia(Manager.TypPoistenia.KLESAJUCA_SUMA);
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
                GuiFactory.INSTANCE.zmenKurzor(true);
                klesajucaSumaMoznost.in();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                GuiFactory.INSTANCE.zmenKurzor(false);
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
