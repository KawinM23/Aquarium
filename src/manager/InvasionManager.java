package manager;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import model.base.Monster;

public class InvasionManager {
	private static Random rand = new Random();

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

			// TODO show warning
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
					setShowWarning(false);
				}
			});
			warningThread.start();
		} else if (!isInvaded && System.nanoTime() >= invasionTime) {
			startInvasion();
		} else if (isInvaded && TankManager.getMonsterCount() == 0) {
			endInvasion();
		}
	}

	public static void startInvasion() {
		System.out.println("Invading");
		setInvaded(true);
		setWarning(false);
		setShowWarning(false);
		// ADD MONSTER
		if (invasion < invasionList.size()) {
			for (Monster m : invasionList.get(invasion)) {
				TankManager.add(m);
			}
		} else {
			for (Monster m : invasionList.get(invasionList.size() - 1)) {
				TankManager.add(m);
			}
		}
	}

	public static void endInvasion() {
		System.out.println("END INVASION");
		setInvaded(false);
		setInvasion(invasion + 1);
		TankManager.endInvasion(getInvasionDuration());
		if (invasion < invasionList.size()) {
			setInvasionTime((long) (System.nanoTime() + (invasionTimeList[invasion] * 1e9)));
		} else {
			// rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			double min = invasionTimeList[invasionTimeList.length - 2];
			double max = invasionTimeList[invasionTimeList.length - 1];
			long randomTime = (long) ((min + ((max - min) * rand.nextDouble())) * 1.0e9);

			setInvasionTime((long) (System.nanoTime() + randomTime));
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
	
	public static void setStartInvasionTime() {
		InvasionManager.invasionTime = (long) (System.nanoTime() + (invasionTimeList[0] * 1.0e9));
	}

	public static void setNextInvasionTime(int nextDuration) {
		InvasionManager.invasionTime = (long) (System.nanoTime() + (nextDuration * 1.0e9));
	}

	public static void addInvasionTime(long duration) {
		InvasionManager.invasionTime = invasionTime + duration;
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
		if (isShowWarning()) {
			gc.fillText("Warning", 0, 400);
		}

	}
}
