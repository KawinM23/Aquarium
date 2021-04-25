package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.base.Unit;

public class Food extends Unit {

	public Food(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setVel(0, 50);
		this.setWidth(60);
		this.setHeight(60);
		this.setImg(new Image("file:res/image/Food1.png"));
	}

	
	
	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
		this.move(fr);
	}


	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());
	}

	

}
