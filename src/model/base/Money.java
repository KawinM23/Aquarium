package model.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.TankManager;

public class Money extends Unit {

	public Money(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setVel(0, 40);
		this.setWidth(30);
		this.setHeight(30);
	}

	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		if(this.getPosY()+(this.getHeight()/2.0) > GameManager.getHEIGHT()) {
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

}