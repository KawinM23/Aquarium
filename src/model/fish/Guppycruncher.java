package model.fish;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.InvasionManager;
import manager.SoundManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Guppycruncher extends Fish implements Renderable{

	private static final Image GuppycruncherImage = new Image(ClassLoader.getSystemResource("Guppycruncher.png").toString());
	private static final Image GuppycruncherHungryImage = new Image(ClassLoader.getSystemResource("GuppycruncherHungry.png").toString());

	public Image getImage() {
		return GuppycruncherImage;
	}
	
	private boolean isJumping;
	private static final double FALL_ACC = 120;
	private static final double VEL_Y_JUMP = -150;
	private static final double REACH_HEIGHT = 160;
	private static final double REACH_DISTANCE = 80;

	public Guppycruncher(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setSize(100, 100);
		this.setMouthPos(35, 40);
		
		this.setSpeed(40);
		this.setVelZero();

		this.setJumping(false);

		this.setHunger(new Hunger(6, 30));
		this.setProduction(new Production(this, 6, 15));
		this.setIdle(new Idle(this, 25));
		this.setPrice(750);
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
				setHungry(false);
				break;
			case 1:
				// find food
				this.findFood();
				break;
			case 2:
				// Very Hungry
				this.findFood();
				setHungry(true);
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
	}

	public void move(int fr) {
		//Jumping GC
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		if(getPosY() < GameManager.getBOTTOMHEIGHT() - getHeight()) {
			setJumping(true);
		}
		if (isJumping) {
			this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
			this.setVelY(getVelY() + (FALL_ACC * deltaTime));
			if (getPosY() >= GameManager.getBOTTOMHEIGHT() - getHeight()) {
				this.getIdle().randomVel();
				this.getIdle().setNextIdleRandom(1, 2);
				this.setVelX(getVelX() * 0.8);
				this.getIdle().setVelX(getVelX());
				this.setVelY(0);
				this.setJumping(false);
			}
		}
	}

	public void findFood() {
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFood = null;
			// Find NearestFood
			try {
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
			} catch (Exception e) {
				nearestFood = null;
			}

			// Check Food position and Fish
			if (nearestFood != null) {
				if (this.isAtMounth(nearestFood)) {
					SoundManager.playEatFishSound();
					TankManager.remove(nearestFood);
					this.feed(nearestFood);
					return;
				}
				if (Math.abs(distanceX(nearestFood)) > 20) {
					headToUnitX(nearestFood);
				} else {
					setVelX(getVelX() / Math.abs(getVelX()) * getSpeed() * 0.8);
				}
				if (!isJumping && Math.abs(distanceY(nearestFood)) < REACH_HEIGHT
						&& Math.abs(distanceX(nearestFood)) < REACH_DISTANCE) {
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
		// jump
		setVelY(VEL_Y_JUMP);
		setJumping(true);
	}
	
	public boolean isAtMounth(Unit nearestFood) {
		return new Rectangle2D(getPosX() + getMouthPosX(false), getMouthPosY() - 2,
				getWidth() - (2 * getMouthPosX(false)), 25).intersects(nearestFood.getBoundary());
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isHungry()) {
			gc.drawImage(GuppycruncherHungryImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(GuppycruncherImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

}
