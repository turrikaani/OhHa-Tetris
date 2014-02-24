package StartMenu;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GravitySelectionPanel extends JPanel {

    public GravitySelectionPanel() {
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 14f));
        graphics.drawString("jTetris 1.0", 20, 20);
    }
}
