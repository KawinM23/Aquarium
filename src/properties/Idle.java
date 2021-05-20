package properties;

import java.util.Random;

import model.base.Unit;

public class Idle {

	private static Random rand = new Random(0);

	private Unit unit;
	private long nextIdle;
	private int speed;
	private double velX;
	private double velY;

	public Idle(Unit u, int idleSpeed) {
		super();
		this.unit = u;
		this.nextIdle = System.nanoTime();
		this.speed = idleSpeed;
		this.velX = 0;
		this.velY = 0;
	}

	public void checkIdle() {
		if (System.nanoTime() <= nextIdle) {
			this.unit.setVel(velX, velY);
		} else {
			randomVel();
			this.unit.setVel(velX, velY);
			this.setNextIdleRandom(2, 7); // interval idle
		}
	}

	public void checkIdleX() {
		if (System.nanoTime() <= nextIdle) {
			this.unit.setVel(velX, unit.getVelY());
		} else {
			randomVel();
			this.unit.setVel(velX, unit.getVelY());
			this.setNextIdleRandom(3, 6); // interval idle
		}
	}

	public void addNextIdle(long duration) {
		this.nextIdle = this.nextIdle + duration;
	}

	public void setNextIdleRandom(double min, double max) {
		// rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		long randomTime = (long) ((min + ((max - min) * rand.nextDouble())) * 1.0e9);
		this.setNextIdle(System.nanoTime() + randomTime);
	}

	public void eatFood() {
		setVelX(unit.getVelX() * 0.2);
		setVelY(unit.getVelY() * 0.2);
		setNextIdleRandom(1, 1.75);
	}

	public void slowIdle() {
		setVelX(unit.getVelX() * (Math.random() / 2));
		setVelY(unit.getVelY() * (Math.random() / 2));
		this.nextIdle = (long) (System.nanoTime() + 1e9);
	}

	public void randomVel() {
		double angle = Math.random() * 360;
		this.setVelX(Math.cos(angle) * getSpeed());
		this.setVelY(Math.sin(angle) * getSpeed());
	}

	public long getNextIdle() {
		return nextIdle;
	}

	public void setNextIdle(long setNextIdle) {
		this.nextIdle = setNextIdle;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
