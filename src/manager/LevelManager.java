package manager;

import java.util.ArrayList;

import model.Food;
import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import model.fish.Beetlemuncher;
import model.fish.Carnivore;
import model.fish.Guppy;
import model.fish.Guppycruncher;
import model.fish.Starcatcher;
import model.fish.Ultravore;
import model.monster.Balrog;
import model.monster.Destructor;
import model.monster.Gus;
import model.monster.Sylvester;

public class LevelManager {
	// Store Each Level Information
	// Level,StartMoney,StartFish,ShopDetail,InvasionDetail

	private static Level level1_1 = new Level("Adventure", 1, 1);
	private static Level level1_2 = new Level("Adventure", 1, 2);
	private static Level level1_3 = new Level("Adventure", 1, 3);
	private static Level level1_4 = new Level("Adventure", 1, 4);

	private static Level level2_1 = new Level("Adventure", 2, 1);
	private static Level level2_2 = new Level("Adventure", 2, 2);
	private static Level level2_3 = new Level("Adventure", 2, 3);
	private static Level level2_4 = new Level("Adventure", 2, 4);

	private static Level level3_1 = new Level("Adventure", 3, 1);
	private static Level level3_2 = new Level("Adventure", 3, 2);
	private static Level level3_3 = new Level("Adventure", 3, 3);
	private static Level level3_4 = new Level("Adventure", 3, 4);

	private static Level level4_1 = new Level("Adventure", 4, 1);
	private static Level level4_2 = new Level("Adventure", 4, 2);
	private static Level level4_3 = new Level("Adventure", 4, 3);
	private static Level level4_4 = new Level("Adventure", 4, 4);

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

		// Invasion
		level.setInvasionMonster(new ArrayList<>());

		int[] invasionTime = new int[] { 0 };
		level.setInvasionTime(invasionTime);
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

		// Invasion
		Sylvester sv = new Sylvester("Sylvester", 0, 0, 0);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(sv);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel1_3(Level level) {
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
		level.setWeaponUpgradable(true);

		level.setGoalPrice(1000);

		// Invasion
		Sylvester sv = new Sylvester("Sylvester", 0, 0, 0);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(sv);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel1_4(Level level) {
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
		level.setWeaponUpgradable(true);

		level.setGoalPrice(2000);

		// Invasion
		Balrog br = new Balrog("Balrog", 0, 0, 0);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(br);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel2_1(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Food("Potion", 0, 0, 2);
		shopItem[2] = null;
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(false);

		level.setGoalPrice(500);

		// Invasion
		Sylvester sv = new Sylvester("Sylvester", 0, 0, 120);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(sv);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel2_2(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Food("Potion", 0, 0, 2);
		shopItem[2] = new Starcatcher("Starcatcher", 0, 0);
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(1000);

		// Invasion
		Balrog br = new Balrog("Balrog", 0, 0, 250);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(br);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel2_3(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Food("Potion", 0, 0, 2);
		shopItem[2] = new Starcatcher("Starcatcher", 0, 0);
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(2000);

		// Invasion
		Gus g = new Gus("Gus", 0, 0, 0);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(g);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel2_4(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Food("Potion", 0, 0, 2);
		shopItem[2] = new Starcatcher("Starcatcher", 0, 0);
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(4000);

		// Invasion
		Destructor d = new Destructor("Destructor", 0, 0, 0);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(d);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel3_1(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Guppycruncher("Guppycruncher", 0, 0);
		shopItem[2] = null;
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(false);

		level.setGoalPrice(500);

		// Invasion
		Balrog br = new Balrog("Balrog", 0, 0, 280);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(br);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel3_2(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Guppycruncher("Guppycruncher", 0, 0);
		shopItem[2] = new Beetlemuncher("Beetlemuncher", 0, 0);
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(1000);

		// Invasion
		Gus g = new Gus("Gus", 0, 0, 350);
		Destructor d = new Destructor("Destructor", 0, 0, 350);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(g);
		wave1.add(d);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel3_3(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Guppycruncher("Guppycruncher", 0, 0);
		shopItem[2] = new Beetlemuncher("Beetlemuncher", 0, 0);
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(2000);

		// Invasion
		Sylvester sv = new Sylvester("Sylvester", 0, 0, 300);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(sv);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel3_4(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Guppycruncher("Guppycruncher", 0, 0);
		shopItem[2] = new Beetlemuncher("Beetlemuncher", 0, 0);
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(4000);

		// Invasion
		Balrog br = new Balrog("Balrog", 0, 0, 350);
		Sylvester sv = new Sylvester("Sylvester", 0, 0, 250);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(br);
		wave1.add(sv);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel4_1(Level level) {
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

		level.setGoalPrice(1000);

		// Invasion
		Balrog br = new Balrog("Balrog", 0, 0, 300);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(br);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel4_2(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Carnivore("Carnivore", 0, 0);
		shopItem[2] = new Ultravore("Ultravore", 0, 0);
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(true);

		level.setGoalPrice(3000);

		// Invasion
		Balrog br = new Balrog("Balrog", 0, 0, 300);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(br);

		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel4_3(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Carnivore("Carnivore", 0, 0);
		shopItem[2] = new Ultravore("Ultravore", 0, 0);

		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(false);

		level.setGoalPrice(5000);

		// Invasion
		Gus g = new Gus("Gus", 0, 0, 400);
		Balrog br = new Balrog("Balrog", 0, 0, 350);
		Sylvester sv = new Sylvester("Sylvester", 0, 0, 250);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(g);
		ArrayList<Monster> wave2 = new ArrayList<Monster>();
		wave2.add(sv);
		wave2.add(br);
		
		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);
		level.getInvasionMonster().add(wave2);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadLevel4_4(Level level) {
		level.setStartingMoney(200);

		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level.setStartingFish(startingFishList);

		Unit[] shopItem = new Unit[3];
		shopItem[0] = new Guppy("Guppy", 0, 0);
		shopItem[1] = new Carnivore("Carnivore", 0, 0);
		shopItem[2] = new Ultravore("Ultravore", 0, 0);
		level.setShopItem(shopItem);

		level.setFoodUpgradable(true);
		level.setWeaponUpgradable(false);

		level.setGoalPrice(10000);

		// Invasion
		Gus g = new Gus("Gus", 0, 0, 500);
		Balrog br = new Balrog("Balrog", 0, 0, 350);
		Sylvester sv = new Sylvester("Sylvester", 0, 0, 300);
		ArrayList<Monster> wave1 = new ArrayList<Monster>();
		wave1.add(g);
		ArrayList<Monster> wave2 = new ArrayList<Monster>();
		wave2.add(sv);
		wave2.add(br);
		
		level.setInvasionMonster(new ArrayList<>());
		level.getInvasionMonster().add(wave1);
		level.getInvasionMonster().add(wave2);

		int[] invasionTime = new int[] { 30, 40, 50 };
		level.setInvasionTime(invasionTime);

	}

	public static void loadAllLevel() {
		loadLevel1_1(level1_1);
		loadLevel1_2(level1_2);
		loadLevel1_3(level1_3);
		loadLevel1_4(level1_4);

		loadLevel2_1(level2_1);
		loadLevel2_2(level2_2);
		loadLevel2_3(level2_3);
		loadLevel2_4(level2_4);

		loadLevel3_1(level3_1);
		loadLevel3_2(level3_2);
		loadLevel3_3(level3_3);
		loadLevel3_4(level3_4);

		loadLevel4_1(level4_1);
		loadLevel4_2(level4_2);
		loadLevel4_3(level4_3);
		loadLevel4_4(level4_4);

	}

	public static Level getLevel(int tankNumber, int levelNumber) {
		switch (tankNumber) {
		case 1:
			switch (levelNumber) {
			case 1:
				return level1_1;
			case 2:
				return level1_2;
			case 3:
				return level1_3;
			case 4:
				return level1_4;
			default:
			}
			break;
		case 2:
			switch (levelNumber) {
			case 1:
				return level2_1;
			case 2:
				return level2_2;
			case 3:
				return level2_3;
			case 4:
				return level2_4;
			default:
			}
			break;
		case 3:
			switch (levelNumber) {
			case 1:
				return level3_1;
			case 2:
				return level3_2;
			case 3:
				return level3_3;
			case 4:
				return level3_4;
			default:
			}
			break;
		case 4:
			switch (levelNumber) {
			case 1:
				return level4_1;
			case 2:
				return level4_2;
			case 3:
				return level4_3;
			case 4:
				return level4_4;
			default:
			}
			break;
		default:
		}
		return level1_1;
	}

	public static Level getLatestLevel() {
		int tankNumber = JSONManager.getTank();
		int levelNumber = JSONManager.getLevel();
		return getLevel(tankNumber, levelNumber);
	}
}
