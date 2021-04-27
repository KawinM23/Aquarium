package properties;

import model.Food;
import model.base.Unit;

public class Hunger {

	private int foodType;
	
	private long lastFed;
	private double cooldown;
	private double lifetime;

	public Hunger(int foodType, double cooldown, double lifetime) {
		super();
		this.foodType = foodType;
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

	public int getFoodType() {
		return foodType;
	}

	public void setFoodType(int foodType) {
		this.foodType = foodType;
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
