package model.monster;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import manager.PlayerController;
import manager.SoundManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Renderable;

public class Sylvester extends Monster implements Renderable {

	private static final Image SylvesterLeftImage = new Image(ClassLoader.getSystemResource("SylvesterLeft.png").toString());
	private static final Image SylvesterRightImage = new Image(ClassLoader.getSystemResource("SylvesterRight.png").toString());

	public Sylvester(String name, double posX, double posY, int health) {
		super(name, posX, posY);
		this.setSize(132, 185);
		this.setSpeed(80);

		this.setMaxHealth(100);
		this.setMaxHealth(health);
		this.setHealth(getMaxHealth());
		
		this.setHunger(new Hunger(5, 0));
		this.setIdle(new Idle(this, 20));

	}

	@Override
	public void update(int fr) {
		if (this.getHealth() <= 0) {
			defeated();
			return;
		}

		switch (getHunger().checkHunger()) {
		case 0:
			// idle
			this.getIdle().checkIdleMonster();
			break;
		case 1:
			// find food
			this.attack();
			break;
		}

		this.move(fr);
		this.checkFacingLeft();
	}

	@Override
	public void render(GraphicsContext gc) {

		gc.setStroke(new Color(1, 0, 0, 1));
		gc.strokeRect(getPosX(), getPosY(), getWidth(), getHeight());
		gc.strokeRect(getPosX() + getInnerX(), getPosY() + getInnerY(), getWidth() - (2 * getInnerX()),
				getHeight() - (2 * getInnerY()));
		if (isFacingLeft()) {
			gc.drawImage(SylvesterLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(SylvesterRightImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void attack() {
		//Try eat one fish
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFish = TankManager.getFishList().get(0);
			// Find NearestFish
			for (Fish f : TankManager.getFishList()) {
				if (this.distance(f) < this.distance(nearestFish)) {
					nearestFish = f;
				}
			}
			// Check Fish
			if (this.getInnerHitbox(getInnerX(), getInnerY()).contains(nearestFish.getCenterX(),
					nearestFish.getCenterY())) {
				System.out.println(this.getName() + " kill " + nearestFish.getName());
				this.eat(nearestFish);
				this.getIdle().checkIdleMonster();
			} else {
				// Go to food
				this.headToUnit(nearestFish);
			}

		} else {
			// Idle
			this.getIdle().checkIdleMonster();
		}
	}

	private void eat(Unit nearestFish) {
		SoundManager.playEatFishSound();
		if (!TankManager.getRemoveFishList().contains(nearestFish)) {
			Thread feedThread = new Thread(() -> {
				try {
					TankManager.remove(nearestFish);
					this.getHunger().setLastFedNow();
					this.getHunger().addLastFedRandom(1, 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			feedThread.start();
		}

	}

	public void getHit() {
		//Onclick Mouse -> decrease Hp
		double hpPercent = (((double) getHealth()) / ((double) getMaxHealth())) * 100;
		if (hpPercent>=50) {
			SoundManager.playShieldHitSound();
		} else if (hpPercent<50) {
			SoundManager.playBodyHitSound();
		}
		this.decreaseHealth(PlayerController.getGunDamage());
		System.out.println(getName() + " Health: " + getHealth());
	}

	@Override
	public void continuePause(long duration) {
		this.getHunger().addLastFed(duration);
	}

}
