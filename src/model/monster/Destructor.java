package model.monster;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import manager.GameManager;
import manager.PlayerController;
import manager.SoundManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Renderable;

public class Destructor extends Monster implements Renderable {

	private static final Image DestructorImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	private ArrayList<Unit> targetFishes;

	public Destructor(String name, double posX, double posY, int health) {
		super(name, posX, posY);

		this.setSize(135, 200);
		this.setSpeed(80);

		this.setMaxHealth(300);
		this.setMaxHealth(health);
		this.setHealth(getMaxHealth());

		this.setHunger(new Hunger(8, 0));
		this.setIdle(new Idle(this, 20));

		this.getHunger().setLastFedRandom(3, 4);
	}

	@Override
	public void update(int fr) {
		if (this.getHealth() <= 0) {
			//Defeat
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

	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		if (getPosY() < GameManager.getBOTTOMHEIGHT() - getHeight()) {
			setPosY(GameManager.getBOTTOMHEIGHT() - getHeight());
		}
	}

	@Override
	public void render(GraphicsContext gc) {

		gc.setStroke(new Color(1, 0, 0, 1));
		gc.strokeRect(getPosX(), getPosY(), getWidth(), getHeight());
		if (isFacingLeft()) {
			gc.drawImage(DestructorImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(DestructorImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void attack() {
		//Shoot
		Thread attackThread = new Thread(() -> {
			targetFishes = new ArrayList<Unit>();
			if (TankManager.getFishList().size() != 0) {
				if (TankManager.getFishList().size() <= 3) {
					for (Fish f : TankManager.getFishList()) {
						targetFishes.add(f);
					}
				} else {
					for (int i = 0; i < 3; i++) {
						targetFishes.add(TankManager.getFishList().get(i));
					}

					// Find TargetFishes
					for (int j = 3; j < TankManager.getFishList().size(); j++) {
						replaceTargetFishes(targetFishes, TankManager.getFishList().get(j));
					}
				}
				// SHOOT MISSLIES
				shootMissile(targetFishes);

				this.getHunger().setLastFedNow();

				this.getIdle().checkIdleMonster();

			} else {
				// Idle No food
				this.getIdle().checkIdleMonster();
			}
		});
		attackThread.start();

	}

	private void shootMissile(ArrayList<Unit> targetFishes) {
		Thread shootThread = new Thread(() -> {
			for (Unit u : targetFishes) {
				Missile mis = new Missile("Missile", getCenterX(), getCenterY(), 0, u);
				TankManager.add(mis);
			}
		});
		shootThread.start();
	}

	private void replaceTargetFishes(ArrayList<Unit> targetFishes, Unit u) {
		double[] disList = new double[4];
		disList[0] = this.distance(targetFishes.get(0));
		disList[1] = this.distance(targetFishes.get(1));
		disList[2] = this.distance(targetFishes.get(2));
		disList[3] = this.distance(u);

		if (disList[3] <= disList[0] && disList[3] <= disList[1] && disList[3] <= disList[2]) {
			return;
		} else {
			if (disList[3] > disList[0] && disList[3] <= disList[1] && disList[3] <= disList[2]) {
				targetFishes.remove(0);
				targetFishes.add(u);
			} else if ((disList[3] <= disList[0] && disList[3] > disList[1] && disList[3] <= disList[2])) {
				targetFishes.remove(1);
				targetFishes.add(u);
			} else if ((disList[3] <= disList[0] && disList[3] <= disList[1] && disList[3] > disList[2])) {
				targetFishes.remove(2);
				targetFishes.add(u);
			} else {
				if (disList[3] > disList[0] && disList[3] > disList[1] && disList[3] <= disList[2]) {
					if (disList[0] < disList[1]) {
						targetFishes.remove(0);
						targetFishes.add(u);
					} else {
						targetFishes.remove(1);
						targetFishes.add(u);
					}
				} else if (disList[3] > disList[0] && disList[3] <= disList[1] && disList[3] > disList[2]) {
					if (disList[0] < disList[2]) {
						targetFishes.remove(0);
						targetFishes.add(u);
					} else {
						targetFishes.remove(2);
						targetFishes.add(u);
					}
				} else if (disList[3] <= disList[0] && disList[3] > disList[1] && disList[3] > disList[2]) {
					if (disList[1] < disList[2]) {
						targetFishes.remove(1);
						targetFishes.add(u);
					} else {
						targetFishes.remove(2);
						targetFishes.add(u);
					}
				}
			}
		}

	}

	public void getHit() {
		// Onclick Mouse -> decrease Hp
		double hpPercent = (((double) getHealth()) / ((double) getMaxHealth())) * 100;
		if (hpPercent >= 50) {
			SoundManager.playShieldHitSound();
		} else if (hpPercent < 50) {
			SoundManager.playBodyHitSound();
		}
		this.decreaseHealth(PlayerController.getGunDamage());
	}

	@Override
	public void continuePause(long duration) {
		this.getHunger().addLastFed(duration);
	}

	public ArrayList<Unit> getTargetFishes() {
		return targetFishes;
	}

	public void setTargetFishes(ArrayList<Unit> targetFishes) {
		this.targetFishes = targetFishes;
	}

	public static Image getDestructorimage() {
		return DestructorImage;
	}

}
