package game;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import manager.JSONManager;
import manager.SceneController;
import manager.SoundManager;
import manager.ViewManager;

public class main0 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			ViewManager manager = new ViewManager();
//			primaryStage.setScene(manager.getTankScene());
			JSONManager.readJSON();
			SceneController.setStage(primaryStage);
			SceneController.initializeScenes();
			SceneController.changeScene("MainMenu");
			SoundManager.setBgm(0);
			SoundManager.playBgm();

			primaryStage.setTitle("AQUARIUM");
			primaryStage.setResizable(false);
			primaryStage.show();
			
			primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
				if (event.getCode() == KeyCode.SPACE) {
					System.out.println(JSONManager.getJsonNameList());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
