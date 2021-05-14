package manager;

import java.util.ArrayList;

import model.base.Monster;

public class MonsterManager {
	private static boolean isInvaded = false;
	private static int invasion = 0;
	private static long invasionTime;
	private static ArrayList<ArrayList<Monster>> invasionList = new ArrayList<ArrayList<Monster>>();

	public static void update() {
		if (!isInvaded && System.nanoTime() >= invasionTime) {
			System.out.println("Invading");
			setInvaded(true);
			// ADD MONSTER
			if (invasion<invasionList.size()) {
				for(Monster m : invasionList.get(invasion)) {
					TankManager.add(m);
				}
			}
		} else if (isInvaded && TankManager.getMonsterCount() == 0) {
			setInvaded(false);
			setInvasion(invasion + 1);
			TankManager.endInvasion(getInvasionDuration());
			setInvasionTime((long) (System.nanoTime() + 20e10));
		}
	}

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

	public static ArrayList<ArrayList<Monster>> getInvasionList() {
		return invasionList;
	}

	public static void setInvasionList(ArrayList<ArrayList<Monster>> invasionList) {
		MonsterManager.invasionList = invasionList;
	}
}
