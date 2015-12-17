package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
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

    private boolean vyplnenyFormular;

    private class ZakladneUdaje extends JPanel {

        private JLabel rokNarodeniaLabel = new JLabel("Rok narodenia:");
        private JLabel dobaPoisteniaLabel = new JLabel("Doba poistenia:");
        private JLabel vyberRizikoLabel = new JLabel("Riziková skupina:");
        private JLabel vyberPracaLabel = new JLabel("Pracovný pomer:");
        private JLabel poistnaSumaLabel = new JLabel("Poistná suma:");
        private JLabel rokNarodeniaPomoc = new JLabel("?");
        private JLabel dobaPoisteniaPomoc = new JLabel("?");

        private JTextField rokNarodeniaText = new JTextField("");
        private JTextField dobaPoisteniaText = new JTextField("");
        private JTextField mesacnyPrijemText = new JTextField("");
        private JTextField poistnaSumaText = new JTextField("");

        private ButtonGroup skupinaRiziko = new ButtonGroup();
        private ButtonGroup skupinaPraca = new ButtonGroup();

        private JRadioButton rizikoRadio1 = new JRadioButton("1");
        private JRadioButton rizikoRadio2 = new JRadioButton("2");
        private JRadioButton rizikoRadio3 = new JRadioButton("3");

        private JRadioButton zamestnanyRadio = new JRadioButton("zamestnaný");
        private JRadioButton nezamestnanyRadio = new JRadioButton("nezamestnaný");
        private JRadioButton szcoRadio = new JRadioButton("SZCO");

        public ZakladneUdaje() {
            // [fill, grow][labely][fill, grow][textFieldy][pomocnikovia][fill, grow]
            setLayout(new MigLayout("", "[fill, grow][][fill, grow][][][fill, grow]"));
            setBackground(Color.white);

            ActionListener pracovnyPomerListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (zamestnanyRadio.isSelected()) {
                        mesacnyPrijemText.setText("");
                        add(mesacnyPrijemText, "cell 3 5");
                    }
                    if (szcoRadio.isSelected()) {
                        mesacnyPrijemText.setText("");
                        add(mesacnyPrijemText, "cell 3 7");
                    }
                    if (nezamestnanyRadio.isSelected()) {
                        remove(mesacnyPrijemText);
                    }
                    updateUI();
                }
            };

            try {
                BufferedImage imageLogo = ImageIO.read(this.getClass().getResourceAsStream("/obrazky/pomoc.png"));
                rokNarodeniaPomoc = new JLabel(new ImageIcon(imageLogo));
                dobaPoisteniaPomoc = new JLabel(new ImageIcon(imageLogo));
            } catch (Exception ex) {
                //Logger.getLogger(LogoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            rokNarodeniaPomoc.setToolTipText("<html> min. " + Verifier.VEK_MINIMALNY + " <br>max. " + Verifier.VEK_MAXIMALNY + " rokov</html>");
            dobaPoisteniaPomoc.setToolTipText("<html> min. " + Verifier.DOBA_POISTENIA_MINIMALNA + " <br>max. " + Verifier.DOBA_POISTENIA_MAXIMALNA + "</html>");

            rokNarodeniaText.setMinimumSize(new Dimension(200, 23));
            dobaPoisteniaText.setMinimumSize(new Dimension(200, 23));
            mesacnyPrijemText.setMinimumSize(new Dimension(100, 23));
            poistnaSumaText.setMinimumSize(new Dimension(200, 23));

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

            zamestnanyRadio.addActionListener(pracovnyPomerListener);
            nezamestnanyRadio.addActionListener(pracovnyPomerListener);
            szcoRadio.addActionListener(pracovnyPomerListener);

            add(rokNarodeniaLabel, "cell 1 0");
            add(rokNarodeniaText, "cell 3 0");
            add(rokNarodeniaPomoc, "cell 4 0");

            add(dobaPoisteniaLabel, "cell 1 1");
            add(dobaPoisteniaText, "cell 3 1");
            add(dobaPoisteniaPomoc, "cell 4 1");

            add(vyberRizikoLabel, "cell 1 2");
            add(rizikoRadio1, "cell 3 2");
            add(rizikoRadio2, "cell 3 3");
            add(rizikoRadio3, "cell 3 4");

            add(vyberPracaLabel, "cell 1 5");
            add(zamestnanyRadio, "cell 3 5");
            add(mesacnyPrijemText, "cell 3 5");
            add(nezamestnanyRadio, "cell 3 6");
            add(szcoRadio, "cell 3 7");

            add(poistnaSumaLabel, "cell 1 8");
            add(poistnaSumaText, "cell 3 8");
        }

        public void zablokuj() {
            rokNarodeniaText.setEnabled(false);
            dobaPoisteniaText.setEnabled(false);
            mesacnyPrijemText.setEnabled(false);
            poistnaSumaText.setEnabled(false);
            rizikoRadio1.setEnabled(false);
            rizikoRadio2.setEnabled(false);
            rizikoRadio3.setEnabled(false);
            zamestnanyRadio.setEnabled(false);
            nezamestnanyRadio.setEnabled(false);
            szcoRadio.setEnabled(false);
        }

        public boolean skontroluj() {
            boolean stav = true;

            if (!Verifier.skontrolujVek(rokNarodeniaText.getText())) {
                // vypis chybu
                System.out.println("vek");
                stav = false;
            }
            if (!Verifier.skontrolujDobuPoistenia(dobaPoisteniaText.getText())) {
                // vypis chybu
                System.out.println("doba");
                stav = false;
            }
            if ((zamestnanyRadio.isSelected() || szcoRadio.isSelected()) && !Verifier.skontrolujPlat(mesacnyPrijemText.getText())) {
                // vypis chybu
                System.out.println("plat");
                stav = false;
            }
            if (!Verifier.skontrolujPoistnuSumu(poistnaSumaText.getText())) {
                // vypis chybu
                System.out.println("suma");
                stav = false;
            }

            if (stav) {
                Manager.INSTANCE.setRokNarodenia(Integer.valueOf(rokNarodeniaText.getText()));
                Manager.INSTANCE.setDobaPoistenia(Integer.valueOf(dobaPoisteniaText.getText()));
                Manager.INSTANCE.setPoistnaSuma(Integer.valueOf(poistnaSumaText.getText()));

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
                    Manager.INSTANCE.setMesacnyPrijem(Integer.valueOf(mesacnyPrijemText.getText()));
                }
            }

            return stav;
        }

    }

    private class VyberPripoisteniaAktivator extends JPanel {

        private JLabel tlacidlo = new JLabel();
        private JLabel oznam = new JLabel("Vyberte si pripoistenie");

        private JLabel error = new JLabel("Nesprávne vyplnené údaje!");

        public VyberPripoisteniaAktivator() {
            // [tlacidlo][fill, grow][label][fill, grow][error][fill, grow]
            setLayout(new MigLayout("", "[][fill, grow][][fill, grow][][fill, grow]", "[]"));
            setBackground(Color.white);

            try {
                BufferedImage imageDown = ImageIO.read(this.getClass().getResource("/obrazky/dole.png"));
                tlacidlo = new JLabel(new ImageIcon(imageDown));
            } catch (Exception ex) {
                //Logger.getLogger(FormularPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            error.setForeground(Color.red);
            error.setVisible(false);

            add(tlacidlo, "cell 0 0");
            add(oznam, "cell 2 0");
            add(error, "cell 4 0");

            MouseListener listener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (zakladneUdaje.skontroluj()) {
                        ukazError(false);
                        ukazPripoistenie();
                    } else {
                        ukazError(true);
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
                    GuiFactory.INSTANCE.zmenKurzor(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    GuiFactory.INSTANCE.zmenKurzor(false);
                }
            };
            addMouseListener(listener);
            for (int i = 0; i < getComponentCount(); i++) {
                getComponent(i).addMouseListener(listener);
            }
        }

        public void ukazError(boolean stav) {
            error.setVisible(stav);
        }
    }

    private class VyberPripoistenia extends JPanel {

        private JCheckBox[] polePripoistenia = new JCheckBox[7];

        private JCheckBox smrtUrazomBtn = new JCheckBox("smrť spôsobená úrazom");
        private JCheckBox trvaleNasledkyBtn = new JCheckBox("trvalé následky úrazu");
        private JCheckBox trvaleNasledkyProgBtn = new JCheckBox("trvalé následky úrazu s progresívnym plnením");
        private JCheckBox nevyhnutnaLiecbaBtn = new JCheckBox("denné odškodné za nevyhnutnú liečbu úrazu");
        private JCheckBox praceneschopnostBtn = new JCheckBox("denná dávka v prípade práceneschopnosti ");
        private JCheckBox hospitalizaciaBtn = new JCheckBox("denná dávka v prípade hospitalizácie");
        private JCheckBox kritickeChorobyBtn = new JCheckBox("kritické choroby");

        public VyberPripoistenia() {
            // [fill, grow][checkboxy][fill, grow]
            setLayout(new MigLayout("", "[fill, grow][][fill, grow]"));
            setBackground(Color.white);

            ActionListener trvaleNasledkyListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (trvaleNasledkyBtn.getText().equals(e.getActionCommand())) {
                        trvaleNasledkyProgBtn.setSelected(false);
                    }
                    if (trvaleNasledkyProgBtn.getText().equals(e.getActionCommand())) {
                        trvaleNasledkyBtn.setSelected(false);
                    }
                }
            };

            trvaleNasledkyBtn.addActionListener(trvaleNasledkyListener);
            trvaleNasledkyProgBtn.addActionListener(trvaleNasledkyListener);
            /*
             * [smrtUrazom, trvaleNasledky, trvaleNasledkyProg, nevyhnutnaLiecba, 
             *   praceneschopnost, hospitalizacia, kritickeChoroby]
             */
            polePripoistenia[0] = smrtUrazomBtn;
            polePripoistenia[1] = trvaleNasledkyBtn;
            polePripoistenia[2] = trvaleNasledkyProgBtn;
            polePripoistenia[3] = nevyhnutnaLiecbaBtn;
            polePripoistenia[4] = praceneschopnostBtn;
            polePripoistenia[5] = hospitalizaciaBtn;
            polePripoistenia[6] = kritickeChorobyBtn;

            for (JCheckBox pripBtn : polePripoistenia) {
                pripBtn.setOpaque(false);
            }
        }

        public void priradPripoistenia() {
            int index = 0;

            for (int i = 0; i < polePripoistenia.length; i++) {
                if (Verifier.pridatPripoistenie(i)) {
                    add(polePripoistenia[i], "cell 1 " + index);
                    index++;
                }
            }

        }

        public void potvrdPripoistenia() {
            boolean[] pole = new boolean[polePripoistenia.length];
            for (int i = 0; i < polePripoistenia.length; i++) {
                pole[i] = polePripoistenia[i].isSelected();
            }
            Manager.INSTANCE.setPripoistenia(pole);
        }

    }

    public void zmazFormular() {
        panel.remove(zakladneUdaje);
        panel.remove(vyberPripoistenia);
        zakladneUdaje = new ZakladneUdaje();
        vyberPripoistenia = new VyberPripoistenia();
        Manager.TypPoistenia pomocnyTyp = Manager.INSTANCE.getTypPoistenia();
        Manager.INSTANCE.reset();
        Manager.INSTANCE.setTypPoistenia(pomocnyTyp);
        panel.add(zakladneUdaje, "cell 0 0");
        panel.updateUI();
        vyberPripoisteniaAktivator.ukazError(false);
        vyplnenyFormular = false;
    }

    public void ukazPripoistenie() {
        if (!vyplnenyFormular) {
            zakladneUdaje.zablokuj();
            vyberPripoistenia.priradPripoistenia();
            panel.add(vyberPripoistenia, "cell 0 2");
            panel.updateUI();
            vyplnenyFormular = true;
        }
    }

    public boolean potvrd() {
        if (vyplnenyFormular) {
            vyberPripoistenia.potvrdPripoistenia();
            return true;
        } else {
            if (zakladneUdaje.skontroluj()) {
                vyberPripoistenia.potvrdPripoistenia();
                return true;
            }
        }
        vyberPripoisteniaAktivator.ukazError(true);
        return false;
    }

    public FormularPanel() {
        setBorder(null);
        getVerticalScrollBar().setUnitIncrement(4);
        setViewportView(panel);

        panel.setLayout(new MigLayout("", "[fill, grow]", ""));
        panel.setBackground(Color.white);

        panel.add(zakladneUdaje, "cell 0 0");
        panel.add(vyberPripoisteniaAktivator, "cell 0 1");
    }
}
