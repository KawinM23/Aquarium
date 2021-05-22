package template;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import manager.ButtonManager;
import manager.DrawManager;
import manager.PlayerController;
import manager.SceneController;
import manager.SoundManager;
import manager.GameManager;

public class Pause {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	private static final double[][] BUTTON_DETAIL = { { 53, 138, 340, 217, 20, 40.0 }, { 54, 253, 340, 325, 20, 30.0 },
			{ 59, 363, 342, 434, 20.0, 30.0 }, { 63, 467, 342, 537, 100.0, 30.0 } };
	private static String[] buttonTexts = { "Resume", "Music: ", "Sound: ", "Back to Menu" };
	private static Image image = new Image(ClassLoader.getSystemResource("pause.jpg").toString());
	private static final int POS_X = 275;
	private static final int POS_Y = 50;
	private static final int WIDTH = 391;
	private static final int HEIGHT = 576;
	private static AnchorPane anchorPane;
	private static ArrayList<Button> buttonList = new ArrayList<Button>();

	public static void drawPane(GraphicsContext gc) {
		DrawManager.drawImageFixSize(gc, image, POS_X, POS_Y, WIDTH, HEIGHT);
	}

	public static void setPauseButtons() {
		for (int i = 0; i <= 3; i++) {
			Button button = new Button(buttonTexts[i]);
			ButtonManager.setFont(button, (int) BUTTON_DETAIL[i][5]);
			ButtonManager.setHighlightProperty(button, (int) BUTTON_DETAIL[i][4]);
			ButtonManager.setPauseButtonHandler(button);
			ButtonManager.setPauseButtonLocation(anchorPane, button, BUTTON_DETAIL[i], POS_X, POS_Y);
			buttonList.add(button);
			//addButtons(buttonTexts[i], BUTTON_DETAIL[i]);
		}
	}

	public static void setAnchorPane(AnchorPane newAnchorPane) {
		anchorPane = newAnchorPane;
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

}
