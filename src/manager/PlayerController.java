package manager;

public class PlayerController {
	private static int money;
	private static boolean isPlaying;
	
	
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
	
	
}
