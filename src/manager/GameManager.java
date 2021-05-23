package manager;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import manager.base.Level;
import model.Food;
import model.base.Fish;
import model.base.Money;
import model.base.Monster;
import model.fish.Beetlemuncher;
import model.fish.Breeder;
import model.fish.Carnivore;
import model.fish.Guppy;
import model.fish.Guppycruncher;
import model.fish.Starcatcher;
import model.fish.Ultravore;
import model.money.Star;
import model.monster.Balrog;
import model.monster.Destructor;
import model.monster.Gus;
import model.monster.Sylvester;
import template.Pause;

public class GameManager {
	private static int WIDTH = (int) (640 * 1.5);
	private static int HEIGHT = (int) (480 * 1.5);
	private static int TOPHEIGHT = 150;
	private static int BOTTOMHEIGHT = HEIGHT - 30;

	private static int FRAMERATE = 25;

	private static Canvas canvas;
	private static Pane tankPane;
	private static AnchorPane anchorPane;
	private static GraphicsContext gc;
	private static Scene tankScene;

	private static int currentTank;
	private static int currentLevel;
	private static Thread tankThread;

	public GameManager() {
		tankPane = new Pane();
		tankScene = new Scene(tankPane, GameManager.getWIDTH(), GameManager.getHEIGHT());

		canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		tankPane.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		anchorPane = new AnchorPane();
		tankPane.getChildren().add(anchorPane);

		Pause.setAnchorPane(anchorPane);
		Pause.setPauseButtons();
		Pause.hideButtons();

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

						if (!InvasionManager.isHasGus()) {
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

	public Thread newTankThread(Canvas canvas, GraphicsContext gc) {
		return new Thread(new Runnable() {

			@Override
			public void run() {
				Runnable updater = new Runnable() {
					@Override
					public void run() {
						if (!PlayerController.isPause()) {
							TankManager.update();
							if (!PlayerController.isBack()) {
								PlayerController.checkPlaying();
								InvasionManager.update();

							}

							Pause.hideButtons();
						}
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//						gc.drawImage(bc, 0, 0, canvas.getWidth(), canvas.getHeight());
						gc.setFill(Color.rgb(102, 204, 255));
						gc.fillRect(0, 0, canvas.getWidth(), GameManager.getBOTTOMHEIGHT());

						DrawManager.setTankBg(gc, currentTank);

						TankManager.render(gc);
						ShopController.drawShop(gc);
						InvasionManager.render(gc);

						if (PlayerController.isPause()) {
							Pause.drawPane(gc);
							Pause.showButtons();
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

			}

		});
	}

	public void startLevelTest(int tank, int level) {
		clearLevel();
		GameManager.setCurrentTank(tank);
		GameManager.setCurrentLevel(level);
		StatTracker.clear();

		//////////////////////////
		Guppy g1 = new Guppy("g1", 500, 300);
		TankManager.addStartFish(g1);

		Guppy g2 = new Guppy("g2", 200, 100);
		TankManager.addStartFish(g2);

		Guppy g3 = new Guppy("g3", 400, 100);
		g3.setGrowth(200);
		g3.setStar(true);
		TankManager.addStartFish(g3);

		Carnivore c1 = new Carnivore("c1", 300, 200);
		TankManager.addStartFish(c1);

		Star s1 = new Star("s1", 480, 20);
		TankManager.add(s1);

		Starcatcher sc1 = new Starcatcher("sc1", 300, 400);
		TankManager.addStartFish(sc1);

		Guppycruncher gc1 = new Guppycruncher("gc1", 300, 100);
		TankManager.addStartFish(gc1);

		Beetlemuncher bm1 = new Beetlemuncher("bm", 300, 300);
		TankManager.addStartFish(bm1);

		Breeder b1 = new Breeder("b1", 400, 288);
		TankManager.addStartFish(b1);

		Ultravore uv1 = new Ultravore("uv1", 100, 500);
		TankManager.addStartFish(uv1);

		// Invasion
		Sylvester sv = new Sylvester("SV", 400, 500, 0);
		Balrog br = new Balrog("BR", 400, 500, 0);
		Gus g = new Gus("G", 400, 500, 0);
		Destructor d = new Destructor("d", 400, GameManager.getBOTTOMHEIGHT() - 200, 0);

		ArrayList<Monster> firstInvasion = new ArrayList<Monster>();
		firstInvasion.add(sv);
		firstInvasion.add(br);

		ArrayList<Monster> secondInvasion = new ArrayList<Monster>();
		secondInvasion.add(g);

		ArrayList<Monster> thirdInvasion = new ArrayList<Monster>();
		thirdInvasion.add(d);

		InvasionManager.setInvasionList(new ArrayList<>());
		InvasionManager.getInvasionList().add(firstInvasion);
		InvasionManager.getInvasionList().add(secondInvasion);
		InvasionManager.getInvasionList().add(thirdInvasion);
		
		InvasionManager.setInvasionTimeList(new int[] { 40, 50, 60, 60, 70 });
		InvasionManager.setInvasionTime((long) (System.nanoTime() + (InvasionManager.getInvasionTimeList()[0] * 1e9)));

		//////////////////////////// Setting

		PlayerController.setPlaying(true);
		PlayerController.setMaxFood(3);
		PlayerController.setMoney(200);
		PlayerController.setGunLevel(2);

		ShopController.setShopDetail(LevelManager.getLevel(1, 1));
		ShopController.setAllButtons(anchorPane);
		ShopController.prices[6] = 50;

		tankThread = newTankThread(canvas, gc);
		tankThread.setDaemon(true);
		System.out.println("Playing Level ???");
		tankThread.start();
	}

	public void startLevel(Level level) {
		clearLevel();
		GameManager.setCurrentTank(level.getId()[0]);
		GameManager.setCurrentLevel(level.getId()[1]);

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
		tankThread = newTankThread(canvas, gc);
		tankThread.setDaemon(true);
		System.out.println("Playing Level " + level.getId()[0] + "-" + level.getId()[1]);
		tankThread.start();
	}

	public static void clearLevel() {
		// Clear Level
		TankManager.clear();
		StatTracker.clear();
		PlayerController.clear();
		InvasionManager.clear();
	}

	public static void winLevel() {
		if (currentTank == JSONManager.getTank() && currentLevel == JSONManager.getLevel()) {
			JSONManager.nextLevel();
		}
	}

	public void clickAddFood(MouseEvent event, int i) {

		Thread addFoodThread = new Thread(() -> {
			try {
				if (event.getSceneY() >= GameManager.getTOPHEIGHT()
						&& event.getSceneY() <= GameManager.getBOTTOMHEIGHT()) {
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
			} catch (Exception e) {

			}
		});
		addFoodThread.start();

	}

	// GETTER SETTER

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static int getBOTTOMHEIGHT() {
		return BOTTOMHEIGHT;
	}

	public static int getFRAMERATE() {
		return FRAMERATE;
	}

	public static int getTOPHEIGHT() {
		return TOPHEIGHT;
	}

	public static void setScene(Stage stage) {
		stage.setScene(getStaticTankScene());
	}

	public static Thread getTankThread() {
		return tankThread;
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
		GameManager.currentTank = currentTank;
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}

	public static void setCurrentLevel(int currentLevel) {
		GameManager.currentLevel = currentLevel;
	}

}
