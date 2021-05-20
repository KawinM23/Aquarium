package manager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DrawManager {
	public static void drawImage(GraphicsContext gc, Image image, double positionX, double positionY) {
		gc.drawImage(image, positionX, positionY);
	}

	public static void drawImageFixSize(GraphicsContext gc, Image image, double positionX, double positionY,
			double width, double height) {

		// image, x ,y, width, height
		gc.drawImage(image, positionX, positionY, width, height);
	}

	public static Image cropImage(GraphicsContext gc, Image image, int positionX, int positionY, int width,
			int height) {
		WritableImage croppedImage = new WritableImage(image.getPixelReader(), positionX, positionY, width, height);
		return (Image) croppedImage;
	}

	public static void drawText(GraphicsContext gc, String text, int fontSize, int positionX, int positionY,
			int width) {
		// Set line width
		gc.setLineWidth(2);
		// Set fill color
		gc.setFill(Color.LIMEGREEN);
		gc.setStroke(Color.BLACK);
		// set font
		Font theFont = Font.font("Comic Sans MS", FontWeight.LIGHT, fontSize);
		gc.setFont(theFont);
		// text,x,y,maxWidth - maximum width the text string can have.
		gc.fillText(text, positionX, positionY, width);

	}

	public static void drawOval(GraphicsContext gc, int posX, int posY, int width, int height) {
		gc.setLineWidth(2);
		gc.setFill(Color.rgb(77,52,178));
		// x,y,w,h
		gc.fillOval(posX, posY, width, height);

	}
}
