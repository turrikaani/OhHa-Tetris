package Tetris.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {

    private BufferedImage backBuffer;

    public CustomPanel(BufferedImage b) {
        this.backBuffer = b;
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.backBuffer, 0, 0, null);
    }
}
