package model.base;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import manager.TankManager;
import model.Food;
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

	public void findFood(Class food) {
		// TODO Auto-generated method stub

		if (food != null) {
			if (TankManager.getUnitList().size() != 0) {
				Unit nearestFood = null;
				for (Unit u : TankManager.getUnitList()) {
					if (food.isInstance(u) && nearestFood != null) {
						if (this.distance(u) < this.distance(nearestFood)) {
							nearestFood = u;
						}
					} else {
						nearestFood = u;
					}

				}

				// Check Food position and Fish
				if (isAtMounth(nearestFood)) {
					// eat & levelup
					TankManager.remove(nearestFood);
					this.setVelZero();
					this.hunger.feed();
				} else {
					this.headToUnit(nearestFood);
				}
			} else {
				// idle
				this.setVelZero();
			}
		} else {
			System.out.println("No Food Class");
		}

	}

	public boolean isAtMounth(Unit nearestFood) {
		return nearestFood.getBoundary().contains(new Point2D(this.getPosX() + 5, this.getPosY() + 40));
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
			this.findFood(this.hunger.getFoodType());
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
