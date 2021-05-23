package manager;

public class StatTracker {

	private static int moneyGained;
	private static int fishBought;
	private static long startTime;
	private static int foodBought;
	private static int monsterDefeated;
	
	public static void calculateStat() {
		//JSON Calculate
		addStatToJSON();
		
	}

	private static void addStatToJSON() {
		JSONManager.addMoneyGained(getMoneyGained());
		JSONManager.addFishBought(getFishBought());
		JSONManager.addPlayTime(getDuration());
		JSONManager.addFoodBought(getFoodBought());
		JSONManager.addMonsterDefeated(getMonsterDefeated());
		
		JSONManager.writeJSON();
	}

	public static void clear() {
		setMoneyGained(0);
		setFishBought(0);
		setStartTime(System.nanoTime());
		setFoodBought(0);
		setMonsterDefeated(0);
	}

	public static int getMoneyGained() {
		return moneyGained;
	}

	public static void setMoneyGained(int moneyGained) {
		StatTracker.moneyGained = moneyGained;
	}

	public static void addMoneyGain(int moneyGained) {
		StatTracker.moneyGained = StatTracker.moneyGained + moneyGained;
	}

	public static int getFishBought() {
		return fishBought;
	}

	public static void setFishBought(int fishBought) {
		StatTracker.fishBought = fishBought;
	}

	public static void addFishBought() {
		StatTracker.fishBought = StatTracker.fishBought + 1;
	}

	public static long getStartTime() {
		return startTime;
	}

	public static void setStartTime(long startTime) {
		StatTracker.startTime = startTime;
	}

	public static long getDuration() {
		return System.nanoTime() - StatTracker.startTime;
	}

	public static int getFoodBought() {
		return foodBought;
	}

	public static void setFoodBought(int foodBought) {
		StatTracker.foodBought = foodBought;
	}

	public static void addFoodBought() {
		StatTracker.foodBought = StatTracker.foodBought + 1;
	}

	public static int getMonsterDefeated() {
		return monsterDefeated;
	}

	public static void setMonsterDefeated(int monsterDefeated) {
		StatTracker.monsterDefeated = monsterDefeated;
	}

	public static void addMonsterDefeated() {
		StatTracker.monsterDefeated = StatTracker.monsterDefeated + 1;
	}

}
