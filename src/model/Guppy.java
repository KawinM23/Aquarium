package model;

import javafx.scene.canvas.GraphicsContext;
import model.base.Fish;
import properties.Hunger;
import properties.Production;

public class Guppy extends Fish{

	private int growth;
	
	public Guppy(String name, double posX, double posY){
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(100);
		this.setHeight(30);
	}

	@Override
	public void update(long t) {
		// TODO Auto-generated method stub
		switch(hunger.checkHunger()) {
		case 0:
			//idle
			break;
		case 1:
			//find food
			break;
		case 2:
			//die
			break;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(getImg(), getPosX(), getPosY(), getWidth(), getHeight());
	}

	

	
	
}
