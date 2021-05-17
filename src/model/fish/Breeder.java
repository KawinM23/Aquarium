package model.fish;

import javafx.scene.image.Image;
import model.Food;
import model.base.Fish;
import properties.Hunger;
import properties.Idle;
import properties.Production;

public class Breeder extends Fish {
	
	private Image BreederImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Breeder(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setWidth(80);
		this.setHeight(80);
		this.setSpeed(70);
		this.setVelZero();
		this.setImg(BreederImage);

		this.setHunger(new Hunger(Food.class, 3, 10));
		this.setProduction(new Production(this, 8, 15));
		this.setIdle(new Idle(this, 20));
		this.setPrice(200);
	}

}
