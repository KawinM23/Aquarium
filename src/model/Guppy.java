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
		this.setSpeed(100);
		this.setVelX(10);
		this.setVelY(10);
		this.setImg(new Image("file:res/image/Guppy.png"));
		
		this.hunger = new Hunger(0, 3, 15);
	}

	

	

	

	
	
}
