package model.base;

public abstract class Monster extends Unit {

	private int health;
	
	private boolean isFacingLeft;

	public Monster(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub

		this.setHealth(100);
	}

	public abstract void attack();
	public abstract void getHit();

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void decreaseHealth(int damage) {
		this.setHealth(getHealth() - damage);
	}

	public boolean isFacingLeft() {
		return isFacingLeft;
	}

	public void setFacingLeft(boolean isFacingLeft) {
		this.isFacingLeft = isFacingLeft;
	}

}
