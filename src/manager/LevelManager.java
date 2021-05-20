package manager;

import java.util.ArrayList;

import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import model.fish.Carnivore;
import model.fish.Guppy;
import model.monster.Sylvester;

public class LevelManager {
	// Store Each Level Information
	// Level,StartMoney,StartFish,ShopDetail,InvasionDetail

	private static Level level1_1 = new Level("Adventure", 1, 1);
	private static Level level1_2 = new Level("Adventure", 1, 2);
	private static Level level1_4 = new Level("Adventure", 1, 4);

	public static Level getLevel1_1() {
		return level1_1;
	}

	public static Level getLevel1_2() {
		return level1_2;
	}

	public static Level getLevel1_4() {
		return level1_4;
	}

	public static void loadLevel1_1(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = null;
		shopItem[2] = null; 
		level.setShopItem(shopItem);
		
		level.setFoodUpgradable(false);
		level.setWeaponUpgradable(false);

		level.setGoalPrice(150);
	}
	
	public static void loadLevel1_2(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Carnivore("Carnivore", 0, 0);
		shopItem[2] = null; 
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(false);

		level.setGoalPrice(500);
		
		//Invasion
		Sylvester sv = new Sylvester("Sylvester", 0, 0);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(sv);
		
		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);
		
		int[] invasionTime = new int[]{30,40,50};
		level.setInvasionTime(invasionTime);
		
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

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(2000);
		
	}
	
	public static void loadLevel1_4(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = null; 
		shopItem[2] = null; 

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(3000);
		
	}
	
	public static void loadAllLevel() {
		loadLevel1_1(level1_1);
		loadLevel1_2(level1_2);
		loadLevel1_4(level1_4);
	}
}
