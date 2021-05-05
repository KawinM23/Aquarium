package model.base;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import manager.TankManager;
import model.Food;
import model.Guppy;
import properties.Hunger;
import properties.Production;

public class Fish extends Unit {

	protected boolean isFacingRight;
	protected Hunger hunger;
	protected Production production;

	public Fish(String name, double posX, double posY) {
		super(name, posX, posY);
		this.isFacingRight = false;
		this.hunger = null;
		this.production = null;
	}

	public void findFood() {
		// TODO Auto-generated method stub

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
				this.feed(nearestFood);
				TankManager.remove(nearestFood);
				this.setVelZero();
			} else {
				// Go to food
				this.headToUnit(nearestFood);
			}

		} else {
			this.setVelZero();
			System.out.println("No Food");
		}

	}

	public boolean isAtMounth(Unit nearestFood) {
		return nearestFood.getBoundary().contains(new Point2D(this.getPosX() + 5, this.getPosY() + 40));
	}

	public void feed(Unit nearestFood) {
		this.hunger.resetTime();
	}

	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		switch (hunger.checkHunger()) {
		case 0:
			// idle
			System.out.println(this.getName() + " Chill");
			break;
		case 1:
			System.out.println(this.getName() + " Hungry");
			// find food
			this.findFood();
			break;
		case 2:
			// die
			System.out.println(this.getName() + " Dying");
			TankManager.remove(this);
			return;
		}
		
		this.production.checkProduce();

		this.move(fr);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());

	}
}
