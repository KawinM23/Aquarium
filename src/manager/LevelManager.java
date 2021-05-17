package manager;

import java.util.ArrayList;

import model.base.Fish;
import model.base.Unit;
import model.fish.Carnivore;
import model.fish.Guppy;

public class LevelManager {
	// TODO
	// Store Each Level Information
	// Level,StartMoney,StartFish,ShopDetail,InvasionDetail

	private Level level1_1 = new Level("Adventure", 1, 1);
	private Level level1_2 = new Level("Adventure", 1, 1);
	private Level level1_4 = new Level("Adventure", 1, 4);

	public Level getLevel1_1() {
		return level1_1;
	}

	public Level getLevel1_2() {
		return level1_2;
	}

	public Level getLevel1_4() {
		return level1_4;
	}

	public void loadLevel1_1(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[4];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = null;
		shopItem[2] = null; 

		level.setFoodUpgrable(false);
		level.setWeaponUpgrable(false);

		level.setGoalPrice(150);
		
	}
	
	public void loadLevel1_2(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[4];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Carnivore("Carnivore", 0, 0);
		shopItem[2] = null; 

		level.setFoodUpgrable(true);
		level.setWeaponUpgrable(false);

		level.setGoalPrice(500);
		
	}
	
	public void loadLevel1_3(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[4];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Carnivore("Carnivore", 0, 0);
		shopItem[2] = null; 

		level.setFoodUpgrable(true);
		level.setWeaponUpgrable(true);

		level.setGoalPrice(2000);
		
	}
	
	public void loadLevel1_4(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = null; 
		shopItem[2] = null; 

		level.setFoodUpgrable(true);
		level.setWeaponUpgrable(true);

		level.setGoalPrice(3000);
		
	}
}