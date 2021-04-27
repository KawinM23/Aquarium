package model.base;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import manager.TankManager;
import model.Food;
import properties.Hunger;
import properties.Production;

public class Fish extends Unit {

	
	protected Hunger hunger;
	protected Production production;

	public Fish(String name, double posX, double posY) {
		super(name, posX, posY);
	}

	public void findFood() {
		// TODO Auto-generated method stub

		switch (this.hunger.getFoodType()) {
		case 0: // Food
			if (TankManager.getFoodList().size() != 0) {
				System.out.println(TankManager.getFishList());
				Unit nearestFood = TankManager.getFoodList().get(0);
				for (Food f : TankManager.getFoodList()) {
					if (this.distance(f) < this.distance(nearestFood)) {
						nearestFood = f;
					}
				}
				System.out.println(nearestFood.getPosX() + " " + nearestFood.getPosY());
				System.out.println(this.getVelX() + " " + this.getVelY());
				
				//Check Food position and Fish
				if (nearestFood.getBoundary().contains(new Point2D(this.getPosX()+5, this.getPosY()+40))) {
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
		case 1: // Guppy mini

		case 2: // Carnivore

		}
	}

	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		switch (hunger.checkHunger()) {
		case 0:
			// idle
			System.out.println("" + System.nanoTime() / 1.0e9 + " Chill");
			break;
		case 1:
			System.out.println("" + System.nanoTime() / 1.0e9 + " Hungry");
			// find food
			this.findFood();
			break;
		case 2:
			// die
			System.out.println("" + System.nanoTime() / 1.0e9 + " Dying");
			break;
		}
	
		this.move(fr);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());

	}
}
