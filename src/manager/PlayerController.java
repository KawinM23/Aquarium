package manager;

import com.sun.glass.ui.View;

public class PlayerController {

	private static int money = 10000;
	private static boolean isPlaying = true;
	private static boolean isPause = false;
	private static long pauseTime;

	private static boolean isPotion;//Is next food Potion?
	
	private static int maxFood;
	private static int foodLevel = 1;
	private static int gunLevel = 1;
	private static int goal = 0; //GOAL Tracker

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int money) {
		PlayerController.money = money;
	}

	public static void addMoney(int value) {
		PlayerController.money += value;
	}

	public static boolean buy(int value) {
		if (PlayerController.money >= value) {
			PlayerController.money -= value;
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPlaying() {
		return isPlaying;
	}

	public static void setPlaying(boolean isPlaying) {
		PlayerController.isPlaying = isPlaying;
	}

	public static void checkPlaying() {
		if (TankManager.getFishList().size() <= 0) {
			lose();
		} else if (checkGoal()) {
			win();
		}
	}

	private static void win() {
		// TODO Winning ,Change scene
		System.out.println("WIN");
		setPlaying(false);
		ViewManager.getTankThread().stop();
		
		ViewManager.winLevel();
		StatTracker.calculateStat();
	}

	private static void lose() {
		// TODO Losing ,Change scene
		setPlaying(false);
		ViewManager.getTankThread().stop();
		System.out.println("LOSE");
		StatTracker.calculateStat();
	}

	public static int getMaxFood() {
		return maxFood;
	}

	public static void setMaxFood(int maxFood) {
		PlayerController.maxFood = maxFood;
	}

	public static int getFoodLevel() {
		return foodLevel;
	}

	public static void setFoodLevel(int foodLevel) {
		PlayerController.foodLevel = foodLevel;
	}

	public static int getGunLevel() {
		return gunLevel;
	}

	public static void setGunLevel(int gunLevel) {
		PlayerController.gunLevel = gunLevel;
	}

	public static int getGunDamage() {
		switch (gunLevel) {
		case 1:
			return 10;
		case 2:
			return 25;
		}
		return 0;
	}

	public static int getGoal() {
		return goal;
	}

	public static void setGoal(int goal) {
		PlayerController.goal = goal;
	}

	public static void buyGoal() {
		if (goal < 3) {
			PlayerController.setGoal(goal + 1);
		}
	}

	public static boolean checkGoal() {
		if (goal == 3) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPause() {
		return isPause;
	}

	public static void setPause(boolean isPause) {
		PlayerController.isPause = isPause;
	}

	public static void togglePause() {
		if (isPause) {
			setPause(false);
			TankManager.continuePause(System.nanoTime() - pauseTime);
		} else {
			setPause(true);
			setPauseTime(System.nanoTime());
		}
	}

	public static long getPauseTime() {
		return pauseTime;
	}

	public static void setPauseTime(long pauseTime) {
		PlayerController.pauseTime = pauseTime;
	}

	public static boolean isPotion() {
		return isPotion;
	}

	public static void setPotion(boolean isPotion) {
		PlayerController.isPotion = isPotion;
	}

	public static void clear() {
		setMoney(0);
		setPlaying(false);
		setPause(false);
		setPotion(false);
		setMaxFood(1);
		setFoodLevel(1);
		setGunLevel(1);
		setGoal(0);
	}


}
