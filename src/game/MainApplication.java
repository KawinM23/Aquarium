package game;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import manager.DrawManager;
import manager.JSONManager;
import manager.LevelManager;
import manager.SceneController;
import manager.SoundManager;

public class MainApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			ViewManager manager = new ViewManager();
//			primaryStage.setScene(manager.getTankScene());
			JSONManager.readJSON();

			SceneController.setStage(primaryStage);
			SoundManager.setBgm(0);
			DrawManager.setJustOpened();
			SceneController.updatePlayerSettings();
			SceneController.initializeScenes();
			SceneController.changeScene("MainMenu");

			LevelManager.loadAllLevel();

			SoundManager.setBgm(0);
			SoundManager.playBgm();

			primaryStage.setTitle("AQUARIUM");
			primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("GuppyLeft.png").toString()));
			primaryStage.setResizable(false);
			primaryStage.show();

			primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
				if (event.getCode() == KeyCode.SPACE) {
//					System.out.println(JSONManager.getJsonNameList());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		return;
	}
}
