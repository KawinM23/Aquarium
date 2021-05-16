package model.fish;

import javafx.scene.image.Image;
import model.Food;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Production;

public class Guppy extends Fish {

	private boolean isStar;
	private int growth; // 0-99 Baby / 100+ Medium / 200 Large
	private long bornTime;
	
	
	private Image GuppyImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Guppy(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setWidth(80);
		this.setHeight(60);
		this.setMouthPos(5, 30);

		this.setSpeed(80);
		this.setVelZero();

		this.setImg(GuppyImage);

		this.isStar = false;
		this.growth = 0;
		this.setBornTime(System.nanoTime());
		
		this.setHunger(new Hunger(Food.class, 3, 20));
		this.setProduction(new Production(this, 0, 5 + Math.random()));
		this.setIdle(new Idle(this,15));
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
		this.setGrowth(getGrowth() + 0); //TODO FoodGrowth
		if (this.growth >= 100 && getProduction().getProductType() != 1) {
			this.getProduction().setProductType(1);
		} else if (this.growth >= 200) {

		}
	}

}
