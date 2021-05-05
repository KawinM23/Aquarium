package model;

import javafx.scene.image.Image;
import manager.TankManager;
import model.base.Fish;
import model.base.Money;
import model.base.Unit;
import properties.Hunger;
import properties.Production;

public class StarCatcher extends Fish {

	public StarCatcher(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(40);
		this.setHeight(40);
		this.setSpeed(100);
		this.setVelZero();
		this.setImg(new Image("file:res/image/Guppy.png"));
		
		this.hunger = new Hunger(Star.class, 3, 20);
		this.production = new Production(this, 5, 7);
	}

	public void findFood() {
		if (TankManager.getMoneyList().size() != 0) {
			Unit nearestFood = null;
			// Find NearestFood
			for (Money m : TankManager.getMoneyList()) {
				if (m instanceof Star) {
					if (nearestFood == null) {
						nearestFood = m;
					} else if (this.distance(m) < this.distance(nearestFood)) {
						nearestFood = m;
					}
				}
			}
			// Check Food position and Fish
			if (nearestFood != null) {
				if (this.intersects(nearestFood)) {
					// eat & levelup
					TankManager.remove(nearestFood);
					this.setVelZero();
					this.feed(nearestFood);
				} else {
					// Go to food
					this.headToUnitX(nearestFood);
				}
			} else {
				this.setVelZero();
			}
		} else {
			this.setVelZero();
			System.out.println("No Food");
		}
	}

	public void headToUnitX(Unit nearestFood) {
		// TODO Auto-generated method stub
		this.setVelX(((nearestFood.getPosX() - this.getPosX()) / this.distance(nearestFood))*this.getSpeed());
	}
}
