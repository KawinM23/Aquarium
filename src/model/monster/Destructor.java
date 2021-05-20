package model.monster;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import manager.GameManager;
import manager.PlayerController;
import manager.TankManager;
import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Renderable;

public class Destructor extends Monster implements Renderable{

	private static final Image DestructorImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());
	
	public Destructor(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		
		this.setSize(135, 200);
		this.setSpeed(80);

		this.setHealth(100);
		this.setHunger(new Hunger(7, 0));
		this.setIdle(new Idle(this, 20));
	}

	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		if (this.getHealth() <= 0) {
			// TODO Defeat
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
	}
	
	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		if(getPosY() < GameManager.getBOTTOMHEIGHT() - getHeight()) {
			setPosY(GameManager.getBOTTOMHEIGHT()-getHeight());
		}
	}

	@Override
	public void render(GraphicsContext gc) {

		gc.setStroke(new Color(1, 0, 0, 1));
		gc.strokeRect(getPosX(), getPosY(), getWidth(), getHeight());
		gc.strokeRect(getPosX() + getInnerX(), getPosY() + getInnerY(), getWidth() - (2 * getInnerX()), getHeight() - (2 * getInnerY()));
		if (isFacingLeft()) {
			gc.drawImage(DestructorImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(DestructorImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void attack() {
		// TODO Shoot
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFish = TankManager.getFishList().get(0);
			// Find NearestFood
			for (Fish f : TankManager.getFishList()) {
				if (this.distance(f) < this.distance(nearestFish)) {
					nearestFish = f;
				}
			}
			
			this.getIdle().checkIdleMonster();

		} else {
			// Idle No food
			this.getIdle().checkIdleMonster();
		}
	}

	public void getHit() {
		// TODO Onclick Mouse -> decrease Hp
		System.out.println("Hit Monster " + getHealth());
		this.decreaseHealth(PlayerController.getGunDamage());
	}



	@Override
	public void continuePause(long duration) {
		// TODO Auto-generated method stub
		this.getHunger().addLastFed(duration);
	}

}
