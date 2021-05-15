package model.fish;

import javafx.scene.image.Image;
import manager.GameManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Money;
import model.base.Unit;
import model.money.Diamond;
import model.money.Star;
import properties.Hunger;
import properties.Production;

public class Starcatcher extends Fish {

	public Starcatcher(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setWidth(40);
		this.setHeight(40);
		this.setSpeed(40);
		this.setVelZero();
		this.setImg(new Image("file:res/image/Guppy.png"));

		this.setHunger(new Hunger(Star.class, 3, 10)); // TODO Hunger 10sec
		this.setProduction(new Production(this, 4, 0));
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
					this.feed(nearestFood);
					this.getIdle().checkIdle();
				} else if (this.distanceX(nearestFood) > 20) {
					// Go to food
					this.headToUnitX(nearestFood);
				} else {
					if (this.distanceX(nearestFood) < 5) {
						this.setVelZero();
					} else if (getVelX() > 5) {
						this.multiplyVel(0.98);
					}
				}
			} else {
				//No food
				this.getIdle().checkIdle();
			}
		} else {
			//No food
			this.getIdle().checkIdle();
		}
	}
	
	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		this.setPosY(this.getPosY());
		if (getPosX() <= 0) {
			setPosX(0);
		} else if (getPosX() + getWidth() >= GameManager.getWIDTH()) {
			setPosX(GameManager.getWIDTH() - getWidth());
		}
	}

	public void feed(Unit nearestFood) {
		this.getHunger().setLastFedNow();
		TankManager.produceMoney(new Diamond("Diamond", this.getCenterX(), this.getCenterY() - 20, 1));
	}
	
	

}
