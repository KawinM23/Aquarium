package model.fish;

import javafx.scene.image.Image;
import manager.TankManager;
import model.base.Fish;
import model.base.Unit;
import model.money.Beetle;
import model.money.Pearl;
import properties.Hunger;
import properties.Idle;
import properties.Production;

public class Beetlemuncher extends Fish {
	
	private Image BeetlemuncherImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Beetlemuncher(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setWidth(100);
		this.setHeight(70);
		this.setSpeed(100);
		this.setVelZero();

		this.setHunger(new Hunger(Beetle.class, 6, 20));
		this.setProduction(new Production(this, 7, 0));
		this.setIdle(new Idle(this, 20));
		this.setPrice(2000);
	}
	
	public void findFood() {
		//TODO
	}

	public void feed(Unit nearestFood) {
		TankManager.produceMoney(new Pearl("Pearl", this.getCenterX(), this.getCenterY()));
		this.getHunger().setLastFedNow();
	}

}
