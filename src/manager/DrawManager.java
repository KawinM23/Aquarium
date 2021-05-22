package manager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DrawManager {
	
	private static final Image tank1Bg = new Image(ClassLoader.getSystemResource("Tank1.png").toString());
	private static final Image tank2Bg = new Image(ClassLoader.getSystemResource("Tank2.jpg").toString());
	private static final Image tank3Bg = new Image(ClassLoader.getSystemResource("Tank3.jpg").toString());
	private static final Image tank4Bg = new Image(ClassLoader.getSystemResource("Tank4.jpg").toString());

	private static boolean justOpened = true;
	
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

	public static void drawStatsText(GraphicsContext gc, String text, int fontSize, int positionX, int positionY,
			int width) {
		// Set line width
		gc.setLineWidth(2);
		// Set fill color
		gc.setFill(Color.MOCCASIN);
		gc.setStroke(Color.BLACK);
		// set font
		Font theFont = Font.font("Comic Sans MS", FontWeight.LIGHT, fontSize);
		gc.setFont(theFont);
		// text,x,y,maxWidth - maximum width the text string can have.
		gc.fillText(text, positionX, positionY, width);

	}

	public static void drawOval(GraphicsContext gc, int posX, int posY, int width, int height) {
		gc.setLineWidth(2);
		gc.setFill(Color.rgb(77, 52, 178));
		// x,y,w,h
		gc.fillOval(posX, posY, width, height);

	}

	// Set background to BLACK
	public static void resetBackGround(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	// Set background to an image
	public static void setBackGroundImage(GraphicsContext gc, String image_path) {
		Image image = new Image(image_path);
		gc.drawImage(image, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	public static void setTankBg(GraphicsContext gc, int tankNumber) {
		switch (tankNumber) {
		case 1:
			gc.drawImage(tank1Bg, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			break;
		case 2:
			gc.drawImage(tank2Bg, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			break;
		case 3:
			gc.drawImage(tank3Bg, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			break;
		case 4:
			gc.drawImage(tank4Bg, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			break;
		default:
		}
	}

	public static void drawGuraGreeting(GraphicsContext gc, String key) {
		String speech = "Welcome, " + JSONManager.getPlayerName() + "!";
		switch (key) {
		case "MainMenu":
			break;
		case "Credits":
			// Sleeping is also important na
			speech = "They're awesome!";
			break;
		case "Tank":
			speech = "Choose a level!";
			break;
		case "Statistics":
			speech = "I hate numbers...";
			break;
		default:
		}

		String quote = "\" " + speech + " \"";
		String quoteBy = "- Gawr Gura 2021";
		drawStatsText(gc, quote, 40, 15, 539, 420);
		drawStatsText(gc, quoteBy, 20, 190, 580, 240);
		justOpened=false;
	}
}
