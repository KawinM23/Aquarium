package manager;

public class PlayerController {
	
	private static int money;
	private static boolean isPlaying;
	private static int maxFood;
	
	
	public static int getMoney() {
		return money;
	}
	public static void setMoney(int money) {
		PlayerController.money = money;
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
	
	
}
