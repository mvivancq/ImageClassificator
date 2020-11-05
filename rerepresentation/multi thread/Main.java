import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main{
  private static final int MAXTHREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws IOException {
        /*
        Martin Antonio Vivanco Palacios a01701167
        read the image cols and rows that constitute it and then
        divide it into the number of available availableProcessors
        then assign an interval to each thread, start the thread, wait
        for them and rewrite the image
        */
        BufferedImage img = ImageIO.read(new File(args[0]));
        int numCols = img.getHeight();
        int numRows = img.getWidth();
        MyThread threads[] = new MyThread[MAXTHREADS];

        for (int i = 0; i < threads.length; i++) {
          threads[i] = new MyThread((i+1)*(numRows/threads.length), numCols, i*(numRows/threads.length), img);
        }

        double startTime = System.currentTimeMillis();
  			for (int i = 0; i < threads.length; i++) {
  				threads[i].start();
  			}

  			for (int i = 0; i < threads.length; i++) {
  				try {
  					threads[i].join();
  				} catch (InterruptedException e) {
  					e.printStackTrace();
  				}
  			}
  			double stopTime = System.currentTimeMillis();
  			double acum =  (stopTime - startTime);
        System.out.println("Elapsed Time: "+acum);
        ImageIO.write(img, "jpg", new File("newimage.jpg"));
        System.out.print("Finished");
    }
}
