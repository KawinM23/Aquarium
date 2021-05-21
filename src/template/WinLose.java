package template;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import manager.ButtonManager;
import manager.DrawManager;
import manager.GameManager;
import manager.PlayerController;
import manager.SceneController;
import manager.SoundManager;
import manager.ViewManager;

public class WinLose {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	static final double[][] buttonDetail = { { 235, 285, 745, 392, 10, 60 }, { 200, 482, 771, 600, 10, 60 } };
	static String[] winButtonTexts = { "Next Level", "Back to Menu" };
	static String[] loseButtonTexts = { "Try Again", "Back to Menu" };
	static String[] buttonTexts;
	private static final String IMAGE_PATH = ClassLoader.getSystemResource("WinLose.jpg").toString();
	private static Image image = new Image(IMAGE_PATH);
	private static final int posX = 275;
	private static final int posY = 50;
	private static final int width = 535;
	private static final int height = 482;
	static AnchorPane anchorPane;
	static GraphicsContext gc;
	static Scene scene;
	static ArrayList<Button> buttonList = new ArrayList<Button>();

	// 1 for win, 0 for lose
	public WinLose(int status) {
		// If Lose
		if (status == 0) {
			buttonTexts = loseButtonTexts;
		}
		// If Win
		else if (status == 1) {
			buttonTexts = winButtonTexts;
		}

		AnchorPane root = new AnchorPane();
		scene = new Scene(root);
		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		setGraphicsContext(gc);
		setAnchorPane(root);
		root.getChildren().add(canvas);

		DrawManager.resetBackGround(gc);
		DrawManager.setBackGroundImage(gc, IMAGE_PATH);

		// If Lose
		if (status == 0) {
			drawLoseText();
		}
		// If Win
		else if (status == 1) {
			drawWinText();
		}

		for (int i = 0; i < buttonDetail.length; i++) {
			Button button = new Button(buttonTexts[i]);
			ButtonManager.setFont(button, (int) buttonDetail[i][5]);
			ButtonManager.setHighlightProperty(button, (int) buttonDetail[i][4]);
			ButtonManager.setWinLoseButtonHandler(button);
			ButtonManager.setButtonLocationNoZoom(root, button, buttonDetail[i]);
			// addButtons(root, buttonTexts[i], buttonDetail[i]);
		}
		;
	}

	public static void drawPane() {
		DrawManager.drawImageFixSize(gc, image, posX, posY, width, height);
	}

	public static void setWinButtons() {
		for (int i = 0; i <= 1; i++) {
			Button button = new Button(winButtonTexts[i]);
			ButtonManager.setFont(button, (int) buttonDetail[i][5]);
			ButtonManager.setHighlightProperty(button, (int) buttonDetail[i][4]);
			ButtonManager.setWinLoseButtonHandler(button);
			ButtonManager.setButtonLocationNoZoom(anchorPane, button, buttonDetail[i]);
			// addButtons(winButtonTexts[i], buttonDetail[i]);
		}
	}

	public static void setLoseButtons() {
		for (int i = 0; i <= 1; i++) {
			Button button = new Button(loseButtonTexts[i]);
			ButtonManager.setFont(button, (int) buttonDetail[i][5]);
			ButtonManager.setHighlightProperty(button, (int) buttonDetail[i][4]);
			ButtonManager.setWinLoseButtonHandler(button);
			ButtonManager.setButtonLocationNoZoom(anchorPane, button, buttonDetail[i]);
			// addButtons(winButtonTexts[i], buttonDetail[i]);
		}
	}

	public static void drawWinText() {
		DrawManager.drawStatsText(gc, "You Win!", 130, 200, 220, 1000);
	}

	public static void drawLoseText() {
		DrawManager.drawStatsText(gc, "You Lose!", 130, 200, 220, 1000);
	}

	public static void setAnchorPane(AnchorPane newAnchorPane) {
		anchorPane = newAnchorPane;
	}

	public static void setGraphicsContext(GraphicsContext gcc) {
		gc = gcc;
	}

	public static void hideButtons() {
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setVisible(false);
		}
	}

	public static void showButtons() {
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setVisible(true);
		}
	}

	public Scene getScene() {
		return this.scene;
	}

}
