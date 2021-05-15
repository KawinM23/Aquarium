package model.monster;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import manager.PlayerController;
import manager.TankManager;
import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import properties.Hunger;

public class Sylvester extends Monster {

	private Hunger hunger;

	public Sylvester(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub

		this.setWidth(100);
		this.setHeight(150);
		this.setSpeed(120);

		this.setImg(new Image("file:res/image/Guppy.png", getWidth(), getHeight(), false, true));

		this.setHealth(200);
		this.hunger = new Hunger(Fish.class, 5, 0);

	}

	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		if (this.getHealth() <= 0) {
			// TODO Defeat
			System.out.println("Defeat");
			TankManager.remove(this);
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

		this.move(fr);
	}

	@Override
	public void draw(GraphicsContext gc) {

		gc.setStroke(new Color(1, 0, 0, 1));
		gc.strokeRect(getPosX(), getPosY(), getWidth(), getHeight());
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());
	}

	@Override
	public void attack() {
		// TODO Try eat one fish
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFish = TankManager.getFishList().get(0);
			// Find NearestFood
			for (Fish f : TankManager.getFishList()) {
				if (this.distance(f) < this.distance(nearestFish)) {
					nearestFish = f;
				}
			}
			// Check Food position and Fish
			if (this.getBoundary().contains(nearestFish.getCenterX(), nearestFish.getCenterY())) {
				// eat & levelup
				System.out.println(this.getName() + " eat " + nearestFish.getName());
				this.eat(nearestFish);
				this.setVelZero();
			} else {
				// Go to food
				this.headToUnit(nearestFish);
			}

		} else {
			// Idle No food
			this.setVelZero();
		}
	}

	private void eat(Unit nearestFish) {
		// TODO Auto-generated method stub
		TankManager.remove(nearestFish);
		this.hunger.setLastFedNow();
	}

	public void getHit() {
		// TODO Onclick Mouse -> decrease Hp
		System.out.println("Hit Monster "+getHealth());
		this.decreaseHealth(PlayerController.getGunDamage());
	}

}
