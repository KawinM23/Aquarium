package model.monster;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import manager.PlayerController;
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

	private int innerX = 20;
	private int innerY = 20;

	public Balrog(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setSize(135, 200);
		this.setSpeed(80);

		this.setHealth(220);
		this.setHunger(new Hunger(Fish.class, 5, 0));
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
			if (this.getInnerHitbox(innerX, innerY).contains(f.getCenterX(), f.getCenterY())) {
				// eat & levelup
				System.out.println(this.getName() + " eat " + f.getName());
				this.eat(f,0);
			} 
		}
		System.out.println(getCenterX()+" "+getCenterY());
		this.move(fr);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFish = TankManager.getFishList().get(0);
			// Find NearestFood
			for (Fish f : TankManager.getFishList()) {
				if (this.distance(f) < this.distance(nearestFish)) {
					nearestFish = f;
				}
			}
			// Check Food position and Fish
			if (this.getInnerHitbox(innerX, innerY).contains(nearestFish.getCenterX(), nearestFish.getCenterY())) {
				// eat & levelup
				System.out.println(this.getName() + " eat " + nearestFish.getName());
				this.eat(nearestFish,1);

				this.getIdle().checkIdle();
			} else {
				// Go to food
				this.headToUnit(nearestFish);
			}

		} else {
			// Idle No food
			this.getIdle().checkIdleMonster();
		}
	}

	private void eat(Unit nearestFish,int i) {
		TankManager.remove(nearestFish);
		if(i == 1) {
			this.getHunger().setLastFedRandom(8, 10);
		}
	}

	@Override
	public void getHit() {
		this.decreaseHealth(PlayerController.getGunDamage());
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setStroke(new Color(1, 0, 0, 1));
		gc.strokeRect(getPosX(), getPosY(), getWidth(), getHeight());
		gc.strokeRect(getPosX() + innerX, getPosY() + innerY, getWidth() - (2 * innerX), getHeight() - (2 * innerY));
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
	
	public Rectangle2D getInnerHitbox(double x, double y) {
		return new Rectangle2D(getPosX() + x, getPosY() + y, getPosX() + getWidth() - x, getPosY() + getHeight() - y);
	}

}
