package manager;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import model.Food;
import model.base.Fish;
import model.base.Unit;

public class TankManager {

	
	private static ArrayList<Unit> unitList = new ArrayList<Unit>();
	
	private static ArrayList<Fish> fishList = new ArrayList<Fish>();
	private static ArrayList<Food> foodList = new ArrayList<Food>();

	
	
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
		for(Unit u:unitList) {
			u.draw(gc);
		}
		for(Fish f:fishList) {
			f.update(GameManager.getFRAMERATE());
		}
		for(Food f:foodList) {
			f.update(GameManager.getFRAMERATE());
		}
	}
	
	public static void remove(Unit u) {
		if(TankManager.unitList.contains(u)) {
			TankManager.unitList.remove(u);
			if(u.getClass().equals(Fish.class)){
				TankManager.fishList.remove(u);
			}
			if(u.getClass().equals(Food.class)){
				TankManager.foodList.remove(u);
			}
		}
	}
	
	
}
