package Tetris.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TetrisGUI implements Runnable {

    private JFrame window;
    private BufferedImage backBuffer;
    private CustomPanel frontBuffer;
    private Graphics2D painter;

    public TetrisGUI() {
    }

    public void run() {

        this.window = new JFrame("jTetris 1.0");
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.setResizable(false);

        this.backBuffer = new BufferedImage(960, 640, BufferedImage.TYPE_INT_BGR);
        this.frontBuffer = new CustomPanel(this.backBuffer);

        this.frontBuffer.setPreferredSize(new Dimension(960, 640));
        this.frontBuffer.setBackground(Color.BLACK);
        this.frontBuffer.setDoubleBuffered(false);

        this.window.getContentPane().add(this.frontBuffer);
        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);

        this.painter = this.backBuffer.createGraphics();
    }

    public Graphics2D getPainter() {
        return this.painter;
    }

    public CustomPanel getFrontBuffer() {
        return this.frontBuffer;
    }

    public void addKeyListener(KeyListener kbListener) {
        this.window.addKeyListener(kbListener);
    }

    public void closeWindow() {
        window.dispose();
    }
}
