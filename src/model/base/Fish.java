package model.base;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import manager.TankManager;
import model.Food;
import model.Guppy;
import properties.Hunger;
import properties.Production;

public class Fish extends Unit {

	private boolean isFacingRight;
	private Hunger hunger;
	private Production production;
	private double mouthPosX;
	private double mouthPosY;

	public Fish(String name, double posX, double posY) {
		super(name, posX, posY);
		this.isFacingRight = false;
		this.hunger = null;
		this.production = null;
	}

	public void findFood() {

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
				System.out.println(this.getName()+" eat "+nearestFood.getName());
				this.feed(nearestFood);
				TankManager.remove(nearestFood);
				this.setVelZero();
			} else {
				// Go to food
				this.headToFood(nearestFood);
			}

		} else {
			// Idle No food
			this.setVelZero();
		}

	}

	public boolean isAtMounth(Unit nearestFood) {
		return nearestFood.getBoundary().contains(new Point2D(getMouthPosX(), getMouthPosY()));
	}

	public void feed(Unit nearestFood) {
		this.hunger.resetTime();
	}

	public void headToFood(Unit u) {
		this.setVelX(
				((u.getCenterX() - getMouthPosX()) / u.distance(getMouthPosX(), getMouthPosY())) * this.getSpeed());
		this.setVelY(
				((u.getCenterY() - getMouthPosY()) / u.distance(getMouthPosX(), getMouthPosY())) * this.getSpeed());
	}
	
	public void die() {
		System.out.println(this.getName()+" die");
		TankManager.remove(this);
	}

	@Override
	public void update(int fr) {
		switch (hunger.checkHunger()) {
		case 0:
			// idle
			break;
		case 1:
			// find food
			this.findFood();
			break;
		case 2:
			// die
			this.die();
			return;
		}

		this.production.checkProduce();

		this.move(fr);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());

	}

	public double getMouthPosX() {
		return getPosX() + mouthPosX;
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

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public void setFacingRight(boolean isFacingRight) {
		this.isFacingRight = isFacingRight;
	}

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

}
