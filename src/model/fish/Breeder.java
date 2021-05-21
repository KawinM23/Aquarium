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

public class Breeder extends Fish implements Renderable{
	
	private static final Image BreederImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	private int growth; // 0-99 Baby / 100+ Medium / 200 Large
	
	public Image getImage() {
		return BreederImage;
	}

	public Breeder(String name, double posX, double posY) {
		super(name, posX, posY);
		//TODO SIze
		this.setSize(67,67);
		this.setMouthPos(10, 50);
		this.setSpeed(70);
		
		this.growth = 0;

		this.setHunger(new Hunger(7, 20));
		this.setProduction(new Production(this, 8, 25));
		this.setIdle(new Idle(this, 20));
		this.setPrice(200);
	}
	
	public void feed(Unit nearestFood) {
		// Play Sound Effect
		SoundManager.playEatSound();
	
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

	}
	
	private void setBreeder(String string) {
		if (string == null) {
			return;
		}
		//TODO SIze
		if (string.equals("Small")) {
			this.setSize(65,65);
			this.setMouthPos(10, 50);
		} else if (string.equals("Medium")) {
			this.setSize(80, 80);
			this.setMouthPos(15, 55);
			this.setPos(getPosX() - 7.5, getPosY() - 7.5);
			this.getProduction().setCooldown(20);
		} else if (string.equals("Large")) {
			this.setSize(95, 95);
			this.setMouthPos(20, 75);
			this.setPos(getPosX() - 7.5, getPosY() - 7.5);
			this.getProduction().setCooldown(25);
		}
	}

	
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isFacingLeft()) {
			gc.drawImage(BreederImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(BreederImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	public int getGrowth() {
		return growth;
	}

	public void setGrowth(int growth) {
		this.growth = growth;
	}
}
