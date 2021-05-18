package manager;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import model.base.Monster;

public class InvasionManager {
	private static boolean isInvaded = false;
	private static boolean warning = false;
	private static boolean showWarning = false;
	private static int invasion = 0;
	private static long invasionTime;
	private static int[] invasionTimeList;
	private static ArrayList<ArrayList<Monster>> invasionList = new ArrayList<ArrayList<Monster>>();

	public static void update() {
		if (!isInvaded && invasionTime - System.nanoTime() <= 7e9 && warning != true) {
			setWarning(true);
			
			//TODO show warning
			Thread warningThread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (isWarning()) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (isShowWarning()) {
							setShowWarning(false);
						} else {
							setShowWarning(true);
						}
					}
				}
			});
			warningThread.start();
		} else if (!isInvaded && System.nanoTime() >= invasionTime) {
			System.out.println("Invading");
			setInvaded(true);
			setWarning(false);
			setShowWarning(false);
			// ADD MONSTER
			if (invasion < invasionList.size()) {
				for (Monster m : invasionList.get(invasion)) {
					TankManager.add(m);
				}
			}
		} else if (isInvaded && TankManager.getMonsterCount() == 0) {
			System.out.println("END INVASION");
			setInvaded(false);
			setInvasion(invasion + 1);
			TankManager.endInvasion(getInvasionDuration());
			setInvasionTime((long) (System.nanoTime() + (invasionTimeList[invasion] * 1e9)));
		}
	}

	public static boolean isInvaded() {
		return isInvaded;
	}

	public static void setInvaded(boolean isInvaded) {
		InvasionManager.isInvaded = isInvaded;
	}

	public static boolean isWarning() {
		return warning;
	}

	public static void setWarning(boolean warning) {
		InvasionManager.warning = warning;
	}

	public static boolean isShowWarning() {
		return showWarning;
	}

	public static void setShowWarning(boolean showWarning) {
		InvasionManager.showWarning = showWarning;
	}

	public static long getInvasionTime() {
		return invasionTime;
	}

	public static long getInvasionDuration() {
		return System.nanoTime() - invasionTime;
	}

	public static void setInvasionTime(long invasionTime) {
		InvasionManager.invasionTime = invasionTime;
	}

	public static int getInvasion() {
		return invasion;
	}

	public static void setInvasion(int invasion) {
		InvasionManager.invasion = invasion;
	}

	public static int[] getInvasionTimeList() {
		return invasionTimeList;
	}

	public static void setInvasionTimeList(int[] invasionTimeList) {
		InvasionManager.invasionTimeList = invasionTimeList;
	}

	public static ArrayList<ArrayList<Monster>> getInvasionList() {
		return invasionList;
	}

	public static void setInvasionList(ArrayList<ArrayList<Monster>> invasionList) {
		InvasionManager.invasionList = invasionList;
	}

	public static void render(GraphicsContext gc) {
		if(isShowWarning()) {
			gc.fillText("Warning", 0, 400);
		}
		
	}
}
