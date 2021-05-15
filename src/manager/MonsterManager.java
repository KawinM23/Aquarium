package manager;

import java.util.ArrayList;

import model.base.Monster;

public class MonsterManager {
	private static boolean isInvaded = false;
	private static boolean warning = false;
	private static int invasion = 0;
	private static long invasionTime;
	private static ArrayList<ArrayList<Monster>> invasionList = new ArrayList<ArrayList<Monster>>();

	public static void update() {
		if (!isInvaded && invasionTime - System.nanoTime() <= 7 && warning != true) {
			setWarning(true);
			Thread warningThread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Warning");
					if(warning==false) {
						Thread.currentThread().stop();
					}
				}

			});
			warningThread.start();
		} else if (!isInvaded && System.nanoTime() >= invasionTime) {
			System.out.println("Invading");
			setInvaded(true);
			setWarning(false);
			// ADD MONSTER
			if (invasion < invasionList.size()) {
				for (Monster m : invasionList.get(invasion)) {
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

	public static boolean isWarning() {
		return warning;
	}

	public static void setWarning(boolean warning) {
		MonsterManager.warning = warning;
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
