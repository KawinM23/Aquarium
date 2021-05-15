package game;

import manager.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class main1 extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("Main1");
			ViewManager manager = new ViewManager();
			primaryStage = manager.getTankStage();
			
			
			
			
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
