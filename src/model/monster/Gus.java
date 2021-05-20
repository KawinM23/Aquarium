package model.monster;

import javafx.scene.canvas.GraphicsContext;
import manager.TankManager;
import model.Food;
import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Renderable;

public class Gus extends Monster implements Renderable {

	public Gus(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub

		this.setSize(100, 100);

		this.setHealth(300);
		this.setHunger(new Hunger(Fish.class, 0.1, 0));
		this.setIdle(new Idle(this, 30));
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		Unit nearestFood = null;
		if (TankManager.getFoodList().size() != 0) {
			nearestFood = TankManager.getFoodList().get(0);
			for (Food f : TankManager.getFoodList()) {
				if (this.distance(f) < this.distance(nearestFood)) {
					nearestFood = f;
				}
			}
		}
		for (Fish f : TankManager.getFishList()) {
			if (this.distance(f) < this.distance(nearestFood)) {
				nearestFood = f;
			}
		}

		if (this.getInnerHitbox(getInnerX(), getInnerY()).contains(nearestFood.getCenterX(),
				nearestFood.getCenterY())) {
			// eat & levelup
			System.out.println(this.getName() + " eat " + nearestFood.getName());
			this.eat(nearestFood);
			this.getIdle().checkIdleMonster();
		} else {
			// Go to food
			this.headToUnit(nearestFood);
		}

	}

	private void eat(Unit nearestFood) {
		TankManager.remove(nearestFood);
		if (nearestFood instanceof Fish) {
			decreaseHealth(20);
		} else if (nearestFood instanceof Food) {
			if (((Food) nearestFood).getFoodType() == 2) {
				decreaseHealth(100);
			} else if(((Food) nearestFood).getFoodType() == 1) {
				switch(((Food) nearestFood).getFoodLevel()){
				case 1:
					decreaseHealth(10);
					break;
				case 2:
					decreaseHealth(20);
					break;
				case 3:
					decreaseHealth(30);
					break;
			}
			}
		}

	}

	@Override
	public void getHit() {
	}

	@Override
	public void continuePause(long duration) {
		getHunger().addLastFed(duration);
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

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

}
