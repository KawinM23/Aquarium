package manager;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import model.Food;
import model.base.Fish;
import model.base.Money;
import model.base.Monster;
import model.base.Unit;

public class TankManager {

	private static ArrayList<Unit> unitList = new ArrayList<Unit>();

	private static ArrayList<Fish> fishList = new ArrayList<Fish>();
	private static ArrayList<Food> foodList = new ArrayList<Food>();
	private static ArrayList<Money> moneyList = new ArrayList<Money>();
	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();

	private static ArrayList<Unit> removeFishList = new ArrayList<Unit>();

	public static ArrayList<Unit> getUnitList() {
		return unitList;
	}

	public static void setUnitList(ArrayList<Unit> unitList) {
		TankManager.unitList = unitList;
	}

	public static ArrayList<Fish> getFishList() {
		return fishList;
	}

	public static void setFishList(ArrayList<Fish> fishList) {
		TankManager.fishList = fishList;
	}

	public static ArrayList<Food> getFoodList() {
		return foodList;
	}

	public static void setFoodList(ArrayList<Food> foodList) {
		TankManager.foodList = foodList;
	}

	public static void update(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for (Unit u : unitList) {
			u.draw(gc);
		}
		for (Fish f : fishList) {
			f.update(GameManager.getFRAMERATE());
		}
		fishList.removeAll(removeFishList);
		for (Food f : foodList) {
			f.update(GameManager.getFRAMERATE());
		}
		for (Money m : moneyList) {
			m.update(GameManager.getFRAMERATE());
		}
		for (Monster m : monsterList) {
			m.update(GameManager.getFRAMERATE());
		}
//		unitList.removeAll(removeList);
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
			if (u instanceof Fish) {
				removeFishList.add(u);
//				Iterator<Fish> fishItr = fishList.iterator();
//				while (fishItr.hasNext()) {
//					Fish unit = fishItr.next();
//					if (unit.equals(u)) {
//						fishItr.remove();
//					}
//				}
			}
			if (u instanceof Food) {
				Iterator<Food> foodItr = foodList.iterator();
				while (foodItr.hasNext()) {
					Food unit = foodItr.next();
					if (unit.equals(u)) {
						foodItr.remove();
					}
				}
			}
		}
	}

}
