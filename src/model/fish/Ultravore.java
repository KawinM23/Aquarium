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

public class Ultravore extends Fish implements Renderable {

	private static final Image UltravoreImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Image getImage() {
		return UltravoreImage;
	}

	public Ultravore(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setWidth(143);
		this.setHeight(103);
		this.setSpeed(100);
		this.setVelZero();

		this.setHunger(new Hunger(Carnivore.class, 5, 30));
		this.setProduction(new Production(this, 9, 10));
		this.setIdle(new Idle(this, 25));
		this.setPrice(10000);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isFacingLeft()) {
			gc.drawImage(UltravoreImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(UltravoreImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void findFood() {
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFood = null;
			// Find NearestFood
			for (Fish f : TankManager.getFishList()) {
				if (f instanceof Carnivore) {
					if (nearestFood == null) {
						nearestFood = f;
					} else if (this.distance(f) < this.distance(nearestFood)) {
						nearestFood = f;
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
			// No Food ,Idle
			this.getIdle().checkIdle();
		}
	}

}
