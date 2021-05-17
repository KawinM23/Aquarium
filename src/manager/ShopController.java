package manager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class ShopController {
	// TODO get money from Money class
	static final String GUPPY_IMAGE_PATH = ClassLoader.getSystemResource("Guppy.png").toString();
	static int[] prices;
	Image[] images;
	static String[] imagePaths;

	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	private static final double[][] buttonDetail = { { 22.0, 3.0, 72.0, 46.0, 100.0, 20.0 },
			{ 91.0, 3.0, 141.0, 46.0, 80.0, 20.0 }, { 148.0, 3.0, 198.0, 46.0, 80.0, 20.0 },
			{ 221.0, 3.0, 271.0, 46.0, 100.0, 20.0 }, { 295.0, 3.0, 344.0, 46.0, 80.0, 20.0 },
			{ 367.0, 3.0, 417.0, 46.0, 80.0, 20.0 }, { 440.0, 3.0, 490.0, 46.0, 80.0, 20.0 },
			{ 532.0, 3.0, 617.0, 25.0, 80.0, 20.0 } };

	public static void setShopDetail(int tankNumber) {
		switch (tankNumber) {
		case 0:
			prices = new int[] { 0, 10, 100, 1000, 100000, 0, 0, 0 };
			imagePaths = new String[] { GUPPY_IMAGE_PATH, GUPPY_IMAGE_PATH, GUPPY_IMAGE_PATH, GUPPY_IMAGE_PATH,
					GUPPY_IMAGE_PATH, GUPPY_IMAGE_PATH, GUPPY_IMAGE_PATH, GUPPY_IMAGE_PATH };
			break;
		case 1:
			break;
		case 2:
			prices = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			break;
		case 3:
			prices = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			break;
		default:
		}
	}

	// TODO Add All button
	public static void addAllButtons(AnchorPane anchorpane) {
		for (int i = 1; i <= 8; i++) {
			addButton(anchorpane, i);
		}
	}

	public static void addButton(AnchorPane anchorpane, int buttonNumber) {
		String buttonText = "Button " + buttonNumber;
		Button button = new Button(buttonText);
		button.setPrefSize((buttonDetail[buttonNumber - 1][2] - buttonDetail[buttonNumber - 1][0]) * 1.5,
				(buttonDetail[buttonNumber - 1][3] - buttonDetail[buttonNumber - 1][1]) * 1.5);
		button.setStyle("-fx-background-radius: " + buttonDetail[buttonNumber - 1][4] + "px;"
				+ "-fx-border-color: transparent;" + "-fx-background-color: transparent;" + "-fx-text-fill: null");
//		button.setStyle("-fx-background-radius: " + buttonDetail[buttonNumber][4] + "px;");
//			button.setBorder(null);
//			button.setBackground(null);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

			}
		});

		AnchorPane.setTopAnchor(button, buttonDetail[buttonNumber - 1][1] * 1.5);
		AnchorPane.setLeftAnchor(button, buttonDetail[buttonNumber - 1][0] * 1.5);

		button.hoverProperty().addListener((event) -> {

		});

		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

			}
		});
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

			}
		});
		button.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				SoundManager.playClickSound();
				switch (buttonText) {
				case "Button 1":
					PlayerController.buy(prices[0]);
					break;
				case "Button 2":
					break;
				case "Button 3":
					PlayerController.buy(prices[2]);
					System.out.println(PlayerController.getMoney());
					break;
				case "Button 4":
					break;
				case "Button 5":
					break;
				case "Button 6":
					break;
				case "Button 7":
					break;
				case "Button 8":
					ScreenController.changeScene("menu");
					break;
				default:

				}
			}
		});
		button.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {

			}

		});

		anchorpane.getChildren().addAll(button);
	}

	// TODO Draw Shop Ui
	public static void drawShop(GraphicsContext gc) {
		// Draw the UI Bar
		String imagePathMenubar = ClassLoader.getSystemResource("menubar.jpg").toString();
		DrawManager.drawImageFixSize(gc, imagePathMenubar, 0, 0, 640.0 * 1.5, 75.0 * 1.5);
		// Draw the images
		for (int i = 0; i < 7; i++) {
			// Draw Images
			DrawManager.drawImageFixSize(gc, imagePaths[i], (int) (buttonDetail[i][0] * 1.5),
					(int) (buttonDetail[i][1] * 1.5), getButtonWidth(i + 1) * 1.5, getButtonHeight(i + 1) * 1.5);
			// Draw Prices
			DrawManager.drawText(gc, "" + prices[i], 18, (int) ((buttonDetail[i][0] + getButtonWidth(i + 1) / 2 - getDigit(prices[i])*3) * 1.5),
					(int) ((buttonDetail[i][3] * 1.5) + 16), (int) ((buttonDetail[i][2] - buttonDetail[i][0]) * 1.5));

		}
		// Draw current money
		DrawManager.drawText(gc, "" + PlayerController.getMoney(), 24, (int) ((buttonDetail[7][0] + getButtonWidth(7 + 1) / 2 - getDigit(prices[7])*5) * 1.5 + 10),
				(int) ((buttonDetail[7][3] * 1.5) + 45), (int) ((buttonDetail[7][2] - buttonDetail[7][0]) * 1.5));

	}

	public void setPrice(int ButtonNumber, int newPrice) {
		this.prices[ButtonNumber - 1] = newPrice;
	}

	private static int getButtonWidth(int buttonNumber) {
		return (int) (buttonDetail[buttonNumber - 1][2] - buttonDetail[buttonNumber - 1][0]);
	}

	private static int getButtonHeight(int buttonNumber) {
		return (int) (buttonDetail[buttonNumber - 1][3] - buttonDetail[buttonNumber - 1][1]);
	}

	private static int getDigit(int n) {
		String str = String.valueOf(n);
		return str.length();
	}

}
