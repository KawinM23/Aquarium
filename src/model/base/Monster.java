package model.base;

import javafx.scene.canvas.GraphicsContext;

public abstract class Monster extends Unit {

	public Monster(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
	}

	public abstract void attack();

}
