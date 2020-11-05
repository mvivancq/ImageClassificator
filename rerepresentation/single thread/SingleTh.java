/*
 *		Example file for Java Lab 3
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SingleTh {

    public static void main(String[] args) throws IOException {

        BufferedImage img = ImageIO.read(new File(args[0]));
        int numRows = img.getHeight();
        int numCols = img.getWidth();
        double startTime = System.currentTimeMillis();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int rgb = img.getRGB(j, i);  // rgb contian all number coded within a single integer concatenaed as red/green/blue

                //use this separation to explore with different filters and effects  you need to do the invers process to encode red green blue into a single value again
                //separation of each number
                int red = rgb & 0xFF;  // & uses  0000000111 with the rgb number to eliminate all the bits but the las 3 (which represent 8 position which are used for 0 to 255 values)
                int green = (rgb >> 8) & 0xFF;  // >> Bitwise shifts 8 positions  & makes  uses  0000000111 with the number and eliminates the rest
                int blue = (rgb >> 16) & 0xFF; // >> Bitwise shifts 16 positions  & makes  uses  0000000111 with the number and eliminates the rest

                //rgb = ~rgb; // ~ negation of the complete pixel value

                float L = (float) (0.2126 * (float) red + 0.7152 * (float) green + 0.0722 * (float) blue);
                // (234, 176, 3) // yellow

                int color;
                color = 234 * (int) L / 255;
                color = (color << 8) | 176 * (int) L / 255;
                color = (color << 8) | 3 * (int) L / 255;

                img.setRGB(j, i, color); // sets the pixeles to specified color  (negative image)

            }
        }
        double stopTime = System.currentTimeMillis();
  			double acum =  (stopTime - startTime);
        System.out.println("Elapsed Time: "+acum);
        ImageIO.write(img, "png", new File("newimage.png"));
        System.out.print("Finished");
    }
}
