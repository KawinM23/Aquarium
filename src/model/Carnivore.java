package model;

import javafx.scene.image.Image;
import manager.TankManager;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Production;

public class Carnivore extends Fish {

	public Carnivore(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(100);
		this.setHeight(50);
		this.setSpeed(100);
		this.setVelZero();
		this.setImg(new Image("file:res/image/Guppy.png"));

		this.hunger = new Hunger(Guppy.class, 3, 10);
		this.production = new Production(this, 0, 5);
	}

	public void findFood() {
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFood = null;
			// Find NearestFood
			for (Fish f : TankManager.getFishList()) {
				if (f instanceof Guppy) {
					if (((Guppy) f).getGrowth() < 100) {
						if (nearestFood == null) {
							nearestFood = f;
						} else if (this.distance(f) < this.distance(nearestFood)) {
							nearestFood = f;
						}
					}
				}
			}
			// Check Food position and Fish
			if (nearestFood != null) {
				if (isAtMounth(nearestFood)) {
					// eat & levelup
					TankManager.remove(nearestFood);
					this.setVelZero();
					this.feed(nearestFood);
				} else {
					// Go to food
					this.headToUnit(nearestFood);
				}
			} else {
				this.setVelZero();
			}
		} else {
			this.setVelZero();
			System.out.println("No Food");
		}
	}

}
