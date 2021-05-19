package model.monster;

import javafx.scene.canvas.GraphicsContext;
import model.base.Fish;
import model.base.Monster;
import properties.Hunger;
import properties.Renderable;

public class Balrog extends Monster implements Renderable {

	
	private Hunger hunger;
	
	public Balrog(String name, double posX, double posY) {
		super(name, posX, posY);
		
		this.setSize(135, 200);
		this.setSpeed(80);
		
		this.setHealth(220);
		this.hunger = new Hunger(Fish.class, 5, 0);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getHit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int fr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
