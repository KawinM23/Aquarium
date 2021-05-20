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
import model.base.Fish;
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
import model.monster.Balrog;
import model.monster.Gus;
import model.monster.Sylvester;
import template.pause1;

public class ViewManager {
	private Pane tankPane;
	private AnchorPane anchorPane;
	private static Scene tankScene;

	private static int currentTank;
	private static int currentLevel;
	private static Thread threadTank;

	public ViewManager() {
		tankPane = new Pane();
		tankScene = new Scene(tankPane, GameManager.getWIDTH(), GameManager.getHEIGHT());

		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		tankPane.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		anchorPane = new AnchorPane();
		tankPane.getChildren().add(anchorPane);
		
		pause1.setAnchorPane(anchorPane);
		pause1.setPauseButtons();
		pause1.hideButtons();

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
							pause1.hideButtons();
						}
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//						gc.drawImage(bc, 0, 0, canvas.getWidth(), canvas.getHeight());
						gc.setFill(Color.rgb(102, 204, 255));
						gc.fillRect(0, 0, canvas.getWidth(), GameManager.getBOTTOMHEIGHT());

						TankManager.render(gc);
						ShopController.drawShop(gc);
						InvasionManager.render(gc);
						if (PlayerController.isPause()) {
							pause1.drawPane(gc);
							pause1.showButtons();
						}

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
						clickAddFood(event, 0);

					} else {

						boolean hasGus = false;
						for (Monster m : TankManager.getMonsterList()) {
							if (m instanceof Gus) {
								hasGus = true;
							}
						}
						if (!hasGus) {
							// Shoot
							for (Monster m : TankManager.getMonsterList()) {
								if (m.getBoundary().contains(event.getSceneX(), event.getSceneY())) {
									m.getHit();
									break;
								}
							}
						} else {
							// Feed
							clickAddFood(event, 1);
						}
					}
				}

			}

		};

		tankScene.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

//		startLevelTest(1, 1);
	}

	public void startLevelTest(int tank, int level) {
		ViewManager.setCurrentTank(tank);
		ViewManager.setCurrentLevel(level);
		StatTracker.clear();

		//////////////////////////
		Guppy g1 = new Guppy("g1", 500, 300);
		TankManager.addNewFish(g1);

		Guppy g2 = new Guppy("g2", 200, 100);
		TankManager.addNewFish(g2);

		Guppy g3 = new Guppy("g3", 400, 100);
		TankManager.addNewFish(g3);

		Carnivore c1 = new Carnivore("c1", 300, 200);
		TankManager.addNewFish(c1);

		Starcatcher sc1 = new Starcatcher("sc1", 300, 400);
		TankManager.add(sc1);

		Star s1 = new Star("s1", 400, 200);
		TankManager.add(s1);
//
//
		Guppycruncher gc1 = new Guppycruncher("gc1", 300, 100);
		TankManager.addNewFish(gc1);
//
//		Beetlemuncher bm1 = new Beetlemuncher("bm", 300, 300);
//		TankManager.add(bm1);

		Sylvester sv = new Sylvester("Sv", 400, 500);

		Balrog br = new Balrog("Br", 400, 500);

		Gus g = new Gus("GUS", 400, 500);

		InvasionManager.setInvasionTime((long) (System.nanoTime() + 20e9));
		ArrayList<Monster> firstInvasion = new ArrayList<Monster>();
		firstInvasion.add(g);
		InvasionManager.getInvasionList().add(firstInvasion);
		InvasionManager.setInvasionTimeList(new int[] { 10, 30, 40 });
		InvasionManager.setInvasionTime((long) (System.nanoTime() + (InvasionManager.getInvasionTimeList()[0] * 1e9)));

		//////////////////////////// Setting

		PlayerController.setPlaying(true);
		PlayerController.setMaxFood(3);
		PlayerController.setMoney(200);

		ShopController.setShopDetail(LevelManager.getLevel1_1());
		ShopController.setAllButtons(anchorPane);
		ShopController.prices[6] = 50;

		threadTank.start();
	}

	public void startLevel(Level level) {
		clearLevel();
		ViewManager.setCurrentTank(level.getId()[0]);
		ViewManager.setCurrentLevel(level.getId()[1]);

		PlayerController.setMoney(level.getStartingMoney());
		for (Fish fish : level.getStartingFish()) {
			TankManager.addStartFish(fish);
		}
		
		ShopController.setShopDetail(level);
		ShopController.setAllButtons(anchorPane);

		InvasionManager.setInvasionList(level.getInvasionMonster());
		InvasionManager.setInvasionTimeList(level.getInvasionTime());
		InvasionManager.setStartInvasionTime();

		PlayerController.setPlaying(true);
		threadTank.start();
	}

	private void clearLevel() {
		// TODO Clear
		TankManager.clear();
		StatTracker.clear();
		PlayerController.clear();

	}

	public static void winLevel() {
		if (currentTank == JSONManager.getTank() && currentLevel == JSONManager.getLevel()) {
			JSONManager.nextLevel();
		}
	}

	public void clickAddFood(MouseEvent event, int i) {
		if (event.getSceneY() >= GameManager.getTOPHEIGHT()) {
			if (i == 0) {
				if (PlayerController.isPotion()) {
					StatTracker.addFoodBought();
					TankManager.addFood(new Food("Food", event.getSceneX(), event.getSceneY(), 2));
					PlayerController.setPotion(false);
				} else if (TankManager.getFoodList().size() < PlayerController.getMaxFood()
						&& PlayerController.buy(5)) {
					System.out.println("Add Food " + PlayerController.getFoodLevel());
					StatTracker.addFoodBought();
					TankManager.addFood(new Food("Food", event.getSceneX(), event.getSceneY(), 1));
				}
			} else if (i == 1) {
				if (PlayerController.isPotion()) {
					StatTracker.addFoodBought();
					TankManager.addFood(new Food("Food", event.getSceneX(), event.getSceneY(), 2));
					PlayerController.setPotion(false);
				} else if (TankManager.getFoodList().size() < PlayerController.getMaxFood()) {
					System.out.println("Add Food " + PlayerController.getFoodLevel());
					TankManager.addFood(new Food("Food", event.getSceneX(), event.getSceneY(), 1));
				}
			}
		}
	}

	public static void setScene(Stage stage) {
		stage.setScene(getStaticTankScene());
	}

	public static Thread getThreadTank() {
		return threadTank;
	}

	public Scene getTankScene() {
		return tankScene;
	}

	public static Scene getStaticTankScene() {
		return tankScene;
	}

	public static int getCurrentTank() {
		return currentTank;
	}

	public static void setCurrentTank(int currentTank) {
		ViewManager.currentTank = currentTank;
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}

	public static void setCurrentLevel(int currentLevel) {
		ViewManager.currentLevel = currentLevel;
	}

}
