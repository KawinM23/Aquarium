package model.fish;

import javafx.scene.image.Image;
import model.base.Fish;
import properties.Hunger;
import properties.Idle;
import properties.Production;

public class Ultravore extends Fish {
	
	private Image UltravoreImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Ultravore(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setWidth(100);
		this.setHeight(70);
		this.setSpeed(100);
		this.setVelZero();
		this.setImg(UltravoreImage);

		this.setHunger(new Hunger(Carnivore.class, 5, 30));
		this.setProduction(new Production(this, 9, 10));
		this.setIdle(new Idle(this, 25));
	}
	
	

}
