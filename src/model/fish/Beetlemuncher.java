package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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

public class Beetlemuncher extends Fish implements Renderable{

	private Image BeetlemuncherImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Beetlemuncher(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setWidth(100);
		this.setHeight(70);
		this.setSpeed(100);
		this.setVelZero();

		this.setHunger(new Hunger(Beetle.class, 6, 20));
		this.setProduction(new Production(this, 7, 9));
		this.setIdle(new Idle(this, 20));
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
		TankManager.produceMoney(new Pearl("Pearl", this.getCenterX(), this.getCenterY()));
		this.getHunger().setLastFedNow();
	}
	
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isFacingLeft()) {
			gc.drawImage(BeetlemuncherImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(BeetlemuncherImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

}
