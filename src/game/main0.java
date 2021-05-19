package game;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.SceneController;
import manager.ViewManager;

public class main0 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			ViewManager manager = new ViewManager();
//			primaryStage.setScene(manager.getTankScene());
			SceneController.setStage(primaryStage);
			SceneController.initializeScenes();
			SceneController.changeScene("MainMenu");
			
			primaryStage.setTitle("AQUARIUM");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
