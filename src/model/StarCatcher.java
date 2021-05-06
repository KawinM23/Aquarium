package model;

import javafx.scene.image.Image;
import manager.TankManager;
import model.base.Fish;
import model.base.Money;
import model.base.Unit;
import properties.Hunger;
import properties.Production;

public class Starcatcher extends Fish {

	public Starcatcher(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(40);
		this.setHeight(40);
		this.setSpeed(35);
		this.setVelZero();
		this.setImg(new Image("file:res/image/Guppy.png"));

		this.setHunger(new Hunger(Star.class, 3, 20));
		this.setProduction(new Production(this, 5, 0));
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
					// Eat
					TankManager.remove(nearestFood);
					this.setVelZero();
					this.feed(nearestFood);
				} else if (this.distanceX(nearestFood) > 20) {
					// Go to food
					this.headToUnitX(nearestFood);
				} else {
					if (this.distanceX(nearestFood) < 5) {
						this.setVelZero();
					} else if(getVelX() > 5) {
						this.multiplyVel(0.98);
					}
				}
			} else {
				this.setVelZero();
			}
		} else {
			this.setVelZero();
		}
	}

	public void feed(Unit nearestFood) {
		this.getHunger().resetTime();
		TankManager.produceMoney(new Diamond("Diamond", this.getCenterX(), this.getCenterY(), 1));
	}

}
