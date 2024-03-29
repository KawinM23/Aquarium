package manager;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import model.Food;
import model.base.Fish;
import model.base.Money;
import model.base.Monster;
import model.base.Unit;
import model.fish.Breeder;
import model.fish.Guppy;
import model.monster.Destructor;
import model.monster.Missile;
import properties.Renderable;

public class TankManager {

	private static Random rand = new Random();

	private static ArrayList<Unit> unitList = new ArrayList<Unit>();
	private static ArrayList<Fish> fishList = new ArrayList<Fish>();
	private static ArrayList<Food> foodList = new ArrayList<Food>();
	private static ArrayList<Money> moneyList = new ArrayList<Money>();
	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();

	private static ArrayList<Unit> addUnitList = new ArrayList<Unit>();
	private static ArrayList<Fish> addFishList = new ArrayList<Fish>();
	private static ArrayList<Monster> addMonsterList = new ArrayList<Monster>();

	private static ArrayList<Unit> removeUnitList = new ArrayList<Unit>();
	private static ArrayList<Unit> removeFishList = new ArrayList<Unit>();
	private static ArrayList<Unit> removeFoodList = new ArrayList<Unit>();
	private static ArrayList<Unit> removeMoneyList = new ArrayList<Unit>();
	private static ArrayList<Unit> removeMonsterList = new ArrayList<Unit>();

	public static ArrayList<Unit> getUnitList() {
		return unitList;
	}

	public static ArrayList<Fish> getFishList() {
		return fishList;
	}

	public static ArrayList<Food> getFoodList() {
		return foodList;
	}

	public static ArrayList<Money> getMoneyList() {
		return moneyList;
	}

	public static ArrayList<Monster> getMonsterList() {
		return monsterList;
	}

	public static int getMonsterCount() {
		return monsterList.size();
	}

	public static void render(GraphicsContext gc) {
		unitList.addAll(addUnitList);
		addUnitList.clear();
		for (Unit u : unitList) {
			if (u instanceof Renderable) {
				((Renderable) u).render(gc);
			}
		}
		unitList.removeAll(removeUnitList);
		removeUnitList.clear();
	}

	public static void update() {
		// Update all unit
		fishList.addAll(addFishList);
		addFishList.clear();
		for (Fish f : fishList) {
			f.update(GameManager.getFRAMERATE());
		}
		fishList.removeAll(removeFishList);
		removeFishList.clear();

		for (Food f : foodList) {
			f.update(GameManager.getFRAMERATE());
		}
		foodList.removeAll(removeFoodList);
		removeFoodList.clear();

		for (Money m : moneyList) {
			m.update(GameManager.getFRAMERATE());
		}
		moneyList.removeAll(removeMoneyList);
		removeMoneyList.clear();

		monsterList.addAll(addMonsterList);
		addMonsterList.clear();
		for (Monster m : monsterList) {
			m.update(GameManager.getFRAMERATE());
		}
		monsterList.removeAll(removeMonsterList);
		removeMonsterList.clear();

		System.gc();
	}

	public static void add(Unit u) {
		addUnitList.add(u);
		if (u instanceof Food) {
			foodList.add((Food) u);
		} else if (u instanceof Money) {
			moneyList.add((Money) u);
		} else if (u instanceof Fish) {
			addFishList.add((Fish) u);
		} else if (u instanceof Monster) {
			addMonsterList.add((Monster) u);
		}
	}

	public static void addMonster(Monster m) {
		if (!(m instanceof Destructor) && !(m instanceof Missile)) {
			double posX = 0 + (GameManager.getWIDTH() - m.getWidth() - 0) * rand.nextDouble();
			double posY = GameManager.getTOPHEIGHT()
					+ (GameManager.getBOTTOMHEIGHT() - m.getHeight() - GameManager.getTOPHEIGHT()) * rand.nextDouble();
			m.setPos(posX, posY);
		}

		m.setHealth(m.getMaxHealth());

		addUnitList.add(m);
		addMonsterList.add(m);
	}

	public static void addNewFish(Fish f) {
		Thread addNewFishThread = new Thread(() -> {
			try {
				// Play Sound Effect
				SoundManager.playSplashSound();
				// rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				double posX = 0 + (GameManager.getWIDTH() - f.getWidth() - 0) * rand.nextDouble();
				double posY = GameManager.getTOPHEIGHT()
						+ (GameManager.getBOTTOMHEIGHT() - f.getHeight() - GameManager.getTOPHEIGHT())
								* rand.nextDouble();
				f.setPos(posX, posY / 2);
				addUnitList.add(f);
				addFishList.add(f);
				// Random Hunger Fish
				f.getHunger().addLastFedRandom(3, 7);
				f.getProduction().setLastProduceRandom(2, 6);

				// Stat Tracker
				StatTracker.addFishBought();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		addNewFishThread.start();

	}

	public static void addStartFish(Fish f) {
		double posX = 0 + (GameManager.getWIDTH() - f.getWidth() - 0) * rand.nextDouble();
		double posY = GameManager.getTOPHEIGHT()
				+ (GameManager.getBOTTOMHEIGHT() - f.getHeight() - GameManager.getTOPHEIGHT()) * rand.nextDouble();
		f.setPos(posX, posY / 2);
		if (f instanceof Guppy) {
			((Guppy) f).setStar(false);
			((Guppy) f).setKing(false);
			((Guppy) f).setGuppy("Small");
			((Guppy) f).setBornTime(System.nanoTime());
		}
		if (f instanceof Breeder) {
			((Breeder) f).setBreeder("Small");
		}
		
		addUnitList.add(f);
		addFishList.add(f);
		// Random Hunger Fish
		f.getHunger().setLastFedNow();
		f.getHunger().addLastFedRandom(3, 6);
		f.getProduction().setLastProduceRandom(2, 6);
	}

	public static void addBornedFish(Fish f) {
		Thread addBornedThread = new Thread(() -> {
			addUnitList.add(f);
			addFishList.add(f);
			// Random Hunger Fish
			f.getHunger().addLastFedRandom(3, 6);
			f.getProduction().setLastProduceRandom(2, 6);
		});
		addBornedThread.start();
	}

	public static void addFood(Food f) {
		// Play Sound Effect
		SoundManager.playDropFoodSound();

		Thread addFoodThread = new Thread(() -> {
			if (!(foodList.size() >= PlayerController.getMaxFood())) {
				addUnitList.add(f);
				foodList.add(f);
			}
		});
		addFoodThread.start();

	}

	public static void produceMoney(Money m) {
		Thread produceMoneyThread = new Thread(() -> {
			addUnitList.add(m);
			moneyList.add(m);
		});
		produceMoneyThread.start();
	}

	public static void remove(Unit u) {

		if (unitList.contains(u)) {
			removeUnitList.add(u);
			if (u instanceof Food) {
				removeFoodList.add(u);
			} else if (u instanceof Money) {
				removeMoneyList.add(u);
			} else if (u instanceof Fish) {
				removeFishList.add(u);
			} else if (u instanceof Monster) {
				removeMonsterList.add(u);
			}
		}

	}

	public static void clear() {
		unitList.clear();

		foodList.clear();
		moneyList.clear();
		fishList.clear();
		monsterList.clear();

		addUnitList.clear();
		addFishList.clear();
		addMonsterList.clear();

		removeFoodList.clear();
		removeMoneyList.clear();
		removeFishList.clear();
		removeMonsterList.clear();

	}

	public static void clearRemoveList() {
		removeFoodList.clear();
		removeMoneyList.clear();
		removeFishList.clear();
		removeMonsterList.clear();
	}

	public static void endInvasion(long invasionDuration) {
		// Add TIME ti Hunger AND Production to every fish
		for (Fish f : fishList) {
			f.getHunger().endInvasion(invasionDuration);
			f.getProduction().endInvasion(invasionDuration);
		}
	}

	public static void continuePause(long duration) {
		//continue pause
		for (Fish f : fishList) {
			f.getHunger().addLastFed(duration);
			f.getIdle().addNextIdle(duration);
		}
		for (Money m : moneyList) {
			m.addDisappearTime(duration);
		}

		for (Monster m : monsterList) {
			m.continuePause(duration);
		}
		InvasionManager.addInvasionTime(duration);
	}

	public static ArrayList<Unit> getRemoveUnitList() {
		return removeUnitList;
	}

	public static ArrayList<Unit> getRemoveFishList() {
		return removeFishList;
	}

	public static ArrayList<Unit> getRemoveFoodList() {
		return removeFoodList;
	}

	public static ArrayList<Unit> getRemoveMoneyList() {
		return removeMoneyList;
	}

	public static ArrayList<Unit> getRemoveMonsterList() {
		return removeMonsterList;
	}

}
