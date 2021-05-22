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

public class Balrog extends Monster implements Renderable {

	private static final Image BalrogImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	private Hunger hunger;

	public Balrog(String name, double posX, double posY, int health) {
		super(name, posX, posY);

		this.setSize(135, 200);
		this.setSpeed(80);

		this.setMaxHealth(220);
		this.setMaxHealth(health);
		this.setHealth(getMaxHealth());

		this.setHunger(new Hunger(5, 0));
		this.setIdle(new Idle(this, 25));
	}

	@Override
	public void update(int fr) {
		if (this.getHealth() <= 0) {
			// TODO Defeat
			defeated();
			return;
		}

		switch (hunger.checkHunger()) {
		case 0:
			// idle
			this.setVelZero();
			break;
		case 1:
			// find food
			this.attack();
			break;
		}
		for (Fish f : TankManager.getFishList()) {
			if (this.getInnerHitbox(getInnerX(), getInnerY()).contains(f.getCenterX(), f.getCenterY())) {
				// eat & levelup
				System.out.println(this.getName() + " kill " + f.getName());
				this.eat(f, 0);
			}
		}
		this.move(fr);
		this.checkFacingLeft();
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFish = TankManager.getFishList().get(0);
			// Find NearestFish
			for (Fish f : TankManager.getFishList()) {
				if (this.distance(f) < this.distance(nearestFish)) {
					nearestFish = f;
				}
			}
			// CheckFish
			if (this.getInnerHitbox(getInnerX(), getInnerY()).contains(nearestFish.getCenterX(),
					nearestFish.getCenterY())) {
				System.out.println(this.getName() + " kill " + nearestFish.getName());
				this.eat(nearestFish, 1);

				this.getIdle().checkIdle();
			} else {
				// Go to food
				this.headToUnit(nearestFish);
			}

		} else {
			// Idle
			this.getIdle().checkIdleMonster();
		}
	}

	private void eat(Unit nearestFish, int i) {
		SoundManager.playEatFishSound();
		if (!TankManager.getRemoveFishList().contains(nearestFish)) {
			Thread feedThread = new Thread(() -> {
				try {
					TankManager.remove(nearestFish);
					if (i == 1) {
						this.getHunger().setLastFedRandom(8, 10);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			feedThread.start();
		}
	}

	@Override
	public void getHit() {
		double hpPercent = (((double) getHealth()) / ((double) getMaxHealth())) * 100;
		if (hpPercent >= 50) {
			SoundManager.playShieldHitSound();
		} else if (hpPercent < 50) {
			SoundManager.playBodyHitSound();
		}
		this.decreaseHealth(PlayerController.getGunDamage());
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setStroke(new Color(1, 0, 0, 1));
		gc.strokeRect(getPosX(), getPosY(), getWidth(), getHeight());
		gc.strokeRect(getPosX() + getInnerX(), getPosY() + getInnerY(), getWidth() - (2 * getInnerX()),
				getHeight() - (2 * getInnerY()));
		if (isFacingLeft()) {
			gc.drawImage(BalrogImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(BalrogImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void continuePause(long duration) {
		// TODO Auto-generated method stub
		this.getHunger().addLastFed(duration);
	}

	public Hunger getHunger() {
		return hunger;
	}

	public void setHunger(Hunger hunger) {
		this.hunger = hunger;
	}

}
