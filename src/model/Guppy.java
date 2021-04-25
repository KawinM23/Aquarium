package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import model.base.Fish;
import properties.Hunger;
import properties.Production;

public class Guppy extends Fish{

	private int growth;
	
	public Guppy(String name, double posX, double posY){
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		

		this.setWidth(90);
		this.setHeight(60);
		this.setVelX(10);
		this.setVelY(10);
		this.setImg(new Image("file:res/image/Guppy.png"));
	}

	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub
//		switch(hunger.checkHunger()) {
//		case 0:
//			//idle
//			break;
//		case 1:
//			//find food
//			break;
//		case 2:
//			//die
//			break;
//		}
		
		this.move(fr);
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());
		
	}

	

	
	
}
