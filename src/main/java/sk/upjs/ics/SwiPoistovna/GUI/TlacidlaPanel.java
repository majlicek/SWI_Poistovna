package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class TlacidlaPanel extends JPanel {

    JLabel domov;
    JLabel zrus;
    JLabel ok;

    public TlacidlaPanel() {
        try {
            setLayout(new MigLayout("", "[][fill, grow][][]", "[]"));
            setBackground(Color.white);

            BufferedImage imageHome = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\swi_domov.bmp"));
            domov = new JLabel(new ImageIcon(imageHome));
            add(domov);
            domov.addMouseListener(new MouseListener() {

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
            });

            add(new JLabel());

            BufferedImage imageCancel = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\swi_zrus.bmp"));
            zrus = new JLabel(new ImageIcon(imageCancel));
            add(zrus);
            zrus.addMouseListener(new MouseListener() {

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
            });

            BufferedImage imageOk = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\swi_ok.bmp"));
            ok = new JLabel(new ImageIcon(imageOk));
            add(ok);
            ok.addMouseListener(new MouseListener() {

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
            });

        } catch (IOException ex) {
            Logger.getLogger(TlacidlaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void KlikNaDomov() {
        GuiFactory.INSTANCE.tlacidloDomov();
    }

    private void KlikNaZrus() {
        GuiFactory.INSTANCE.tlacidloZrus();
    }

    private void KlikNaOk() {
        GuiFactory.INSTANCE.tlacidloOk();
    }

}
