package game;

import manager.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class main1 extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage();
			
			
			
			
			primaryStage.setTitle("JAVAFXTEST");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
