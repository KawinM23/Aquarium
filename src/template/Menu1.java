package template;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import manager.ButtonManager;
import manager.DrawManager;
import manager.GameManager;

public class Menu1 {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	private final double[][] BUTTON_DETAIL = { { 354.0, 43.0, 576.0, 116.0, 100.0, 50.0 },
			{ 354.0, 135.0, 576.0, 189.0, 30.0, 38.0 }, { 354.0, 207.0, 576.0, 262.0, 30.0, 40.0 },
			{ 354.0, 282.0, 576.0, 357.0, 100.0, 30.0 }, { 402.0, 377.0, 529.0, 409.0, 80.0, 20.0 },
			{ 323.0, 410.0, 417.0, 443.0, 78.0, 15.0 }, { 416.0, 410.0, 512.0, 443.0, 10.0, 15.0 },
			{ 513.0, 410.0, 603.0, 443.0, 80.0, 20.0 } };
	private final String IMAGE_PATH = ClassLoader.getSystemResource("menu_editted5.jpg").toString();
	private Scene scene;
	private String[] buttonTexts;
	// ClassLoader.getSystemResource("").toString();

	public Menu1(String[] buttonTexts) {
		this.buttonTexts = buttonTexts;
		AnchorPane root = new AnchorPane();
		this.scene = new Scene(root);
		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);

		DrawManager.resetBackGround(gc);
		DrawManager.setBackGroundImage(gc, IMAGE_PATH);
		DrawManager.drawGuraGreeting(gc, "MainMenu");

		for (int i = 0; i < BUTTON_DETAIL.length; i++) {
			Button button = new Button(buttonTexts[i]);
			ButtonManager.setFont(button, (int) BUTTON_DETAIL[i][5]);
			ButtonManager.setHighlightProperty(button, (int) BUTTON_DETAIL[i][4]);
			ButtonManager.setMenu1ButtonHandler(button);
			ButtonManager.setButtonLocation(root, button, BUTTON_DETAIL[i]);
			// addButtons(root, buttonTexts[i], BUTTON_DETAIL[i]);
		}
		;
	}

	public Scene getScene() {
		return this.scene;
	}

}
