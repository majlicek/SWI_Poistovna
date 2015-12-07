package sk.upjs.ics.SwiPoistovna.GUI;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

public class VypisPoisteniPanel extends JScrollPane {

    private JPanel panel = new JPanel();

    public VypisPoisteniPanel() {
        panel.setLayout(new MigLayout("", "[fill, grow]"));
        panel.setBackground(Color.green);

        for (int i = 0; i < 3; i++) {
            JPanel pan = new JPanel();
            pan.setBackground(Color.yellow);
            pan.setPreferredSize(new Dimension(500, 70));
            panel.add(pan, "wrap");
        }

        setViewportView(panel);
        setBorder(null);
        getVerticalScrollBar().setUnitIncrement(4);
    }

}
