package properties;

import java.sql.Time;
import java.time.*;

public class Hunger {
	private boolean isHungry;
	private double cooldown;
	private long startingTime;
	private long deltaTime;
	
	
	public Hunger() {
		super();
		this.isHungry = false;
		this.cooldown = 10f;
		startingTime = System.currentTimeMillis();
	}
	
	public Hunger(boolean isHungry) {
		super();
		this.isHungry = isHungry;
	}

	public boolean isHungry() {
		return this.isHungry;
	}
	
	public void setHungry(boolean isHungry) {
		this.isHungry = isHungry;
	}
	
	
	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	public long getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(long startingTime) {
		this.startingTime = startingTime;
	}

	public long getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(long deltaTime) {
		this.deltaTime = deltaTime;
	}

	public String toString() {
		return ""+deltaTime;
	}
}
