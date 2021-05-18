package properties;

import model.base.Unit;

public class Idle {

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
			this.nextIdle = (long) (System.nanoTime() + 7e9); // TODO interval idle
		}
	}

	public void checkIdleX() {
		if (System.nanoTime() <= nextIdle) {
			this.unit.setVel(velX, unit.getVelY());
		} else {
			randomVel();
			this.unit.setVel(velX, unit.getVelY());
			this.nextIdle = (long) (System.nanoTime() + 7e9); // TODO interval idle
		}
	}

	public void addNextIdle(long duration) {
		this.nextIdle = this.nextIdle + duration;
	}

	public void resetIdle() {
		randomVel();
		this.nextIdle = (long) (System.nanoTime() + 7e9);
	}

	public void resetIdle(double time) {
		randomVel();
		this.nextIdle = (long) (System.nanoTime() + (time * 1e9));
	}

	public void eatFood() {
		setVelX(unit.getVelX() * 0.2);
		setVelY(unit.getVelY() * 0.2);
		this.nextIdle = (long) (System.nanoTime() + 1e9);
	}

	public void slowIdle() {
		setVelX(unit.getVelX() * (Math.random() / 2));
		setVelY(unit.getVelY() * (Math.random() / 2));
		this.nextIdle = (long) (System.nanoTime() + 1e9);
	}

	private void randomVel() {
		double angle = Math.random() * 360;
		this.setVelX(Math.cos(angle) * getSpeed());
		this.setVelY(Math.sin(angle) * getSpeed());
	}

	public long getLastIdle() {
		return nextIdle;
	}

	public void setLastIdle(long lastIdle) {
		this.nextIdle = lastIdle;
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
