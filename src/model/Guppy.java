package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import model.base.Fish;
import properties.Hunger;
import properties.Production;

public class Guppy extends Fish {

	private int growth;

	public Guppy(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(80);
		this.setHeight(60);
		this.setSpeed(100);
		this.setVelZero();
		this.setImg(new Image("file:res/image/Guppy.png"));

		this.growth = 0;
		this.hunger = new Hunger(0, 3, 7);
		this.production = new Production(this, 0, 5);
	}

}
