package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.SoundManager;
import manager.TankManager;
import model.Food;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Breeder extends Fish implements Renderable {

	private static final Image BreederLeftImage = new Image(
			ClassLoader.getSystemResource("BreederLeft.png").toString());
	private static final Image BreederRightImage = new Image(
			ClassLoader.getSystemResource("BreederRight.png").toString());
	private static final Image BreederHungryLeftImage = new Image(
			ClassLoader.getSystemResource("BreederHungryLeft.png").toString());
	private static final Image BreederHungryRightImage = new Image(
			ClassLoader.getSystemResource("BreederHungryRight.png").toString());

	private int growth; // 0-99 Baby / 100+ Medium / 200 Large

	public Image getImage() {
		return BreederLeftImage;
	}

	public Breeder(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO SIze
		this.setSize(67, 52);
		this.setMouthPos(7.5, 20);
		this.setSpeed(60);

		this.growth = 0;

		this.setHunger(new Hunger(7, 20));
		this.setProduction(new Production(this, 8, 25));
		this.setIdle(new Idle(this, 25));
		this.setPrice(200);
	}

	public void feed(Unit nearestFood) {
		// Play Sound Effect
		if (!TankManager.getRemoveFoodList().contains(this)) {
			SoundManager.playEatSound();
			Thread feedThread = new Thread(() -> {
				try {

					this.getHunger().setLastFedNow();
					this.getHunger().addLastFedRandom(0, 2);

					if (nearestFood instanceof Food) {
						if (growth < 200 && ((Food) nearestFood).getFoodType() == 2) {
							this.setGrowth(getGrowth() + 40);
							return;
						}
						switch (((Food) nearestFood).getFoodLevel()) {
						case 1:
							this.setGrowth(getGrowth() + 25); // FoodGrowth 25,50,75
							break;
						case 2:
							this.setGrowth(getGrowth() + 50);
							break;
						case 3:
							this.setGrowth(getGrowth() + 75);
							break;
						}
						TankManager.remove(nearestFood);
					}

					if (this.growth >= 100 && this.growth < 200) {
						this.setBreeder("Medium");
					} else if (this.growth >= 200) {
						this.setBreeder("Large");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			feedThread.start();
		}

	}

	public void setBreeder(String string) {
		if (string == null) {
			return;
		}
		//Size
		if (string.equals("Small")) {
			this.setSize(67, 52);
			this.setMouthPos(7.5, 20);
			this.getProduction().setCooldown(25);
		} else if (string.equals("Medium")) {
			this.setSize(80, 62);
			this.setMouthPos(9, 24);
			this.setPos(getPosX() - 6.5, getPosY() - 5);
			this.getProduction().setCooldown(20);
		} else if (string.equals("Large")) {
			this.setSize(95, 74);
			this.setMouthPos(10.5, 28);
			this.setPos(getPosX() - 7.5, getPosY() - 6);
			this.getProduction().setCooldown(18);
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isHungry()) {
			if (isFacingLeft()) {
				gc.drawImage(BreederHungryLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
			} else {
				gc.drawImage(BreederHungryRightImage, getPosX(), getPosY(), getWidth(), getHeight());
			}
		} else {

			if (isFacingLeft()) {
				gc.drawImage(BreederLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
			} else {
				gc.drawImage(BreederRightImage, getPosX(), getPosY(), getWidth(), getHeight());
			}
		}
	}

	public int getGrowth() {
		return growth;
	}

	public void setGrowth(int growth) {
		this.growth = growth;
	}
}
