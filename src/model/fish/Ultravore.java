package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.SoundManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Ultravore extends Fish implements Renderable {

	private static final Image UltravoreLeftImage = new Image(ClassLoader.getSystemResource("UltravoreLeft.png").toString());
	private static final Image UltravoreRightImage = new Image(ClassLoader.getSystemResource("UltravoreRight.png").toString());
	private static final Image UltravoreHungryLeftImage = new Image(
			ClassLoader.getSystemResource("UltravoreHungryLeft.png").toString());
	private static final Image UltravoreHungryRightImage = new Image(
			ClassLoader.getSystemResource("UltravoreHungryRight.png").toString());

	public Image getImage() {
		return UltravoreLeftImage;
	}

	public Ultravore(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setSize(215, 155);
		this.setMouthPos(45, 100);
		
		this.setSpeed(60);

		this.setHunger(new Hunger(10, 32));
		this.setProduction(new Production(this, 9, 18));
		this.setIdle(new Idle(this, 20));
		
		this.setPrice(10000);
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
					SoundManager.playEatFishSound();
					TankManager.remove(nearestFood);
					this.getIdle().eatFood();;
					this.feed(nearestFood);
				} else {
					// Go to food
					this.headToFood(nearestFood);
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

	@Override
	public void render(GraphicsContext gc) {

		if (isHungry()) {
			if (isFacingLeft()) {
				gc.drawImage(UltravoreHungryLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
			} else {
				gc.drawImage(UltravoreHungryRightImage, getPosX(), getPosY(), getWidth(), getHeight());
			}
		} else {

			if (isFacingLeft()) {
				gc.drawImage(UltravoreLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
			} else {
				gc.drawImage(UltravoreRightImage, getPosX(), getPosY(), getWidth(), getHeight());
			}
		}
	}

}
