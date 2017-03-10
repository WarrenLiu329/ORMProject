import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class VisualTester extends PApplet {
	ArrayList<PImage> images;
	PImage current_image;
	int currentImageIndex = 0;
	int w = 1200;
	int h = 900;

	public void setup() {
		size(w, h);
		images = PDFHelper.getPImagesFromPdf("/omrtest.pdf");
	}

	public void draw() {
		background(255);
		if (images.size() > 0) {
			current_image = images.get(currentImageIndex);
			image(current_image, 0, 0); // display image i
			textSize(50);
			int myColor = color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
			fill(0);
			// rect(3, 259, 40, 40);
			text(mouseX + " " + mouseY, 30, 30);
			noFill();

			stroke(0);
			strokeWeight(2);
			// int width = 200;
			// int height = 37;
			// int topCornerX = 114;
			// int topCornerY = 462;
			// int xIncrement = width + 83;
			// int section = width / 5;
			// for (int i = 0; i < 4; i++) {
			// for (int j = 0; j < 12; j++) {
			// int rectY = topCornerY + (j * height);
			// rect(topCornerX + (xIncrement * i), rectY, width, height);
			// for (int s = 1; s < 5; s++) {
			// line(topCornerX + (section * s) + (xIncrement * i), rectY,
			// topCornerX + (section * s) + (xIncrement * i), rectY + height);
			// }
			// }
			// }
			int color = OpticalMarkReader.getPixelAt(mouseX, mouseY, current_image);
			text(color, mouseX, mouseY + 40);
		}
	}

	public void mouseReleased() {
		currentImageIndex = (currentImageIndex + 1) % images.size(); // increment
																		// current
																		// image
	}
}
