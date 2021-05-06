package properties;

public class Hunger {

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
		double deltaTime = (System.nanoTime() - getLastFed()) / 1.0e9;
		if (lifetime != 0 && deltaTime > lifetime) {
			return 2;
		} else if (cooldown != 0 && deltaTime > cooldown) {
			return 1;
		} else {
			return 0;
		}
	}

	public void resetTime() {
		this.setLastFed(System.nanoTime());
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

	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

}
