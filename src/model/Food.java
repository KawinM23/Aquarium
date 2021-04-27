package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.TankManager;
import model.base.Unit;

public class Food extends Unit {

	public Food(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setVel(0, 40);
		this.setWidth(30);
		this.setHeight(30);
		this.setImg(new Image("file:res/image/Food1.png"));
	}

	
	
	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		if(this.getPosY()+(this.getHeight()/2.0) > GameManager.getHEIGHT()) {
			TankManager.remove(this);
			return; //BUG : Some Exception
		}
		this.move(fr);
	}


	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());
	}

	

}
