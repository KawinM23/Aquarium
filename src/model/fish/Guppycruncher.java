package model.fish;

import javafx.scene.image.Image;
import manager.GameManager;
import manager.InvasionManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Money;
import model.base.Unit;
import model.money.Star;
import properties.Hunger;
import properties.Idle;
import properties.Production;

public class Guppycruncher extends Fish {

	private Image GuppycruncherImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	private boolean isJumping;
	private final double fallAcc = 120;
	private final double velYJump = -150;
	private final double reachHeight = 150;
	private final double reachDistance = 80;

	public Guppycruncher(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO SetMoreVar

		this.setWidth(50);
		this.setHeight(50);
		this.setSpeed(40);
		this.setVelZero();

		this.setImg(GuppycruncherImage);

		this.setJumping(false);

		this.setHunger(new Hunger(Guppy.class, 6, 30));
		this.setProduction(new Production(this, 6, 5));
		this.setIdle(new Idle(this, 20));
		this.setPrice(750);
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
					TankManager.remove(nearestFood);
					this.feed(nearestFood);
					return;
				}
				if (Math.abs(distanceX(nearestFood)) > 20) {
					headToUnitX(nearestFood);
				} else {
					setVelX(getVelX() / Math.abs(getVelX()) * getSpeed() * 0.8);
				}
				if (!isJumping && Math.abs(distanceY(nearestFood)) < reachHeight
						&& Math.abs(distanceX(nearestFood)) < reachDistance) {
					jump();
				}
			} else {
				if (!isJumping) {
					getIdle().checkIdleX();
				}
			}
		} else {
			if (!isJumping) {
				getIdle().checkIdleX();
			}
		}
	}

	private void jump() {
		// TODO jump
		System.out.println("Jump");
		setVelY(velYJump);
		setJumping(true);
	}

	@Override
	public void update(int fr) {
		if (!InvasionManager.isInvaded()) {
			switch (getHunger().checkHunger()) {
			case 0:
				// idle
				if (!isJumping) {
					this.getIdle().checkIdleX();
				}
				break;
			case 1:
				// find food
				this.findFood();
				break;
			case 2:
				// Very Hungry TODO ChangePic
				this.findFood();
				break;
			case 3:
				// die
				this.die();
				return;
			}

			this.getProduction().checkProduce();
		} else {
			getIdle().checkIdleX();
		}
		this.move(fr);
		this.checkFacingLeft();
	}

	public void move(int fr) {
		// TODO Test Jumping GC
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		if (isJumping) {
			this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
			this.setVelY(getVelY() + (fallAcc * deltaTime));
			if (getPosY() >= GameManager.getBOTTOMHEIGHT() - getHeight()) {
				this.getIdle().resetIdle(1);
				this.setVelX(getVelX() * 0.8);
				this.getIdle().setVelX(getVelX());
				this.setVelY(0);
				this.setJumping(false);
			}
		}
		if (getPosY() > GameManager.getBOTTOMHEIGHT() - getHeight()) {
			this.setPosY(GameManager.getBOTTOMHEIGHT() - getHeight());
		}

	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

}
