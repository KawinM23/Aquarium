package model.base;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import manager.InvasionManager;
import manager.SoundManager;
import manager.TankManager;
import model.Food;
import properties.Hunger;
import properties.Idle;
import properties.Production;

public abstract class Fish extends Unit {

	private int price;
	private Hunger hunger;
	private Production production;
	private Idle idle;

	private boolean isFacingLeft;
	private long turningTime;
	private double mouthPosX;
	private double mouthPosY;
	
	private boolean isHungry;

	public Fish(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setMouthPos(10, 30);
		this.setSize(50, 50);
		this.setSpeed(80);
		
		this.isFacingLeft = true;
		this.price = 0;
		this.hunger = null;
		this.production = null;
		this.setIdle(new Idle(this, 15));
	}

	public Idle getIdle() {
		return idle;
	}

	public void setIdle(Idle idle) {
		this.idle = idle;
	}

	public void findFood() {

		Thread findFoodThread = new Thread(() -> {
			try {
				if (TankManager.getFoodList().size() != 0) {
					Unit nearestFood = TankManager.getFoodList().get(0);
					// Find NearestFood
					for (Food f : TankManager.getFoodList()) {
						if (this.distance(f) < this.distance(nearestFood)) {
							nearestFood = f;
						}
					}
					// Check Food position and Fish
					if (isAtMounth(nearestFood)) {
						// eat & levelup
						System.out.println(this.getName() + " eat " + nearestFood.getName());
						this.feed(nearestFood);
						this.idle.eatFood();
					} else {
						// Go to food
						this.headToFood(nearestFood);
						if (Math.abs(this.distanceX(nearestFood)) < 10) {
							this.setVelX(0);
						}
						this.idle.slowIdle();
					}

				} else {
					// Idle No food
					this.idle.checkIdle();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		findFoodThread.start();
		
		

	}

	public boolean isAtMounth(Unit nearestFood) {
		return new Rectangle2D(getPosX() + getMouthPosX(false), getMouthPosY() - 2,
				getWidth() - (2 * getMouthPosX(false)), 4).intersects(nearestFood.getBoundary());
//		return nearestFood.getBoundary().contains(new Point2D(getMouthPosX(), getMouthPosY()));
	}

	public void feed(Unit nearestFood) {
		// Play Sound Effect
		if (!TankManager.getRemoveFoodList().contains(nearestFood)) {
			Thread feedThread = new Thread(() -> {
				try {
					// Play Sound Effect
					SoundManager.playEatSound();
					
					TankManager.remove(nearestFood);
					this.hunger.setLastFedNow();
					this.hunger.addLastFedRandom(0, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			feedThread.start();
		}

	}

	public void headToFood(Unit u) {
		this.setVelX(((u.getCenterX() - getMouthPosX(true)) / u.distance(getMouthPosX(true), getMouthPosY()))
				* this.getSpeed());
		this.setVelY(
				((u.getCenterY() - getMouthPosY()) / u.distance(getMouthPosX(true), getMouthPosY())) * this.getSpeed());
	}

	public void die() {
		Thread fishDieThread = new Thread(() -> {
			// Play Sound Effect
			SoundManager.playDieSound();
			TankManager.remove(this);
		});
		fishDieThread.start();
	}

	@Override
	public void update(int fr) {
		if (!InvasionManager.isInvaded()) {
			
			switch (hunger.checkHunger()) {
			case 0:
				// idle
				this.idle.checkIdle();
				setHungry(false);
				break;
			case 1:
				// find food
				this.findFood();
				break;
			case 2:
				// Very Hungry TODO ChangePic
				this.findFood();
				setHungry(true);
				break;
			case 3:
				// die
				this.die();
				return;
			}

			this.production.checkProduce();
		} else {
			idle.checkIdle();
		}
		this.move(fr);
		this.checkFacingLeft();
	}

	public double getMouthPosX(boolean abs) {
		if (abs) {
			if (isFacingLeft) {
				return getPosX() + mouthPosX;
			} else {
				return getPosX() + getWidth() - mouthPosX;
			}
		} else {
			return mouthPosX;
		}
	}

	public void setMouthPosX(double mouthPosX) {
		this.mouthPosX = mouthPosX;
	}

	public double getMouthPosY() {
		return getPosY() + mouthPosY;
	}

	public void setMouthPosY(double mouthPosY) {
		this.mouthPosY = mouthPosY;
	}

	public void setMouthPos(double mouthPosX, double mouthPosY) {
		this.mouthPosX = mouthPosX;
		this.mouthPosY = mouthPosY;
	}

	public boolean isHungry() {
		return isHungry;
	}

	public void setHungry(boolean isHungry) {
		this.isHungry = isHungry;
	}

	public boolean isFacingLeft() {
		return isFacingLeft;
	}

	public void setFacingLeft(boolean isFacingLeft) {
		this.isFacingLeft = isFacingLeft;
	}

	public void checkFacingLeft() {
		if (System.nanoTime() - turningTime >= 0.5e9) {
			if (getVelX() > 0) {
				setFacingLeft(false);
				setTurningTimeNow();
			} else {
				setFacingLeft(true);
				setTurningTimeNow();
			}
		}
	}

	public abstract Image getImage();

	public Hunger getHunger() {
		return hunger;
	}

	public void setHunger(Hunger hunger) {
		this.hunger = hunger;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getTurningTime() {
		return turningTime;
	}

	public void setTurningTime(long turningTime) {
		this.turningTime = turningTime;
	}
	
	public void setTurningTimeNow() {
		this.turningTime = System.nanoTime();
	}

}
