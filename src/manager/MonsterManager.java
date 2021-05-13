package manager;

public class MonsterManager {
	private static boolean isInvaded;
	private static int invasion;
	private static long invasionTime;

	public static boolean isInvaded() {
		return isInvaded;
	}

	public static void setInvaded(boolean isInvaded) {
		MonsterManager.isInvaded = isInvaded;
	}

	public static long getInvasionTime() {
		return invasionTime;
	}

	public static long getInvasionDuration() {
		return System.nanoTime() - invasionTime;
	}

	public static void setInvasionTime(long invasionTime) {
		MonsterManager.invasionTime = invasionTime;
	}

	public static int getInvasion() {
		return invasion;
	}

	public static void setInvasion(int invasion) {
		MonsterManager.invasion = invasion;
	}
}
