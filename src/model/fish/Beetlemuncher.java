package model.fish;

import javafx.scene.image.Image;
import model.base.Fish;
import properties.Hunger;
import properties.Idle;
import properties.Production;

public class Beetlemuncher extends Fish {

	public Beetlemuncher(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setWidth(100);
		this.setHeight(70);
		this.setSpeed(100);
		this.setVelZero();
		this.setImg(new Image("file:res/image/Guppy.png"));

		this.setHunger(new Hunger(Guppy.class, 3, 10));
		this.setProduction(new Production(this, 0, 5));
		this.setIdle(new Idle(this,15));
		
	}

}
