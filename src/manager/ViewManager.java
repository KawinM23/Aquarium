package manager;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Food;
import model.base.Money;
import model.base.Monster;
import model.fish.Beetlemuncher;
import model.fish.Carnivore;
import model.fish.Guppy;
import model.fish.Guppycruncher;
import model.fish.Starcatcher;
import model.money.Beetle;
import model.money.SilverCoin;
import model.money.Star;
import model.monster.Sylvester;

public class ViewManager {
	private Pane mainPane;
	private static Scene tankScene;
	private Stage tankStage;
	
	private static Thread threadTank;

	public ViewManager() {
		mainPane = new Pane();
		tankScene = new Scene(mainPane, GameManager.getWIDTH(), GameManager.getHEIGHT());
//		tankStage = new Stage();
//		tankStage.setScene(tankScene);

		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		mainPane.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		///////////////////////// TEST

		Guppy g1 = new Guppy("g1", 500, 300);
		TankManager.addNewFish(g1);

		Guppy g2 = new Guppy("g2", 200, 100);
		TankManager.addNewFish(g2);

		Guppy g3 = new Guppy("g3", 400, 100);
		TankManager.addNewFish(g3);

		Carnivore c1 = new Carnivore("c1", 300, 200);
		TankManager.addNewFish(c1);

//		Starcatcher sc1 = new Starcatcher("sc1", 300, 400);
//		TankManager.add(sc1);
//
//		Star s1 = new Star("s1", 400, 200);
//		TankManager.add(s1);
//
//
		Guppycruncher gc1 = new Guppycruncher("gc1", 300, 100);
		TankManager.addNewFish(gc1);
//
//		Beetlemuncher bm1 = new Beetlemuncher("bm", 300, 300);
//		TankManager.add(bm1);

		Sylvester sv = new Sylvester("Sv", 400, 500);

		SoundManager test = new SoundManager();

		PlayerController.setPlaying(true);
		PlayerController.setMaxFood(3);
		PlayerController.setMoney(200);

		InvasionManager.setInvasionTime((long) (System.nanoTime() + 20e9));
		ArrayList<Monster> firstInvasion = new ArrayList<Monster>();
		firstInvasion.add(sv);
		InvasionManager.getInvasionList().add(firstInvasion);
		InvasionManager.setInvasionTimeList(new int[] { 20, 30, 40 });
		InvasionManager.setInvasionTime((long) (System.nanoTime() + (InvasionManager.getInvasionTimeList()[0] * 1e9)));

		AnchorPane ap = new AnchorPane();

		Level level1_1 = new Level("1_1", 1, 1);
		LevelManager.loadLevel1_1(level1_1);

		ShopController.setShopDetail(level1_1);
		ShopController.setAllButtons(ap);
		ShopController.prices[6] = 50;

		mainPane.getChildren().add(ap);
		//////////////////////////

		// TODO Change Code to new Controller
		threadTank = new Thread(new Runnable() {

			@Override
			public void run() {
				Runnable updater = new Runnable() {
					@Override
					public void run() {
						if (!PlayerController.isPause()) {
							InvasionManager.update();
							TankManager.update();
							PlayerController.checkPlaying();
						}
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//						gc.drawImage(bc, 0, 0, canvas.getWidth(), canvas.getHeight());
						gc.setFill(Color.rgb(102, 204, 255));
						gc.fillRect(0, 0, canvas.getWidth(), GameManager.getBOTTOMHEIGHT());

						TankManager.render(gc);
						ShopController.drawShop(gc);
						InvasionManager.render(gc);
					}
				};

				while (PlayerController.isPlaying()) {
					try {
						Thread.sleep(1000 / GameManager.getFRAMERATE());// 50hz ,1000/50 = 20
					} catch (InterruptedException ex) {
					}
					Platform.runLater(updater);
				}
				if (!PlayerController.isPlaying()) {

				}
			}

		});

		// don't let thread prevent JVM shutdown
		threadTank.setDaemon(true);
		
		StatTracker.clear();

		// MouseClick Position
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (PlayerController.isPlaying() && !PlayerController.isPause()) {
					if (!InvasionManager.isInvaded()) {
						for (Money m : TankManager.getMoneyList()) {
							if (m.getBoundary().contains(event.getSceneX(), event.getSceneY())) {
								// Collect Money
								m.collected();
								return;
							}
						}
						// Add Food at mouse position
						if (TankManager.getFoodList().size() < PlayerController.getMaxFood()
								&& PlayerController.buy(5)) {
							System.out.println("Add Food " + PlayerController.getFoodLevel());
							StatTracker.addFoodBought();
							TankManager.addFood(new Food("Food", event.getSceneX(), event.getSceneY(), 1));
						}

					} else {
						// Shoot
						for (Monster m : TankManager.getMonsterList()) {
							if (m.getBoundary().contains(event.getSceneX(), event.getSceneY())) {
								m.getHit();
								break;
							}
						}
					}
				}

			}
		};

		tankScene.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

//		tankStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//			if (event.getCode() == KeyCode.SPACE) {
//				PlayerController.togglePause();
//			}
//		});

	}
	
	public void startLevel() {
		StatTracker.clear();
		threadTank.start();
	}
	
	public static void setScene(Stage stage) {
		stage.setScene(getStaticTankScene());
	}

	public static Thread getThreadTank() {
		return threadTank;
	}

	public Stage getTankStage() {
		return tankStage;
	}

	public Scene getTankScene() {
		return tankScene;
	}
	
	public static Scene getStaticTankScene() {
		return tankScene;
	}

}
