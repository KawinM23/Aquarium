package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.PlayerController;
import manager.TankManager;
import model.base.Unit;

public class Food extends Unit {

	private int foodType; // 1 Normal , 2 Potion
	private int foodLevel; // 1,2,3
	private int maxFood; //

	public Food(String name, double posX, double posY, int foodType) {
		super(name, posX, posY);
		this.setFoodType(foodType);
		if (foodType == 1) {
			this.setWidth(30);
			this.setHeight(30);
			this.setFoodLevel(PlayerController.getFoodLevel());
		} else if (foodType == 2) {
			this.setWidth(40);
			this.setHeight(40);
			this.setFoodLevel(0);
		}
		this.setPos(posX - (getWidth() / 2), posY - (getHeight() / 2)); // Slide to match mouse position

		this.setVel(0, 40);

		this.setImg(new Image("file:res/image/Food1.png")); // TODO DifferentPic
	}

	@Override
	public void update(int fr) {
		if (this.getPosY() + this.getHeight() >= GameManager.getBOTTOMHEIGHT()) {
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

	public int getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	public int getMaxFood() {
		return maxFood;
	}

	public void setMaxFood(int maxFood) {
		this.maxFood = maxFood;
	}

}
