package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.SwiPoistovna.Verifier;
import sk.upjs.ics.SwiPoistovna.Manager;

public class FormularPanel extends JScrollPane {

    private JPanel panel = new JPanel();

    private ZakladneUdaje zakladneUdaje = new ZakladneUdaje();
    private VyberPripoisteniaAktivator vyberPripoisteniaAktivator = new VyberPripoisteniaAktivator();
    private VyberPripoistenia vyberPripoistenia = new VyberPripoistenia();

    private class ZakladneUdaje extends JPanel {

        private JLabel rokNarodeniaLabel = new JLabel("Rok narodenia:");
        private JLabel dobaPoisteniaLabel = new JLabel("Doba poistenia:");
        private JLabel vyberRizikoLabel = new JLabel("Rizikova skupina:");
        private JLabel vyberPracaLabel = new JLabel("Pracovny pomer:");

        private JTextField rokNarodeniaText = new JTextField();
        private JTextField dobaPoisteniaText = new JTextField();
        private JTextField mesacnyPrijemText = new JTextField();

        private ButtonGroup skupinaRiziko = new ButtonGroup();
        private ButtonGroup skupinaPraca = new ButtonGroup();

        private JRadioButton rizikoRadio1 = new JRadioButton("1");
        private JRadioButton rizikoRadio2 = new JRadioButton("2");
        private JRadioButton rizikoRadio3 = new JRadioButton("3");

        private JRadioButton zamestnanyRadio = new JRadioButton("zamestnany");
        private JRadioButton nezamestnanyRadio = new JRadioButton("nezamestnany");
        private JRadioButton szcoRadio = new JRadioButton("SZCO");

        public ZakladneUdaje() {
            // [oddelovac1][labely][oddelovac2][textFieldy][fill, grow]
            setLayout(new MigLayout("", "[][][][][fill, grow]"));
            setPreferredSize(new Dimension(500, 70));
            setBackground(Color.cyan);

            ActionListener pracaListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (zamestnanyRadio.isSelected()) {
                        mesacnyPrijemText.setText("");
                        add(mesacnyPrijemText, "cell 3 5");
                        updateUI();
                    } else {
                        remove(mesacnyPrijemText);
                        updateUI();
                    }
                }
            };

            JPanel oddelovac1 = new JPanel();
            oddelovac1.setPreferredSize(new Dimension(80, 1));
            oddelovac1.setBackground(Color.red);
            JPanel oddelovac2 = new JPanel();
            oddelovac2.setPreferredSize(new Dimension(80, 1));
            oddelovac2.setBackground(Color.red);

            rokNarodeniaText.setMinimumSize(new Dimension(200, 23));
            dobaPoisteniaText.setMinimumSize(new Dimension(200, 23));
            mesacnyPrijemText.setMinimumSize(new Dimension(100, 23));

            skupinaRiziko.add(rizikoRadio1);
            skupinaRiziko.add(rizikoRadio2);
            skupinaRiziko.add(rizikoRadio3);

            rizikoRadio1.setSelected(true);
            rizikoRadio1.setOpaque(false);
            rizikoRadio2.setOpaque(false);
            rizikoRadio3.setOpaque(false);

            skupinaPraca.add(zamestnanyRadio);
            skupinaPraca.add(nezamestnanyRadio);
            skupinaPraca.add(szcoRadio);

            zamestnanyRadio.setSelected(true);
            zamestnanyRadio.setOpaque(false);
            nezamestnanyRadio.setOpaque(false);
            szcoRadio.setOpaque(false);

            zamestnanyRadio.addActionListener(pracaListener);
            nezamestnanyRadio.addActionListener(pracaListener);
            szcoRadio.addActionListener(pracaListener);

            add(oddelovac1, "cell 0 0");
            add(rokNarodeniaLabel, "cell 1 0");
            add(oddelovac2, "cell 2 0");
            add(rokNarodeniaText, "cell 3 0");

            add(dobaPoisteniaLabel, "cell 1 1");
            add(dobaPoisteniaText, "cell 3 1");

            add(vyberRizikoLabel, "cell 1 2");
            add(rizikoRadio1, "cell 3 2");
            add(rizikoRadio2, "cell 3 3");
            add(rizikoRadio3, "cell 3 4");

            add(vyberPracaLabel, "cell 1 5");
            add(zamestnanyRadio, "cell 3 5");
            add(mesacnyPrijemText, "cell 3 5");
            add(nezamestnanyRadio, "cell 3 6");
            add(szcoRadio, "cell 3 7");
        }

        public void zablokuj() {
            rokNarodeniaText.setEnabled(false);
            dobaPoisteniaText.setEnabled(false);
            mesacnyPrijemText.setEnabled(false);
            rizikoRadio1.setEnabled(false);
            rizikoRadio2.setEnabled(false);
            rizikoRadio3.setEnabled(false);
            zamestnanyRadio.setEnabled(false);
            nezamestnanyRadio.setEnabled(false);
            szcoRadio.setEnabled(false);
        }

        public boolean skontroluj() {
            boolean stav = true;

            if (Verifier.skontrolujVek(rokNarodeniaText.getText())) {
                // vypis chybu
                stav = false;
            }
            if (Verifier.skontrolujDobuPoistenia(dobaPoisteniaText.getText())) {
                // vypis chybu
                stav = false;
            }
            if (zamestnanyRadio.isSelected() && Verifier.skontrolujPlat(mesacnyPrijemText.getText())) {
                // vypis chybu
                stav = false;
            }

            if (stav) {
                Manager.INSTANCE.setRokNarodenia(Integer.valueOf(rokNarodeniaText.getText()));
                Manager.INSTANCE.setDobaPoistenia(Integer.valueOf(dobaPoisteniaText.getText()));

                if (rizikoRadio1.isSelected()) {
                    Manager.INSTANCE.setRizikovaSkupina(Manager.RizikovaSkupina.PRVA);
                }
                if (rizikoRadio2.isSelected()) {
                    Manager.INSTANCE.setRizikovaSkupina(Manager.RizikovaSkupina.DRUHA);
                }
                if (rizikoRadio3.isSelected()) {
                    Manager.INSTANCE.setRizikovaSkupina(Manager.RizikovaSkupina.TRETIA);
                }

                if (zamestnanyRadio.isSelected()) {
                    Manager.INSTANCE.setPracovnyPomer(Manager.PracovnyPomer.ZAMESTNANY);
                    Manager.INSTANCE.setMesacnyPrijem(Integer.valueOf(mesacnyPrijemText.getText()));
                }
                if (nezamestnanyRadio.isSelected()) {
                    Manager.INSTANCE.setPracovnyPomer(Manager.PracovnyPomer.NEZAMESTNANY);
                }
                if (szcoRadio.isSelected()) {
                    Manager.INSTANCE.setPracovnyPomer(Manager.PracovnyPomer.SZCO);
                }

            }

            return stav;
        }

    }

    private class VyberPripoisteniaAktivator extends JPanel {

        private JLabel oznam = new JLabel("Vyberte si pripoistenie");
        private JLabel error = new JLabel("Nevyplnili ste povinne udaje!");

        public VyberPripoisteniaAktivator() {
            try {
                // [tlacidlo][fill, grow][label][fill, grow][error][fill, grow]
                setLayout(new MigLayout("", "[][fill, grow][][fill, grow][][fill, grow]", "[]"));
                setPreferredSize(new Dimension(500, 40));
                setBackground(Color.cyan);

                BufferedImage imageDown = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\swi_dole.bmp"));
                JLabel logo = new JLabel(new ImageIcon(imageDown));
                add(logo, "cell 0 0");

                error.setVisible(false);

                add(oznam, "cell 2 0");
                add(error, "cell 4 0");

                MouseListener listener = new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (zakladneUdaje.skontroluj()) {
                            error.setVisible(false);
                            ukazPripoistenie();
                        } else {
                            error.setVisible(true);
                        }

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
                        // nepotrebna metoda
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // nepotrebna metoda
                    }
                };

                addMouseListener(listener);
                for (int i = 0; i < getComponentCount(); i++) {
                    getComponent(i).addMouseListener(listener);
                }

            } catch (IOException ex) {
                Logger.getLogger(FormularPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class VyberPripoistenia extends JPanel {

        private List<JRadioButton> poleRadio = new ArrayList<>();

        private ButtonGroup skupinaTrvaleNasledky = new ButtonGroup();

        private JRadioButton smrtUrazomRadio = new JRadioButton("smrt sposobena urazom");
        private JRadioButton trvaleNasledkyRadio = new JRadioButton("trvale nasledky urazu");
        private JRadioButton trvaleNasledkyProgRadio = new JRadioButton("trvale nasledky urazu s progresivnym plnenim");
        private JRadioButton nevyhLiecbaRadio = new JRadioButton("denne odskodne za nevyhnutnu liecbu urazu");
        private JRadioButton praceneschRadio = new JRadioButton("denna davka v pripade praceneschopnosti ");
        private JRadioButton hospitRadio = new JRadioButton("denna davka v pripade hospitalizacie");
        private JRadioButton kritickeChorobyRadio = new JRadioButton("kriticke choroby");

        public VyberPripoistenia() {
            setLayout(new MigLayout("", "[][][fill, grow]"));
            setPreferredSize(new Dimension(500, 70));
            setBackground(Color.cyan);

            JPanel oddelovac = new JPanel();
            oddelovac.setPreferredSize(new Dimension(80, 1));
            oddelovac.setBackground(Color.red);

            skupinaTrvaleNasledky.add(trvaleNasledkyRadio);
            skupinaTrvaleNasledky.add(trvaleNasledkyProgRadio);

            poleRadio.add(smrtUrazomRadio);
            poleRadio.add(trvaleNasledkyRadio);
            poleRadio.add(trvaleNasledkyProgRadio);
            poleRadio.add(nevyhLiecbaRadio);
            poleRadio.add(praceneschRadio);
            poleRadio.add(hospitRadio);
            poleRadio.add(kritickeChorobyRadio);

            for (JRadioButton radioBtn : poleRadio) {
                radioBtn.setOpaque(false);
            }

            add(oddelovac, "cell 0 0");

            /*
             *for (int i = 0; i < poleRadio.size(); i++) {
             *add(poleRadio.get(i), "cell 1 " + i);
             *}
             */
        }

        public void priradPripoistenia() {
            int index = 0;

            if (Verifier.pridatSmrtUrazom()) {
                add(smrtUrazomRadio, "cell 1 " + index);
                index++;
            }

            if (Verifier.pridatTrvaleNasledky()) {
                add(trvaleNasledkyRadio, "cell 1 " + index);
                index++;
            }

            if (Verifier.pridatTrvaleNasledkyProg()) {
                add(trvaleNasledkyProgRadio, "cell 1 " + index);
                index++;
            }

            if (Verifier.pridatNevyhnutnaLiecba()) {
                add(nevyhLiecbaRadio, "cell 1 " + index);
                index++;
            }

            if (Verifier.pridatPraceneschopnost()) {
                add(praceneschRadio, "cell 1 " + index);
                index++;
            }

            if (Verifier.pridatHospitalizacia()) {
                add(hospitRadio, "cell 1 " + index);
                index++;
            }

            if (Verifier.pridatKritickeChoroby()) {
                add(kritickeChorobyRadio, "cell 1 " + index);
                index++;
            }
        }
        /*
         * public void zablokuj() {
         *    for (JRadioButton radioBtn : poleRadio) {
         *        radioBtn.setEnabled(false);
         *     }
         *  }
         */
    }

    public void zmazFormular() {
        panel.remove(zakladneUdaje);
        panel.remove(vyberPripoistenia);
        zakladneUdaje = new ZakladneUdaje();
        vyberPripoistenia = new VyberPripoistenia();
        Manager.INSTANCE.reset();
        panel.add(zakladneUdaje, "cell 0 0");
        panel.updateUI();
    }

    public void ukazPripoistenie() {
        zakladneUdaje.zablokuj();
        vyberPripoistenia.priradPripoistenia();
        panel.add(vyberPripoistenia, "cell 0 2");
        panel.updateUI();
    }

    public FormularPanel() {
        setBorder(null);
        getVerticalScrollBar().setUnitIncrement(4);
        setViewportView(panel);

        panel.setLayout(new MigLayout("", "[fill, grow]", ""));
        panel.setBackground(Color.yellow);

        panel.add(zakladneUdaje, "cell 0 0");
        panel.add(vyberPripoisteniaAktivator, "cell 0 1");
    }
}
