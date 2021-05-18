package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.TankManager;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Carnivore extends Fish implements Renderable{
	
	private static final Image CarnivoreImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Image getImage() {
		return CarnivoreImage;
	}
	
	public Carnivore(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(100);
		this.setHeight(70);
		this.setMouthPos(10, 50);
		this.setSpeed(100);
		this.setVelZero();

		this.setHunger(new Hunger(Guppy.class, 3, 10));
		this.setProduction(new Production(this, 3, 6));
		this.setIdle(new Idle(this,15));
		this.setPrice(1000);
	}

	public void findFood() {
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFood = null;
			// Find NearestFood
			for (Fish f : TankManager.getFishList()) {
				if (f instanceof Guppy) {
					if (((Guppy) f).getGrowth() < 100) {
						if (nearestFood == null) {
							nearestFood = f;
						} else if (this.distance(f) < this.distance(nearestFood)) {
							nearestFood = f;
						}
					}
				}
			}
			// Check Food position and Fish
			if (nearestFood != null) {
				if (isAtMounth(nearestFood)) {
					// Eat baby guppy
					TankManager.remove(nearestFood);
					this.getIdle();
					this.feed(nearestFood);
				} else {
					// Go to food
					this.headToUnit(nearestFood);
					this.getIdle().slowIdle();
				}
			} else {
				this.getIdle().checkIdle();
			}
		} else {
			//No Food ,Idle
			this.getIdle().checkIdle();
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		if (isFacingLeft()) {
			gc.drawImage(CarnivoreImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(CarnivoreImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

}
