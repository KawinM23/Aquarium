package properties;

import model.base.Unit;

public class Hunger {

	private Unit food;
	private long lastFed;
	private double cooldown;
	private double lifetime;

	public Hunger(Unit food, double cooldown, double lifetime) {
		super();
		this.food = food;
		this.lastFed = System.nanoTime();
		this.cooldown = cooldown;
		this.lifetime = lifetime;
	}

	public int checkHunger() {
		double deltaTime = (System.nanoTime() - getLastFed()) / 1.0e9;
		if (deltaTime > lifetime) {
			return 2;
		} else if (deltaTime > cooldown) {
			return 1;
		} else {
			return 0;
		}

	}

	public Unit getFood() {
		return food;
	}

	public void setFood(Unit food) {
		this.food = food;
	}

	public long getLastFed() {
		return lastFed;
	}

	public void setLastFed(long lastFed) {
		this.lastFed = lastFed;
	}

	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

}
