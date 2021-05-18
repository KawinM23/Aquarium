package properties;

import manager.TankManager;
import model.base.Unit;
import model.fish.Guppy;
import model.money.Beetle;
import model.money.Diamond;
import model.money.GoldCoin;
import model.money.Pearl;
import model.money.SilverCoin;
import model.money.Star;
import model.money.TreasureChest;

public class Production {

	private Unit producer;
	private int productType;

	private long lastProduce;
	private double cooldown;

	public Production(Unit producer, int productType, double cooldown) {
		super();
		this.producer = producer;
		this.productType = productType;
		this.lastProduce = System.nanoTime();
		this.cooldown = cooldown;
	}

	public void checkProduce() {
		if (cooldown == 0) {
			return;
		}
		double deltaTime = getDeltatime();
		if (deltaTime > cooldown) {
			switch (this.productType) {
			case 0:
				setLastProduceNow();
				break;
			case 1:
				TankManager.produceMoney(new SilverCoin("SilverCoin", producer.getCenterX(), producer.getCenterY()));
				setLastProduceNow();
				break;
			case 2:
				TankManager.produceMoney(new GoldCoin("GoldCoin", producer.getCenterX(), producer.getCenterY()));
				setLastProduceNow();
				break;
			case 3:
				TankManager.produceMoney(new Diamond("Diamond", producer.getCenterX(), producer.getCenterY(), 0));
				setLastProduceNow();
				break;
			case 4:
				TankManager.produceMoney(new Diamond("GoldCoin", producer.getCenterX(), producer.getCenterY(), 1));
				setLastProduceNow();
				break;
			case 5:
				TankManager.produceMoney(new Star("Star", producer.getCenterX(), producer.getCenterY()));
				setLastProduceNow();
				break;
			case 6:
				TankManager.produceMoney(new Beetle("Beetle", producer.getCenterX(), producer.getCenterY() - 20));
				setLastProduceNow();
				break;
			case 7:
				TankManager.produceMoney(new Pearl("Pearl", producer.getCenterX(), producer.getCenterY()));
				setLastProduceNow();
				break;
			case 8:
				TankManager.add(new Guppy("Guppy", producer.getCenterX(), producer.getCenterY()));
				setLastProduceNow();
				break;
			case 9:
				TankManager.produceMoney(
						new TreasureChest("Treasure Chest", producer.getCenterX(), producer.getCenterY()));
				setLastProduceNow();
				break;
			}
		}
	}

	public double getDeltatime() {
		return (System.nanoTime() - getLastProduce()) / 1.0e9;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public long getLastProduce() {
		return lastProduce;
	}

	public void setLastProduce(long lastProduce) {
		this.lastProduce = lastProduce;
	}

	public void setLastProduceNow() {
		this.lastProduce = System.nanoTime();
	}

	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	public void endInvasion(long invasionDuration) {
		// TODO
		setLastProduce(lastProduce + invasionDuration);
	}

}
