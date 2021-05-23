package manager;

public class PlayerController {

	private static boolean isPlaying = false;
	private static boolean isPause = false;
	private static boolean isBack = false;
	private static long pauseTime;
	
	private static int money;
	private static int maxFood;
	private static int foodLevel;
	private static boolean isPotion;// Is next food Potion?
	private static int gunLevel;
	private static int goal; // GOAL Tracker

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
			SoundManager.playErrorSound();
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

	@SuppressWarnings("deprecation")
	private static void win() {
		// TODO Winning ,Change scene
		System.out.println("WIN");
		setPlaying(false);
		GameManager.getTankThread().stop();

		GameManager.winLevel();
		StatTracker.calculateStat();
		
		SoundManager.playWinSound();
		SceneController.changeScene("Win");
		clear();
	}

	@SuppressWarnings("deprecation")
	private static void lose() {
		// TODO Losing ,Change scene
		setPlaying(false);
		GameManager.getTankThread().stop();
		System.out.println("LOSE");
		StatTracker.calculateStat();
		
		SoundManager.playLoseSound();
		SceneController.changeScene("Lose");
		clear();
	}

	public static void clear() {
		setMoney(0);
		setPlaying(false);
		setPause(false);
		setBack(false);
		setPotion(false);
		setMaxFood(1);
		setFoodLevel(1);
		setGunLevel(1);
		setGoal(0);
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
		// TODO Gun Damage
		switch (gunLevel) {
		case 1:
			return 10;
		case 2:
			return 13;
		case 3:
			return 17;
		case 4:
			return 20;
		case 5:
			return 24;
		case 6:
			return 27;
		case 7:
			return 31;
		case 8:
			return 34;
		case 9:
			return 38;
		case 10:
			return 41;
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

	public static boolean isBack() {
		return isBack;
	}

	public static void setBack(boolean isBack) {
		PlayerController.isBack = isBack;
	}
}
