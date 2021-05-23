package manager;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DrawManager {

	private static final Image TANK1_BG = new Image(ClassLoader.getSystemResource("Tank1.png").toString());
	private static final Image TANK2_BG = new Image(ClassLoader.getSystemResource("Tank2.jpg").toString());
	private static final Image TANK3_BG = new Image(ClassLoader.getSystemResource("Tank3.jpg").toString());
	private static final Image TANK4_BG = new Image(ClassLoader.getSystemResource("Tank4.jpg").toString());

	private static boolean justOpened = true;

	private static String warningText1 = "WARNING! MONSTERS ARE COMING";
	private static String warningText2 = "WARNING! BEWARE THE MONSTERS";
	private static String warningText3 = "WARNING! INVASION IS HAPPENING";
	private static String warningText4 = "WARNING! YOUR FISH ARE IN DANGER";
	private static String chosenWarningText;

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
			gc.drawImage(TANK1_BG, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			break;
		case 2:
			gc.drawImage(TANK2_BG, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			break;
		case 3:
			gc.drawImage(TANK3_BG, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			break;
		case 4:
			gc.drawImage(TANK4_BG, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			break;
		default:
		}
	}

	public static void resetWarning() {
		chosenWarningText = null;
	}

	public static void drawWarning(GraphicsContext gc) {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		if (chosenWarningText == null) {
			switch (randomNum) {
			case 1:
				chosenWarningText = warningText1;
				break;
			case 2:
				chosenWarningText = warningText2;
				break;
			case 3:
				chosenWarningText = warningText3;
				break;
			case 4:
				chosenWarningText = warningText4;
				break;
			default:
			}
		}

		gc.setLineWidth(2);
		// Set fill color
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		// set font
		Font theFont = Font.font("Comic Sans MS", FontWeight.BOLD, 50);
		gc.setFont(theFont);
		// text,x,y,maxWidth - maximum width the text string can have.
		gc.fillText(chosenWarningText, 155, 200, 650);

	}

	public static void drawGuraGreeting(GraphicsContext gc, String key) {
		String speech = "Welcome, " + JSONManager.getPlayerName() + "!";
		int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
		if (key.substring(0, 4).equals("Tank")) key = "Tank";
		switch (key) {
		case "MainMenu":
			if (randomNum<=2 && !justOpened) {
				speech = "A";
			}
			break;
		case "Credits":
			if (randomNum <= 3) {
				speech = "Sleeping is also important na";
			} else if (randomNum <= 6) {
				speech = "I smell a SHRIMP in here...";
			} else if (randomNum <= 10) {
				speech = "They're awesome!";
			}
			break;
		case "Tank":
			if (randomNum <= 7) {
				speech = "Choose a level!";
			} else if (randomNum <= 10) {
				speech = "Play something!";
			}
			break;
		case "Statistics":
			if (randomNum <= 5) {
				speech = "I hate numbers...";
			} else if (randomNum <= 10) {
				speech = "Wow! I can't read Maths";
			}
			break;
		default:
		}

		String quote = "\" " + speech + " \"";
		String quoteBy = "- Gawr Gura 2021";
		drawStatsText(gc, quote, 40, 15, 539, 420);
		drawStatsText(gc, quoteBy, 20, 190, 580, 240);
		justOpened = false;
	}

	public static void setJustOpened() {
		justOpened = true;
	}
}
