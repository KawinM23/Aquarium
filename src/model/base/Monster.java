package model.base;

import javafx.scene.canvas.GraphicsContext;

public abstract class Monster extends Unit {

	private int health;
	
	public Monster(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		
		this.setHealth(100);
	}

	public abstract void attack();

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
}
