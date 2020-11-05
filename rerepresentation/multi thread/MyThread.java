import java.awt.image.BufferedImage;
import java.awt.Color;

public class MyThread extends Thread{
	/*
	Martin Antonio Vivanco Palacios
	A01701167

	*/
	private int begin, rows, cols;
	private BufferedImage img;

	public MyThread(int rows, int cols, int begin, BufferedImage img){
		this.begin = begin;
		this.rows = rows;
		this.cols = cols;
		this.img = img;
	}
	public void run() {
		/*
		go through all the cols and the specified rows to change the colour of the matrix
		in those points
		*/
		for (int i = begin; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
						int rgb = img.getRGB(i, j);  // rgb contian all number coded within a single integer concatenaed as red/green/blue

						//use this separation to explore with different filters and effects  you need to do the invers process to encode red green blue into a single value again
						//separation of each number
						int red = rgb & 0xFF;  // & uses  0000000111 with the rgb number to eliminate all the bits but the las 3 (which represent 8 position which are used for 0 to 255 values)
						int green = (rgb >> 8) & 0xFF;  // >> Bitwise shifts 8 positions  & makes  uses  0000000111 with the number and eliminates the rest
						int blue = (rgb >> 16) & 0xFF; // >> Bitwise shifts 16 positions  & makes  uses  0000000111 with the number and eliminates the rest

						red = (int) Math.pow(red,(double) 90/100);
						green = (int) Math.pow(green,(double) 90/100);
						blue = (int) Math.pow(blue,(double) 90/100);
						//System.out.println(red);
						//System.out.println(green);
						//System.out.println(blue);

						int color = new Color(red,green,blue).getRGB();

						img.setRGB(i, j, color); // sets the pixeles to specified color
				}
		}
	}
}
