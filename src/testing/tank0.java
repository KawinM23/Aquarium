package testing;

import manager.DrawManager;
import manager.LevelManager;
import manager.SceneController;
import manager.ShopController;
import manager.SoundManager;
import manager.GameManager;
import manager.base.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class tank0 extends Application {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	final double[][] buttonDetail = { { 22.0, 3.0, 72.0, 46.0, 100.0, 20.0 }, { 91.0, 3.0, 141.0, 46.0, 80.0, 20.0 },
			{ 148.0, 3.0, 198.0, 46.0, 80.0, 20.0 }, { 221.0, 3.0, 271.0, 46.0, 100.0, 20.0 },
			{ 295.0, 3.0, 344.0, 46.0, 80.0, 20.0 }, { 367.0, 3.0, 417.0, 46.0, 80.0, 20.0 },
			{ 440.0, 3.0, 490.0, 46.0, 80.0, 20.0 }, { 532.0, 3.0, 617.0, 25.0, 80.0, 20.0 } };
	private int money = 0;
	private Scene scene;
	private static SceneController screenController;
	static final String MENU_IMAGE_PATH = ClassLoader.getSystemResource("menubar.jpg").toString();
	static final String BACKGROUND_IMAGE_PATH = ClassLoader.getSystemResource("aquarium1.jpg").toString();
	static final Image menuImage = new Image(MENU_IMAGE_PATH);
	static final Image backgroundImage = new Image(BACKGROUND_IMAGE_PATH);

	// ClassLoader.getSystemResource("").toString();
	public tank0() {
		screenController = new SceneController();
		AnchorPane root = new AnchorPane();
		scene = new Scene(root);
		Canvas canvas = new Canvas(640 * 1.5, 480 * 1.5);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		setBackGround(gc);
		DrawManager.drawImageFixSize(gc, backgroundImage, 0, 0, 640.0 * 1.5, 480.0 * 1.5);
		//DrawManager.drawImageFixSize(gc, menuImage, 0, 0, 640.0 * 1.5, 75.0 * 1.5);
		
		Level level1_1 = new Level("1_1",1,1);
		LevelManager.loadLevel1_1(level1_1);
		
		ShopController.setShopDetail(level1_1);
		ShopController.setAllButtons(root);
		ShopController.drawShop(gc);
	}

	@Override
	public void start(Stage stage) {
		AnchorPane root = new AnchorPane();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Aquarium Demastered");
		stage.setResizable(false);
		Canvas canvas = new Canvas(640 * 1.5, 480 * 1.5);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		setBackGround(gc);
		DrawManager.drawImageFixSize(gc, backgroundImage, 0, 0, 640.0 * 1.5, 480.0 * 1.5);
		DrawManager.drawImageFixSize(gc, menuImage, 0, 0, 640.0 * 1.5, 75.0 * 1.5);
		// Draw Shop
		ShopController.setAllButtons(root);

		stage.show();
	}

	public void addScreen(SceneController screen) {
		AnchorPane root = new AnchorPane();
		Canvas canvas = new Canvas(640 * 1.5, 480 * 1.5);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		setBackGround(gc);
		DrawManager.drawImageFixSize(gc, backgroundImage, 0, 0, 640.0 * 1.5, 480.0 * 1.5);
		DrawManager.drawImageFixSize(gc, menuImage, 0, 0, 640.0 * 1.5, 75.0 * 1.5);
		// Draw Shop
		ShopController.setAllButtons(root);
	}

	private void setBackGround(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	public Scene getScene() {
		return scene;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
