package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.SoundManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Money;
import model.base.Unit;
import model.money.Beetle;
import model.money.Pearl;
import model.money.Star;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Beetlemuncher extends Fish implements Renderable {

	private static final Image BeetlemuncherLeftImage = new Image(ClassLoader.getSystemResource("BeetlemuncherLeft.png").toString());
	private static final Image BeetlemuncherRightImage = new Image(ClassLoader.getSystemResource("BeetlemuncherRight.png").toString());
	private static final Image BeetlemuncherHungryLeftImage = new Image(ClassLoader.getSystemResource("BeetlemuncherHungryLeft.png").toString());
	private static final Image BeetlemuncherHungryRightImage = new Image(ClassLoader.getSystemResource("BeetlemuncherHungryRight.png").toString());
	
	
	public Image getImage() {
		return BeetlemuncherLeftImage;
	}

	public Beetlemuncher(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setSize(100, 110);
		this.setMouthPos(15, 60);

		this.setSpeed(100);

		this.setHunger(new Hunger(6, 20));
		this.setProduction(new Production(this, 7, 9));
		this.setIdle(new Idle(this, 30));
		this.setPrice(2000);
	}

	public void findFood() {
		// TODO
		if (TankManager.getMoneyList().size() != 0) {
			Unit nearestFood = null;
			// Find NearestFood
			for (Money m : TankManager.getMoneyList()) {
				if (m instanceof Beetle) {
					if (nearestFood == null) {
						nearestFood = m;
					} else if (this.distance(m) < this.distance(nearestFood)) {
						nearestFood = m;
					}
				}
			}
			// Check Food position and Fish
			if (nearestFood != null) {
				if (this.intersects(nearestFood)) {
					// Eat
					TankManager.remove(nearestFood);
					this.feed(nearestFood);
					this.getIdle().checkIdle();
				} else {
					this.headToFood(nearestFood);
					if (Math.abs(this.distanceX(nearestFood)) < 10) {
						this.setVelX(0);
					}
					this.getIdle().slowIdle();
				}
			} else {
				// No food
				this.getIdle().checkIdle();
			}
		} else {
			// No food
			this.getIdle().checkIdle();
		}
	}

	public void feed(Unit nearestFood) {
		if (!TankManager.getRemoveFoodList().contains(nearestFood)) {
			Thread feedThread = new Thread(() -> {
				try {
					// Play Sound Effect
					SoundManager.playEatSound();

					TankManager.remove(nearestFood);
					this.getHunger().setLastFedNow();
					this.getHunger().addLastFedRandom(0, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			feedThread.start();
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isHungry()) {
			if (isFacingLeft()) {
				gc.drawImage(BeetlemuncherHungryLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
			} else {
				gc.drawImage(BeetlemuncherHungryRightImage, getPosX(), getPosY(), getWidth(), getHeight());
			}
		} else {

			if (isFacingLeft()) {
				gc.drawImage(BeetlemuncherLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
			} else {
				gc.drawImage(BeetlemuncherRightImage, getPosX(), getPosY(), getWidth(), getHeight());
			}
		}
	}

}
