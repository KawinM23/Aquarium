package model.base;

import javafx.geometry.Rectangle2D;
import manager.StatTracker;
import manager.TankManager;
import model.money.Diamond;
import properties.Hunger;
import properties.Idle;

public abstract class Monster extends Unit {

	private int health;
	private Hunger hunger;
	private Idle idle;

	private int innerX = 40;
	private int innerY = 40;

	private boolean isFacingLeft;

	public Monster(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub

		this.setHealth(100);
	}

	public abstract void attack();

	public abstract void getHit();

	public abstract void continuePause(long duration);

	public void defeated() {
		TankManager.produceMoney(new Diamond("Diamond", getCenterX(), getCenterY(), 0));
		TankManager.remove(this);
		System.out.println("Defeat");
		StatTracker.addMonsterDefeated();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if(health != 0) {
			this.health = health;
		}
	}

	public void decreaseHealth(int damage) {
		this.health = (getHealth() - damage);
	}

	public boolean isFacingLeft() {
		return isFacingLeft;
	}

	public void setFacingLeft(boolean isFacingLeft) {
		this.isFacingLeft = isFacingLeft;
	}

	public Idle getIdle() {
		return idle;
	}

	public void setIdle(Idle idle) {
		this.idle = idle;
	}

	public Rectangle2D getInnerHitbox(double x, double y) {
		return new Rectangle2D(getPosX() + x, getPosY() + y, getWidth() - (2 * x), getHeight() - (2 * y));
	}

	public int getInnerX() {
		return innerX;
	}

	public void setInnerX(int innerX) {
		this.innerX = innerX;
	}

	public int getInnerY() {
		return innerY;
	}

	public void setInnerY(int innerY) {
		this.innerY = innerY;
	}

	public void setInner(int x, int y) {
		this.setInnerX(x);
		this.setInnerY(y);
	}

	public Hunger getHunger() {
		return hunger;
	}

	public void setHunger(Hunger hunger) {
		this.hunger = hunger;
	}

}
