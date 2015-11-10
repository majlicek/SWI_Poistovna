package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
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

public class LogoPanel extends JPanel {

    public LogoPanel() {
        try {
            setLayout(new MigLayout("", "[][fill, grow]", "[]"));
            setBackground(Color.white);

            BufferedImage imageLogo = ImageIO.read(new File("src\\main\\java\\sk\\upjs\\ics\\SwiPoistovna\\GUI\\obrazky\\swi_logo.bmp"));
            JLabel logo = new JLabel(new ImageIcon(imageLogo));
            add(logo);
        } catch (IOException ex) {
            Logger.getLogger(LogoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
