package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Food;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Guppy extends Fish implements Renderable {

	private boolean isStar;
	private int growth; // 0-99 Baby / 100+ Medium / 200 Large
	private long bornTime;

	private static final Image GuppyImageLeft = new Image(ClassLoader.getSystemResource("Guppy.png").toString());
	private static final Image GuppyImageRight = new Image(ClassLoader.getSystemResource("GuppyRight.png").toString());

	public static Image getImage() {
		return GuppyImageLeft;
	}

	public Guppy(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setWidth(80);
		this.setHeight(60);
		this.setMouthPos(15, 45);

		this.setSpeed(80);
		this.setVelZero();

		this.isStar = false;
		this.growth = 0;
		this.setBornTime(System.nanoTime());

		this.setPrice(100);
		this.setHunger(new Hunger(Food.class, 3, 20));
		this.setProduction(new Production(this, 0, 5 + Math.random()));
		this.setIdle(new Idle(this, 15));
	}

	public int getGrowth() {
		return growth;
	}

	public void setGrowth(int growth) {
		this.growth = growth;
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
		this.getHunger().setLastFedNow();
		try {
			if (growth < 100 && ((Food) nearestFood).getFoodType() == 2) {
				this.die();
			}
			switch (((Food) nearestFood).getFoodLevel()) {
			case 1:
				this.setGrowth(getGrowth() + 25); // TODO FoodGrowth 25,50,75
			case 2:
				this.setGrowth(getGrowth() + 50);
			case 3:
				this.setGrowth(getGrowth() + 75);
			}
		} catch (Exception e) {
			// TODO Catch Not Food
			e.printStackTrace();
		}

		if (this.growth >= 100 && getProduction().getProductType() != 1) {
			this.getProduction().setProductType(1);
		} else if (this.growth >= 200 && getProduction().getProductType() != 2) {
			this.getProduction().setProductType(2);
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isFacingLeft()) {
			gc.drawImage(GuppyImageLeft, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(GuppyImageRight, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

}
