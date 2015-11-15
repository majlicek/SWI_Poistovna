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
import net.miginfocom.swing.MigLayout;

public class TlacidlaPanel extends JPanel {

    MouseListener domovListener;
    MouseListener infoListener;
    MouseListener zrusListener;
    MouseListener okListener;

    JLabel domov = new JLabel("Domov");
    JLabel info = new JLabel("Info");
    JLabel zrus = new JLabel("Zrus");
    JLabel ok = new JLabel("OK");

    public TlacidlaPanel() {

        setLayout(new MigLayout("", "[][][fill, grow][][]", "[]"));
        setBackground(Color.white);

        domovListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                KlikNaDomov();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        infoListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                KlikNaInfo();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        zrusListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                KlikNaZrus();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        okListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                KlikNaOk();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        try {
            BufferedImage imageDomov = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\domov.png"));
            domov = new JLabel(new ImageIcon(imageDomov));
        } catch (Exception ex) {
            System.out.println("nenacitane tlacidlo Domov");
            //Logger.getLogger(TlacidlaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            BufferedImage imageInfo = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\info.png"));
            info = new JLabel(new ImageIcon(imageInfo));
        } catch (Exception ex) {
            System.out.println("nenacitane tlacidlo Info");
            //Logger.getLogger(TlacidlaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            BufferedImage imageZrus = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\zrus.png"));
            zrus = new JLabel(new ImageIcon(imageZrus));
        } catch (Exception ex) {
            System.out.println("nenacitane tlacidlo Zrus");
            //Logger.getLogger(TlacidlaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            BufferedImage imageOk = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\ok.png"));
            ok = new JLabel(new ImageIcon(imageOk));
        } catch (Exception ex) {
            System.out.println("nenacitane tlacidlo Ok");
            //Logger.getLogger(TlacidlaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        domov.addMouseListener(domovListener);
        info.addMouseListener(infoListener);

        add(domov, "cell 0 0");
        add(info, "cell 1 0");
        add(new JLabel(), "cell 2 0");
        add(zrus, "cell 3 0");
        add(ok, "cell 4 0");
    }

    private void KlikNaDomov() {
        GuiFactory.INSTANCE.tlacidloDomov();
    }

    private void KlikNaInfo() {
        GuiFactory.INSTANCE.tlacidloInfo();
    }

    private void KlikNaZrus() {
        GuiFactory.INSTANCE.tlacidloZrus();
    }

    private void KlikNaOk() {
        GuiFactory.INSTANCE.tlacidloOk();
    }

    public void FunkcneTlacidla(boolean stav) {
        if (stav) {
            zrus.addMouseListener(zrusListener);
            ok.addMouseListener(okListener);
        } else {
            zrus.removeMouseListener(zrusListener);
            ok.removeMouseListener(okListener);
        }
    }
}
