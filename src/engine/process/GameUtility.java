package engine.process;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameUtility {

    public static BufferedImage readImage(String filePath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(filePath));
        }
        catch (IOException e) {
            System.err.println("-- Can not read the image file : " + filePath + " --");
        }

        return image;
    }
}