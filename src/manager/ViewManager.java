package manager;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Carnivore;
import model.Food;
import model.Guppy;
import model.Star;
import model.Starcatcher;
import model.Sylvester;

public class ViewManager {
	private Pane mainPane;
	private Scene tankScene;
	private Stage tankStage;

	public ViewManager() {
		mainPane = new Pane();
		tankScene = new Scene(mainPane, GameManager.getWIDTH(), GameManager.getHEIGHT());
//		tankStage = new Stage();
//		tankStage.setScene(tankScene);

		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		mainPane.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		///////////////////////// TEST

		PlayerController.setPlaying(true);
		PlayerController.setMaxFood(3);
		
		Image bc = new Image("file:res/image/aquarium1.jpg");

		Guppy g1 = new Guppy("g1", 500, 300);
		TankManager.add(g1);

		Food f1 = new Food("f1", 200, 20, 1);
		TankManager.add(f1);

		Food f2 = new Food("f2", 800, 200, 1);
		TankManager.add(f2);
//		
//		Carnivore c1 = new Carnivore("c1", 300, 200);
//		TankManager.add(c1);

//		Starcatcher sc1 = new Starcatcher("sc1", 300, GameManager.getBOTTOMHEIGHT()-40);
//		TankManager.add(sc1);
//		
//		Star s1 = new Star("s1", 400, 200);
//		TankManager.add(s1);

		Sylvester sv = new Sylvester("Sv", 400, 500);
		TankManager.add(sv);
		
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

				while (PlayerController.isPlaying()) {
					try {
						Thread.sleep(1000 / GameManager.getFRAMERATE());// 50hz ,1000/50 = 20
					} catch (InterruptedException ex) {
					}
					Platform.runLater(updater);
				}
				if(!PlayerController.isPlaying()) {
					System.out.println("LOSE");
				}
			}

		});

		// don't let thread prevent JVM shutdown
		threadTank.setDaemon(true);
		threadTank.start();

		//MouseClick Position
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Click "+event.getSceneX()+" "+event.getSceneY());
				//Add Food
				TankManager.addFood(new Food("Food",event.getSceneX(),event.getSceneY(), 1));
			}
		};
		
		tankScene.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

	}

	public Stage getTankStage() {
		return tankStage;
	}
	
	public Scene getTankScene() {
		return tankScene;
	}
	
	

}
