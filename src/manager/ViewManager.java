package manager;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Food;
import model.Guppy;

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

		///////////////////////// TEST

		Guppy g1 = new Guppy("g1", 500, 300);
		TankManager.getUnitList().add(g1);
		TankManager.getFishList().add(g1);

		Food f1 = new Food("f1", 200, 50);
		TankManager.getUnitList().add(f1);
		TankManager.getFoodList().add(f1);

		Food f2 = new Food("f2", 800, 200);
		TankManager.getUnitList().add(f2);
		TankManager.getFoodList().add(f2);

		//////////////////////////
		Thread threadTank = new Thread(new Runnable() {

			@Override
			public void run() {
				Runnable updater = new Runnable() {

					@Override
					public void run() {
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
						gc.setFill(Color.rgb(102, 204, 255));
						gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
						TankManager.update(gc);
					}
				};

				while (true) {
					try {
						Thread.sleep(20);// 50hz
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
