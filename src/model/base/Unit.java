package model.base;

import javafx.geometry.Rectangle2D;
import manager.GameManager;

public abstract class Unit {
	private String name;

	private double posX;
	private double posY;
	private double width;
	private double height;

	private double velX;
	private double velY;
	private int speed;

	public Unit(String name, double posX, double posY) {
		super();
		this.name = name;
		this.posX = posX;
		this.posY = posY;
	}

	public abstract void update(int fr);

	/**
	 * Calculate distance between center of this unit and {@link Unit} U
	 *
	 * @param u The {@code Unit} that calculated with.
	 */
	public double distance(Unit u) {
		double deltaX = u.getCenterX() - this.getCenterX();
		double deltaY = u.getCenterY() - this.getCenterY();
		return Math.hypot(deltaX, deltaY);
	}

	/**
	 * Calculate distance between center of this unit and point(x,y)
	 */
	public double distance(double x, double y) {
		double deltaX = x - this.getCenterX();
		double deltaY = y - this.getCenterY();
		return Math.hypot(deltaX, deltaY);
	}

	public double distanceX(Unit u) {
		return u.getCenterX() - this.getCenterX();
	}

	public double distanceY(Unit u) {
		return u.getCenterY() - this.getCenterY();
	}

	// HeadToUnit = set velocity
	/**
	 * Set Velocity of this {@code Unit} to head to {@code Unit} u
	 */
	public void headToUnit(Unit u) {
		this.setVelX(((u.getCenterX() - this.getCenterX()) / this.distance(u)) * this.getSpeed());
		this.setVelY(((u.getCenterY() - this.getCenterY()) / this.distance(u)) * this.getSpeed());
	}

	// HeadToPoint
	public void headToPos(double x, double y) {
		this.setVelX((x - this.getCenterX() / this.distance(x, y) * this.getSpeed()));
		this.setVelY((y - this.getCenterY() / this.distance(x, y) * this.getSpeed()));
	}

	public void headToUnitX(Unit u) {
		this.setVelX((distanceX(u)) / Math.abs(distanceX(u)) * this.getSpeed());
		this.setVelY(getVelY());
	}

	/**
	 * Move {@code Unit} Position by one frame
	 * 
	 * @param fr int Framerate
	 */
	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
		if (getPosX() <= 0) {
			setPosX(0);
		} else if (getPosX() + getWidth() >= GameManager.getWIDTH()) {
			setPosX(GameManager.getWIDTH() - getWidth());
		}
		if (getPosY() <= 0) {
			setPosY(0);
		}
//		else if (getPosY() + getHeight() >= GameManager.getBOTTOMHEIGHT()) {
//			setPosX(GameManager.getBOTTOMHEIGHT() - getHeight());
//		}
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(posX, posY, width, height);
	}

	public boolean intersects(Unit u) {
		return this.getBoundary().intersects(u.getBoundary());
	}

	// GETTER SETTER
	@SuppressWarnings("unused")
	private int _____GETTER_SETTER_____;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		if (posX < 0) {
			this.posX = 0;
		} else if (posX + getWidth() > GameManager.getWIDTH()) {
			this.posX = GameManager.getWIDTH() - getWidth();
		} else {
			this.posX = posX;
		}

	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		if (posY < GameManager.getTOPHEIGHT()) {
			this.posY = GameManager.getTOPHEIGHT();
		} else if (posY + getHeight() > GameManager.getBOTTOMHEIGHT()) {
			this.posY = GameManager.getBOTTOMHEIGHT() - getHeight();
		} else {
			this.posY = posY;
		}
	}

	public void setPos(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setSize(double width, double height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	public double getCenterX() {
		return posX + (width / 2.0);
	}

	public double getCenterY() {
		return posY + (height / 2.0);
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

	public void setVel(double velX, double velY) {
		this.velX = velX;
		this.velY = velY;
	}

	public void setVelZero() {
		this.velX = 0;
		this.velY = 0;
	}

	public void multiplyVel(double i) {
		this.velX = velX * i;
		this.velY = velY * i;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
