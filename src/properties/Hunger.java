package properties;

import java.util.Random;

public class Hunger {

	private static Random rand = new Random();

	private Class<?> foodType;

	private long lastFed;
	private double cooldown;
	private double lifetime;

	public Hunger(Class<?> foodType, double cooldown, double lifetime) {
		super();
		this.foodType = foodType;
		this.lastFed = System.nanoTime();
		this.cooldown = cooldown;
		this.lifetime = lifetime;
	}

	public int checkHunger() {
		double deltaTime = getDeltaTime();
		if (lifetime != 0 && deltaTime > lifetime) {
			return 3;
		} else if (lifetime != 0 && lifetime - deltaTime < 5) {
			return 2;
		} else if (cooldown != 0 && deltaTime > cooldown) {
			return 1;
		} else {
			return 0;
		}
	}

	public Class<?> getFoodType() {
		// 0 food
		// 1 MiniGuppy
		// 2 Coin
		return foodType;
	}

	public void setFoodType(Class<?> foodType) {
		this.foodType = foodType;
	}

	public long getLastFed() {
		return lastFed;
	}

	public void setLastFed(long lastFed) {
		this.lastFed = lastFed;
	}

	public void addLastFed(long duration) {
		this.setLastFed(getLastFed() + duration);
	}

	public void setLastFedNow() {
		this.setLastFed(System.nanoTime());
	}

	public void addLastFedRandom(int min, int max) {
		// rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		long randomTime = (long) ((min + ((max - min) * rand.nextDouble())) * 1.0e9);
		this.setLastFed(getLastFed() + randomTime);
	}

	public double getDeltaTime() {
		return (System.nanoTime() - getLastFed()) / 1.0e9;
	}

	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	public void endInvasion(long invasionDuration) {
		setLastFed(lastFed + invasionDuration);
		if (getDeltaTime() > cooldown) {
			setLastFed((long) (System.nanoTime() - (cooldown * 1e9)));
		}
	}

}
