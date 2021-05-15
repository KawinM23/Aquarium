package model.fish;

import javafx.scene.image.Image;
import model.Food;
import model.base.Fish;
import properties.Hunger;
import properties.Idle;
import properties.Production;

public class Breeder extends Fish {

	public Breeder(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setWidth(80);
		this.setHeight(80);
		this.setSpeed(70);
		this.setVelZero();
		this.setImg(new Image("file:res/image/Guppy.png"));

		this.setHunger(new Hunger(Food.class, 3, 10));
		this.setProduction(new Production(this, 8, 15));
		this.setIdle(new Idle(this, 20));
	}

}
