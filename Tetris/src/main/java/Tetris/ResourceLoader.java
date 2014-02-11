package Tetris;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ResourceLoader {

    public ResourceLoader() {
    }

    public Font loadEmbeddedFont(String fontFileName) {

        InputStream stream = this.getClass().getResourceAsStream(fontFileName);
        Font font = null;

        if (stream == null) {
            System.out.println("Error! Embedded font file " + fontFileName + " could not be located! Quitting!");
            System.exit(0);
        }

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, stream);
        }
        catch (IOException e) {
            System.out.println("An error occurred when trying to read embedded font file " + fontFileName + "! Quitting!");
            System.exit(0);
        }
        catch (FontFormatException e) {
            System.out.println("Error! Format of the embedded font file " + fontFileName + " could not be recognized! Quitting!");
            System.exit(0);
        }

        try {
            stream.close();
        }
        catch (IOException e) {
            System.out.println("Failed to close an input stream!");
        }

        font = font.deriveFont(Font.BOLD, 18f);
        return font;
    }

    public BufferedImage loadEmbeddedImage(String imageFileName, int imageWidth, int imageHeight) {

        InputStream stream = this.getClass().getResourceAsStream(imageFileName);
        BufferedImage img1 = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        BufferedImage img2 = null;

        if (stream == null) {
            System.out.println("Error! Embedded image file " + imageFileName + " could not be located! Quitting!");
            System.exit(0);
        }

        try {
            img2 = ImageIO.read(stream);
        }
        catch (IOException e) {
            System.out.println("An error occurred when trying to read embedded image file " + imageFileName + "! Quitting!");
            System.exit(0);
        }
        if (img2 == null) {
            System.out.println("An error occurred when trying to decode embedded image file " + imageFileName + "! Quitting!");
            System.exit(0);
        }

        try {
            stream.close();
        }
        catch (IOException e) {
            System.out.println("Failed to close an input stream!");
        }

        img1.createGraphics().drawImage(img2, 0, 0, null);
        return img1;
    }
}
