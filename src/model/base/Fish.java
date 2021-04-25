package model.base;

import javafx.scene.canvas.GraphicsContext;
import properties.Hunger;
import properties.Production;

public abstract class Fish extends Unit {
	

	protected Hunger hunger;
	protected Production production;
	
	public Fish(String name, double posX, double posY) {
		super(name, posX, posY);
		this.hunger = hunger;
		this.production = production;
	}
	
	//void update
	public void update() {
		
		
	}
}
