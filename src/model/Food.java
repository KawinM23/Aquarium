package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.TankManager;
import model.base.Unit;

public class Food extends Unit {

	private int foodType; // 1 Normal , 2 Potion
	private int foodLevel; // 1,2,3

	public Food(String name, double posX, double posY, int foodType) {
		super(name, posX, posY);
		this.setFoodType(foodType);
		if (foodType == 1) {
			this.setWidth(30);
			this.setHeight(30);
		} else if (foodType == 2) {
			this.setWidth(40);
			this.setHeight(40);
		}
		this.setVel(0, 40);

		this.setImg(new Image("file:res/image/Food1.png")); //TODO DifferentPic
		//TODO Set Food Level

	}

	@Override
	public void update(int fr) {
		if (this.getPosY() + this.getHeight() > GameManager.getBOTTOMHEIGHT()) {
			TankManager.remove(this);
			return;
		}
		this.move(fr);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());
	}

	public int getFoodType() {
		return foodType;
	}

	public void setFoodType(int foodType) {
		this.foodType = foodType;
	}

}
