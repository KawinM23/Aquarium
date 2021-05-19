package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
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

public class Starcatcher extends Fish implements Renderable{
	
	private static final Image StarcatcherImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Image getImage() {
		return StarcatcherImage;
	}
	
	private boolean isGrounded;
	private static final int fallAcc = 120;

	public Starcatcher(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setWidth(53);
		this.setHeight(66);
		this.setSpeed(40);
		this.setVelZero();
		this.setGrounded(false);

		this.setHunger(new Hunger(Star.class, 3, 10)); // TODO Hunger 10sec
		this.setProduction(new Production(this, 4, 0));
		this.setIdle(new Idle(this,20));
		this.setPrice(750);
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
					this.getIdle().checkIdleX();
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
				this.getIdle().checkIdleX();
			}
		} else {
			//No food
			this.getIdle().checkIdleX();
		}
	}
	
	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		if(getPosY() < GameManager.getBOTTOMHEIGHT() - getHeight()) {
			setGrounded(false);
		}
		if (!isGrounded) {
			this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
			this.setVelY(getVelY() + (fallAcc * deltaTime));
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

	public void feed(Unit nearestFood) {
		this.getHunger().setLastFedNow();
		TankManager.produceMoney(new Diamond("Diamond", this.getCenterX(), this.getCenterY() - 20, 1));
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isFacingLeft()) {
			gc.drawImage(StarcatcherImage, getPosX(), getPosY(), getWidth(), getHeight());
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
