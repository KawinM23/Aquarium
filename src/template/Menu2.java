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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manager.ButtonManager;
import manager.DrawManager;
import manager.GameManager;
import manager.JSONManager;
import manager.LevelManager;
import manager.SceneController;
import manager.SoundManager;
import manager.GameManager;
import manager.base.Level;
import testing.tank0;

public class Menu2 {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	// THIS IS MEASURED IN 940 x 720 PIXELS
	private final double[][] BUTTON_DETAIL = { { 553, 68, 899, 157, 20, 40 }, { 557, 203, 897, 291, 20, 40 },
			{ 563, 338, 898, 423, 20, 40 }, { 564, 460, 903, 543, 20, 40 }, { 578, 597, 882, 681, 40, 40 } };
	private final String IMAGE_PATH = ClassLoader.getSystemResource("menu2_editted3.jpg").toString();
	private Scene scene;
	private String[] buttonTexts;
	
	// ClassLoader.getSystemResource("").toString();

	public Menu2(String[] buttonTexts) {
		this.buttonTexts = buttonTexts;
		AnchorPane root = new AnchorPane();
		this.scene = new Scene(root);
		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		DrawManager.resetBackGround(gc);

		DrawManager.setBackGroundImage(gc, IMAGE_PATH);
		DrawManager.drawGuraGreeting(gc, "Tank");

		for (int i = 0; i < BUTTON_DETAIL.length; i++) {
			Button button = new Button(buttonTexts[i]);
			ButtonManager.setFont(button, (int) BUTTON_DETAIL[i][5]);
			ButtonManager.setHighlightProperty(button, (int) BUTTON_DETAIL[i][4]);
			ButtonManager.setMenu2ButtonHandler(button);
			ButtonManager.setButtonLocationNoZoom(root, button, BUTTON_DETAIL[i]);
			ButtonManager.addMenu2Button(button);
			// addButtons(root, buttonTexts[i], BUTTON_DETAIL[i]);
		}
		ButtonManager.updateTankLevelButtons();
		
	}

	public Scene getScene() {
		return this.scene;
	}

}
