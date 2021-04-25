package model.base;

import javafx.scene.canvas.GraphicsContext;
import properties.Hunger;
import properties.Production;

public abstract class Fish extends Unit {
	

	private Hunger hunger;
	private Production production;
	
	public Fish(String name, double posX, double posY, double width, double height, int speed,Hunger hunger,Production production) {
		super(name, posX, posY, width, height, speed);
		this.hunger = hunger;
		this.production = production;
	}
	
	//void update
	
}
