package manager;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewManager {
	private Pane mainPane;
	private Scene mainScene;
	private Stage mainStage;

	public ViewManager() {
		mainPane = new Pane();
		mainScene = new Scene(mainPane, GameManager.getWIDTH(), GameManager.getHEIGHT());
		mainStage = new Stage();
		mainStage.setScene(mainScene);

		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		mainPane.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Thread threadTank = new Thread(new Runnable() {

			@Override
			public void run() {
				Runnable updater = new Runnable() {

					@Override
					public void run() {
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
						gc.setFill(Color.rgb(102, 204, 255));
						gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
					}
				};

				while (true) {
					try {
						Thread.sleep(20);//50hz

					} catch (InterruptedException ex) {
					}

					// UI update is run on the Application thread
					Platform.runLater(updater);
				}
			}

		});
		// don't let thread prevent JVM shutdown
		threadTank.setDaemon(true);
		threadTank.start();

	}

	public Stage getMainStage() {
		return mainStage;
	}

	
}
