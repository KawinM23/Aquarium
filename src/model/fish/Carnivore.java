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
	
	private static final Image CarnivoreLeftImage = new Image(ClassLoader.getSystemResource("CarnivoreLeft.png").toString());
	private static final Image CarnivoreRightImage = new Image(ClassLoader.getSystemResource("CarnivoreRight.png").toString());

	public Image getImage() {
		return CarnivoreLeftImage;
	}
	
	public Carnivore(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(115);
		this.setHeight(100);
		this.setMouthPos(20, 75);
		
		this.setSpeed(90);
		this.setVelZero();

		this.setHunger(new Hunger(5, 20));
		this.setProduction(new Production(this, 3, 8));
		this.setIdle(new Idle(this,20));
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
					this.feed(nearestFood);
					this.getIdle().eatFood();;
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
			gc.drawImage(CarnivoreLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(CarnivoreRightImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

}
