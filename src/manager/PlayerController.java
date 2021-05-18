package manager;

public class PlayerController {

	private static int money = 10000;
	private static boolean isPlaying = true;
	private static boolean isPause = false;
	private static long pauseTime;

	private static int maxFood;
	private static int foodLevel = 1;
	private static int gunLevel = 1;
	private static int goal = 0; // TODO GOAL Tracker

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

}
