package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.TankManager;
import model.base.Unit;

public class Food extends Unit {
	
	private int foodType;

	public Food(String name, double posX, double posY,int foodType) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setVel(0, 40);
		this.setWidth(30);
		this.setHeight(30);
		this.setImg(new Image("file:res/image/Food1.png"));
		
		this.setFoodType(foodType);
	}

	
	
	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		if(this.getPosY() + this.getHeight() > GameManager.getBOTTOMHEIGHT()) {
			TankManager.remove(this);
			return;
		}
		this.move(fr);
	}


	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());
	}



	public int getFoodType() {
		return foodType;
	}



	public void setFoodType(int foodType) {
		this.foodType = foodType;
	}

	

}
