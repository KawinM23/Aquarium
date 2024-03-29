package model.fish;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.InvasionManager;
import manager.SoundManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Money;
import model.base.Unit;
import model.money.Diamond;
import model.money.Star;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Starcatcher extends Fish implements Renderable {

	private static final Image StarcatcherImage = new Image(
			ClassLoader.getSystemResource("Starcatcher.png").toString());
	private static final Image StarcatcherHungryImage = new Image(
			ClassLoader.getSystemResource("StarcatcherHungry.png").toString());

	public Image getImage() {
		return StarcatcherImage;
	}

	private boolean isGrounded;
	private static final int FALL_ACC = 120;

	public Starcatcher(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setSize(80, 89);
		this.setMouthPos(20, 45);

		this.setSpeed(65);
		this.setGrounded(false);

		this.setHunger(new Hunger(10, 30)); // Hunger 10sec
		this.setProduction(new Production(this, 4, 0));
		this.setIdle(new Idle(this, 25));
		this.setPrice(750);
	}

	@Override
	public void update(int fr) {
		if (!InvasionManager.isInvaded()) {
			switch (getHunger().checkHunger()) {
			case 0:
				// idle
				if (isGrounded) {
					this.getIdle().checkIdleX();
				}
				setHungry(false);
				break;
			case 1:
				// find food
				this.findFood();
				break;
			case 2:
				// Very Hungry ChangePic
				setHungry(true);
				this.findFood();
				break;
			case 3:
				// die
				this.die();
				return;
			}
		} else {
			getIdle().checkIdleX();
		}
		this.move(fr);
	}

	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		if (getPosY() < GameManager.getBOTTOMHEIGHT() - getHeight()) {
			setGrounded(false);
		} else if (getPosY() >= GameManager.getBOTTOMHEIGHT() - getHeight()) {
			setGrounded(true);
		}
		if (!isGrounded) {
			this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
			this.setVelY(getVelY() + (FALL_ACC * deltaTime));
			if (getPosY() >= GameManager.getBOTTOMHEIGHT() - getHeight()) {
				this.getIdle().randomVel();
				this.getIdle().setNextIdleRandom(1, 2);
				this.setVelX(getVelX() * 0.8);
				this.getIdle().setVelX(getVelX());
				this.setVelY(0);
				this.setGrounded(true);
			}
		}
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
				if (this.isAtMounth(nearestFood)) {
					// Eat
					TankManager.remove(nearestFood);
					this.feed(nearestFood);
					this.getIdle().checkIdleX();
				} else if (this.distanceX(nearestFood) > 10) {
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
				// No food
				this.getIdle().checkIdleX();
			}
		} else {
			// No food
			this.getIdle().checkIdleX();
		}
	}

	public boolean isAtMounth(Unit nearestFood) {
		return new Rectangle2D(getPosX() + getMouthPosX(false), getMouthPosY() - 2,
				getWidth() - (2 * getMouthPosX(false)), 20).intersects(nearestFood.getBoundary());
	}

	public void feed(Unit nearestFood) {
		SoundManager.playShootDiamondSound();
		if (!TankManager.getRemoveFoodList().contains(nearestFood)) {
			Thread feedThread = new Thread(() -> {
				try {
					TankManager.produceMoney(new Diamond("Diamond", this.getCenterX(), this.getCenterY() - 20, 1));
					TankManager.remove(nearestFood);

					this.getHunger().setLastFedNow();
					this.getHunger().addLastFedRandom(0, 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			feedThread.start();
		}

	}

	@Override
	public void render(GraphicsContext gc) {
		if (isHungry()) {
			gc.drawImage(StarcatcherHungryImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(StarcatcherImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	public boolean isGrounded() {
		return isGrounded;
	}

	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

}
