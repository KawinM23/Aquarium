package manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import model.Food;
import model.base.Fish;
import model.base.Money;
import model.base.Monster;
import model.base.Unit;
import properties.Renderable;

public class TankManager {

	static Random rand = new Random();

	private static ArrayList<Unit> unitList = new ArrayList<Unit>();

	private static ArrayList<Fish> fishList = new ArrayList<Fish>();
	private static ArrayList<Food> foodList = new ArrayList<Food>();
	private static ArrayList<Money> moneyList = new ArrayList<Money>();
	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();

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
		for (Unit u : unitList) {
			if (u instanceof Renderable) {
				((Renderable) u).render(gc);
			}
		}
	}

	public static void update() {
		// Update and draw all unit
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
		for (Monster m : monsterList) {
			m.update(GameManager.getFRAMERATE());
		}
		monsterList.removeAll(removeMonsterList);
		removeMonsterList.clear();
	}

	public static void add(Unit u) {
		unitList.add(u);
		if (u instanceof Food) {
			foodList.add((Food) u);
		} else if (u instanceof Money) {
			moneyList.add((Money) u);
		} else if (u instanceof Fish) {
			fishList.add((Fish) u);
		} else if (u instanceof Monster) {
			monsterList.add((Monster) u);
		}
	}

	public static void addNewFish(Fish f) {
		// rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		double posX = 0 + (GameManager.getWIDTH() - f.getWidth() - 0) * rand.nextDouble();
		double posY = GameManager.getTOPHEIGHT()
				+ (GameManager.getBOTTOMHEIGHT() - f.getHeight() - GameManager.getTOPHEIGHT()) * rand.nextDouble();
		f.setPos(posX, posY / 2);
		unitList.add(f);
		fishList.add(f);
		// Random Hunger Fish
		f.getHunger().addLastFedRandom(3, 6);

		// Stat Tracker
		StatTracker.addFishBought();
	}

	public static void addBornedFish(Fish f) {
		unitList.add(f);
		fishList.add(f);
		// Random Hunger Fish
		f.getHunger().addLastFedRandom(3, 6);

		// Stat Tracker
		StatTracker.addFishBought();
	}

	public static boolean addFood(Food f) {
		if (!(foodList.size() >= PlayerController.getMaxFood())) {
			unitList.add(f);
			foodList.add(f);
			return true;
		} else {
			return false;
		}
	}

	public static void produceMoney(Money m) {
		unitList.add(m);
		moneyList.add(m);
	}

	public static void remove(Unit u) {

		if (unitList.contains(u)) {
			Iterator<Unit> itr = unitList.iterator();
			while (itr.hasNext()) {
				Unit unit = itr.next();
				if (unit.equals(u)) {
					itr.remove();

				}
			}
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
		// TODO continue pause
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

}
