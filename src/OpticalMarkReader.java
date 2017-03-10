import java.util.ArrayList;

import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */
public class OpticalMarkReader {

	/***
	 * Method to do optical mark reading on page image. Return an AnswerSheet
	 * object representing the page answers.
	 * 
	 * @param image
	 * @return
	 */
	public AnswerSheet processPageImage(PImage image) {
		image.filter(PImage.GRAY);
		System.out.println("imageLength: " + image.width + "imageHeight: " + image.height);
		// calculate a starting point and predetermine distances between bubbles
		// Location start = calcStartingPoint(image);
		Location start = new Location(110, 462);
	//	AnswerSheet sheet = new AnswerSheet();

		int[][] pixels = getTwoDimArray(image);
		int numBubbles = 5;
		int bubbleWidth = 200 / numBubbles;
		int bubbleHeight = 37;
		int distBetweenCol = 83;
		int stringLength = (image.width - start.getX())/200;
		int stringHeight = image.height-start.getY();

		String[][] answers = new String[stringLength][];
		for (int j = start.getY(); j < image.width; j += bubbleWidth + distBetweenCol) {
			for (int i = start.getX(); i < image.height; i += bubbleHeight) {
				//System.out.println("(" + i + ", "+ j +  ")");
				int bubbleIndex = determineBubble(i, j, bubbleWidth, bubbleHeight, numBubbles, pixels);
				//answers.add(determineAnswer(bubbleIndex));
			}
		}
		return null;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param image
	 * @return the grey-color value between 0 and 255
	 */
	public int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();
		int index = row * image.width + col;
		int color = image.pixels[index];
		return color & 255;
	}

	public int[][] getTwoDimArray(PImage image) {
		int[][] pixels = new int[image.height][image.width];
		for (int r = 0; r < image.height; r++) {
			for (int c = 0; c < image.width; c++) {
				pixels[r][c] = getPixelAt(r, c, image);
			}
		}

		return pixels;
	}

	// public Location calcStartingPoint(PImage image) {

	// }

	// public int determineBubble(int r, int c, int width, int height, int
	// numBubbles, int[][] pixels) {
	// int darkestIndex = 0;
	// int darkestVal = 255;
	// int index = 0;
	// for (int col = c; col < pixels[0].length - width; col += pixels[0].length
	// / numBubbles) {
	// int sum = 0;
	// for (int i = r; i < (pixels.length / numBubbles) * r; i++) {
	// for (int j = col; j < (pixels[0].length / numBubbles) * col; j++) {
	// sum += pixels[i][j];
	//
	// }
	// if (sum < darkestVal) {
	// darkestIndex = index;
	// darkestVal = sum;
	// }
	// }
	//
	// index++;
	// }
	//
	// return darkestIndex;
	// }

	public int determineBubble(int r, int c, int width, int height, int numBubbles, int[][] pixels) {
		int boxWidth = width / numBubbles;
		int darkestVal = 255;
		int maxBubble = 0;
		for (int i = 0; i < numBubbles; i++) {
			int value = getSumValue(r, c + i * boxWidth, width, height, pixels);
			//System.out.println(i + ": " + value);
			if (value < darkestVal) {
				darkestVal = value;
				maxBubble = i;
			}
		}

		return maxBubble;
	}

	public int getSumValue(int r, int c, int width, int height, int[][] pixels) {
		int sum = 0;
	//	System.out.println("length: " + pixels.length + " height: " + pixels[0].length);
	//	System.out.println("x: " + r + " y: " + c);
		for (int i = r; i < height; i++) {
			for (int j = c; j < width; j++) {
				System.out.println(pixels[i][j]);
				sum += pixels[i][j];
				
				//System.out.println(sum);
			}
		}

		return sum ;
	}

	public String determineAnswer(int i) {
		if (i == 0) {
			return "A";
		} else if (i == 1) {
			return "B";
		} else if (i == 2) {
			return "C";
		} else if (i == 3) {
			return "D";
		} else if (i == 4) {
			return "E";
		}
		return null;

	}

}
