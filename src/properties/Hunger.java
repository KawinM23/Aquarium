package properties;

import model.base.Unit;

public class Hunger {
	
	
	private boolean isHungry;
	private Unit food;
	private long lastFed;
	private double cooldown;
	
	
	public Hunger(Unit food, double cooldown) {
		super();
		this.isHungry = false;
		this.food = food;
		this.lastFed = System.nanoTime();
		this.cooldown = cooldown;
	}


	public boolean isHungry() {
		return isHungry;
	}

	public void setHungry(boolean isHungry) {
		this.isHungry = isHungry;
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
