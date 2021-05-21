package manager;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.base.Monster;
import model.base.Unit;
import model.monster.Destructor;
import model.monster.Gus;

public class InvasionManager {
	private static Random rand = new Random();

	private static boolean isInvaded = false;
	private static boolean warning = false;
	private static boolean showWarning = false;
	private static int invasion = 0;
	private static long invasionTime;
	private static int[] invasionTimeList;
	private static ArrayList<ArrayList<Monster>> invasionList = new ArrayList<ArrayList<Monster>>();

	private static boolean hasGus;
	private static boolean hasDestructor;

	public static void update() {
		if (!isInvaded && invasionTime - System.nanoTime() <= 7e9 && warning != true) {
			setWarning(true);
			SoundManager.playWarningSound();
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
		} else if (!isInvaded && System.nanoTime() >= invasionTime && invasionTimeList[0] != 0) {
			startInvasion();
		} else if (isInvaded && TankManager.getMonsterCount() == 0) {
			endInvasion();
		}
	}

	public static void render(GraphicsContext gc) {
		if (isShowWarning()) {
			gc.fillText("Warning", 0, 400);
		}

	}

	public static void startInvasion() {
		// SOUND
		SoundManager.playAlienScreamSound();
		SoundManager.changeBgmTo(5);

		System.out.println("Invading");
		setInvaded(true);
		setWarning(false);
		setShowWarning(false);
		setHasGus(false);
		setHasDestructor(false);
		// ADD MONSTER
		if (invasion < invasionList.size()) {
			for (Monster m : invasionList.get(invasion)) {
				if (m instanceof Gus) {
					setHasGus(true);
				}
				if (m instanceof Destructor) {
					setHasDestructor(true);
				}
				TankManager.addMonster(m);
			}
		} else {
			for (Monster m : invasionList.get(invasionList.size() - 1)) {
				if (m instanceof Gus) {
					setHasGus(true);
				}
				if (m instanceof Destructor) {
					setHasDestructor(true);
				}
				TankManager.addMonster(m);
				TankManager.addMonster(m);
			}
		}

	}

	public static void endInvasion() {
		// SOUND
		SoundManager.playAlienDieSound();
		SoundManager.changeBgmTo(ViewManager.getCurrentTank());

		System.out.println("END INVASION");
		setInvaded(false);
		setHasGus(false);
		setHasDestructor(false);
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

	public static void clear() {
		setInvaded(false);
		setWarning(false);
		setShowWarning(false);
		setInvasion(0);
		setInvasionTime(0);
		setInvasionTimeList(new int[] { 0 });
		setInvasionList(new ArrayList<>());

		setHasGus(false);
		setHasDestructor(false);
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

	public static boolean isHasGus() {
		return hasGus;
	}

	public static void setHasGus(boolean hasGus) {
		InvasionManager.hasGus = hasGus;
	}

	public static boolean isHasDestructor() {
		return hasDestructor;
	}

	public static void setHasDestructor(boolean hasDestructor) {
		InvasionManager.hasDestructor = hasDestructor;
	}
}
