package StartMenu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {

    private Image mainMenuBackground;

    public MainMenuPanel() {
        ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/images/startscreen.png"));
        mainMenuBackground = imgIcon.getImage();
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        graphics.drawImage(mainMenuBackground, 0, 0, null);
    }
}
