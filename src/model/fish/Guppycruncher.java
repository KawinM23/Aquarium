package model.fish;

import manager.GameManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Money;
import model.base.Unit;
import model.money.Star;

public class Guppycruncher extends Fish {

	private boolean isJumping;
	private double fallAcc = 10;

	public Guppycruncher(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO SetMoreVar
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
					} else if (getVelX() > 5) {
						this.multiplyVel(0.98);
					}
				}
				if (this.distanceY(nearestFood) < 60 && !isJumping) {
					jump();
				}
			} else {
				this.setVelZero();
			}
		} else {
			this.setVelZero();
		}
	}
	


	private void jump() {
		// TODO jump
		this.setVelY(-20);
		this.isJumping = true;
	}

	@Override
	public void update(int fr) {
		if (this.getPosY() + getHeight() > GameManager.getBOTTOMHEIGHT()) {
			this.setPosY(GameManager.getBOTTOMHEIGHT() - getHeight());
			this.setJumping(false);
		}
		this.move(fr);
	}

	public void move(int fr) {
		//TODO Test Jumping GC
		double deltaTime = 1.0 / fr;
		if (isJumping) {
			this.setVelY(getVelY() + (fallAcc * deltaTime));
		}
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	

}
