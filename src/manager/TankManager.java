package manager;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import model.base.Fish;
import model.base.Unit;

public class TankManager {
	private static GraphicsContext gc;
	
	private static ArrayList<Unit> unitList;
	
	private static ArrayList<Fish> fishList;

	
	
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

	public static void update() {
		// TODO Auto-generated method stub
		for(Unit u:unitList) {
			u.draw(gc);
		}
		for(Fish f:fishList) {
			f.update();
		}
	}
	
	
}
