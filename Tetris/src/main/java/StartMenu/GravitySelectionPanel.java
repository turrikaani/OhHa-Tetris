package StartMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GravitySelectionPanel extends JPanel {

    public GravitySelectionPanel() {
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 13f));
        graphics.drawString("On higher gravity levels the tetrominoes fall", 8, 20);
        graphics.drawString("more quickly, but more points is awarded for", 8, 36);
        graphics.drawString("dropping them and clearing lines.", 8, 52);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 14f));
        graphics.drawString("Select gravity level:", 8, 84);
    }
}
