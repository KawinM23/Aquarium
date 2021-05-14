package manager;

public class MonsterManager {
	private static boolean isInvaded = false;
	private static int invasion = 0;
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

	public static void update() {
		if (!isInvaded && System.nanoTime() >= invasionTime) {
			setInvaded(true);
			setInvasion(invasion + 1);
			// ADD MONSTER
		} else if (isInvaded && TankManager.getMonsterCount() == 0) {
			setInvaded(false);
			TankManager.endInvasion(getInvasionDuration());
		}
	}
}
