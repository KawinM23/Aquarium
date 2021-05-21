package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import manager.GameManager;
import manager.SoundManager;
import manager.TankManager;
import model.Food;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Guppy extends Fish implements Renderable {

	private boolean isStar; // StarGuppy
	private int growth; // 0-99 Baby / 100+ Medium / 200 Large
	private long bornTime;

	private static final Image GuppyLeftImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());
	private static final Image GuppyRightImage = new Image(ClassLoader.getSystemResource("GuppyRight.png").toString());
	private static final Image GuppyHungryLeftImage = new Image(
			ClassLoader.getSystemResource("GuppyHungryLeft.png").toString());
	private static final Image GuppyHungryRightImage = new Image(
			ClassLoader.getSystemResource("GuppyHungryRight.png").toString());

	public Image getImage() {
		return GuppyLeftImage;
	}

	public Guppy(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setWidth(50);
		this.setHeight(50);
		this.setMouthPos(15, 30);

		this.setSpeed(80);
		this.setVelZero();

		this.setStar(false);
		this.growth = 0;
		this.setBornTime(System.nanoTime());

		this.setPrice(100);
		this.setHunger(new Hunger(8, 25));
		this.setProduction(new Production(this, 0, 6 + Math.random()));
		this.setIdle(new Idle(this, 30));
	}

	public int getGrowth() {
		return growth;
	}

	public void setGrowth(int growth) {
		if (growth < 0) {
			this.growth = growth;
		} else if (growth > 200) {
			this.growth = 200;
		} else {
			this.growth = growth;
		}
	}

	public boolean isStar() {
		return isStar;
	}

	public void setStar(boolean isStar) {
		this.isStar = isStar;
	}

	public long getBornTime() {
		return bornTime;
	}

	public void setBornTime(long bornTime) {
		this.bornTime = bornTime;
	}

	public void feed(Unit nearestFood) {
		// Play Sound Effect
		SoundManager.playEatSound();

		this.getHunger().setLastFedNow();
		this.getHunger().addLastFedRandom(0, 2);

		if (nearestFood instanceof Food) {
			if (growth < 200 && ((Food) nearestFood).getFoodType() == 2) {
				this.die();
				return;
			} else if (growth == 200 && ((Food) nearestFood).getFoodType() == 2) {
				this.setStar(true);
				this.getProduction().setProductType(5);
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
		if (!isStar) {
			if (this.growth >= 100 && this.growth < 200 && getProduction().getProductType() != 1) {
				this.setGuppy("Medium");
			} else if (this.growth >= 200 && getProduction().getProductType() != 2) {
				this.setGuppy("Large");
			}
		} else if (this.getProduction().getProductType() != 5) {
			this.getProduction().setProductType(5);
		}
	}

	private void setGuppy(String string) {
		// TODO Auto-generated method stub
		if (string == null) {
			return;
		}
		if (string.equals("Small")) {
			this.setSize(50,50);
			this.setMouthPos(15, 30);
			this.getProduction().setProductType(1);
		} else if (string.equals("Medium")) {
			this.setSize(75, 75);
			this.setMouthPos(15, 55);
			this.setPos(getPosX() - 12.5, getPosY() - 12.5);
			this.getProduction().setProductType(1);
		} else if (string.equals("Large")) {
			this.setSize(100, 100);
			this.setMouthPos(15, 75);
			this.setPos(getPosX() - 12.5, getPosY() - 12.5);
			this.getProduction().setProductType(2);
		}
	}

	@Override
	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
		if (getPosX() <= 0) {
			setPosX(0);
		} else if (getPosX() + getWidth() >= GameManager.getWIDTH()) {
			setPosX(GameManager.getWIDTH() - getWidth());
		}
		if (growth < 100 && getPosY() < GameManager.getTOPHEIGHT() + 40) {
			setPosY(GameManager.getTOPHEIGHT() + 40);
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		if (isStar) {
			gc.setGlobalAlpha(0.8);
			gc.setEffect(new Glow(0.8));
		}
		if (getHunger().checkHunger() == 2) {
			if (isFacingLeft()) {
				gc.drawImage(GuppyHungryLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
			} else {
				gc.drawImage(GuppyHungryRightImage, getPosX(), getPosY(), getWidth(), getHeight());
			}
		} else {

			if (isFacingLeft()) {
				gc.drawImage(GuppyLeftImage, getPosX(), getPosY(), getWidth(), getHeight());
			} else {
				gc.drawImage(GuppyRightImage, getPosX(), getPosY(), getWidth(), getHeight());
			}
		}

		gc.setGlobalAlpha(1);
		gc.setEffect(null);

	}

}
