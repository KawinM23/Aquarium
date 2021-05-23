package model.monster;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import manager.SoundManager;
import manager.TankManager;
import model.Food;
import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Renderable;

public class Gus extends Monster implements Renderable {

	private static final Image GusLeftImage = new Image(ClassLoader.getSystemResource("GusLeft.png").toString());
	private static final Image GusRightImage = new Image(ClassLoader.getSystemResource("GusRight.png").toString());
	
	public Gus(String name, double posX, double posY, int health) {
		super(name, posX, posY);

		this.setSize(150, 200);
		this.setSpeed(60);

		this.setMaxHealth(300);
		this.setMaxHealth(health);
		this.setHealth(getMaxHealth());

		this.setHunger(new Hunger(0.1, 0));
		this.setIdle(new Idle(this, 30));
	}

	@Override
	public void attack() {
		Unit nearestFood = TankManager.getFishList().get(0);
		if (TankManager.getFoodList().size() != 0) {
			nearestFood = TankManager.getFoodList().get(0);
			for (Food food : TankManager.getFoodList()) {
				if (this.distance(food) < this.distance(nearestFood)) {
					nearestFood = food;
				}
			}
		}
		for (Fish fish : TankManager.getFishList()) {
			if (this.distance(fish) < this.distance(nearestFood)) {
				nearestFood = fish;
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
		SoundManager.playEatSound();
		Thread feedThread = new Thread(() -> {
			try {
				TankManager.remove(nearestFood);
				if (nearestFood instanceof Fish) {
					decreaseHealth(15);
				} else if (nearestFood instanceof Food) {
					if (((Food) nearestFood).getFoodType() == 2) {
						decreaseHealth(100);
					} else if (((Food) nearestFood).getFoodType() == 1) {
						switch (((Food) nearestFood).getFoodLevel()) {
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
				getHunger().setLastFedNow();
				System.out.println(getName() + " Health: " + getHealth());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		feedThread.start();

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
		if (this.getHealth() <= 0) {
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

	@Override
	public void render(GraphicsContext gc) {
		gc.setStroke(new Color(1, 0, 0, 1));
		gc.strokeRect(getPosX(), getPosY(), getWidth(), getHeight());
		gc.strokeRect(getPosX() + getInnerX(), getPosY() + getInnerY(), getWidth() - (2 * getInnerX()),
				getHeight() - (2 * getInnerY()));
		if (isFacingLeft()) {
			gc.drawImage(GusLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(GusRightImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

}
