package model.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.PlayerController;
import manager.TankManager;

public class Money extends Unit {
	
	private int value;

	public Money(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setVel(0, 50);
		this.setWidth(40);
		this.setHeight(40);
		
		this.setValue(0);
	}

	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		if (this.getPosY() + this.getHeight() >= GameManager.getBOTTOMHEIGHT()) {
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void collected() {
		TankManager.remove(this);
		PlayerController.addMoney(value);
	}

}
