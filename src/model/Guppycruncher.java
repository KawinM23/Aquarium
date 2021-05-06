package model;

import manager.GameManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Money;
import model.base.Unit;

public class Guppycruncher extends Fish {

	private boolean isJumping;
	private double fallAcc = 10;

	public Guppycruncher(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
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
	
	public void feed(Unit nearestFood) {
		this.getHunger().resetTime();
		TankManager.produceMoney(new Diamond("Diamond", this.getCenterX(), this.getCenterY(), 1));
	}

	private void jump() {
		// TODO Auto-generated method stub
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
