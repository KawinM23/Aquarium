package manager;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Carnivore;
import model.Food;
import model.Guppy;
import model.Star;
import model.Starcatcher;

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

		Image bc = new Image("file:res/image/aquarium1.jpg");

		Guppy g1 = new Guppy("g1", 500, 300);
		TankManager.add(g1);

		Food f1 = new Food("f1", 200, 20, 0);
		TankManager.add(f1);

		Food f2 = new Food("f2", 800, 200, 0);
		TankManager.add(f2);
//		
//		Carnivore c1 = new Carnivore("c1", 300, 200);
//		TankManager.add(c1);
//		
//		StarCatcher sc1 = new StarCatcher("sc1", 300, 600);
//		TankManager.add(sc1);
//		
		Star s1 = new Star("s1", 400, 200);
		TankManager.add(s1);

		//////////////////////////
		Thread threadTank = new Thread(new Runnable() {

			@Override
			public void run() {
				Runnable updater = new Runnable() {

					@Override
					public void run() {
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//						gc.drawImage(bc, 0, 0, canvas.getWidth(), canvas.getHeight());
						gc.setFill(Color.rgb(102, 204, 255));
						gc.fillRect(0, 0, canvas.getWidth(), GameManager.getBOTTOMHEIGHT());
						TankManager.update(gc);
					}
				};

				while (true) {
					try {
						Thread.sleep(1000 / GameManager.getFRAMERATE());// 50hz ,1000/50 = 20
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
