package properties;

import manager.TankManager;
import model.SilverCoin;
import model.base.Unit;

public class Production {

	private Unit producer;
	private int productType;
	
	private long lastProduce;
	private double cooldown;

	public Production(Unit producer,int productType, double cooldown) {
		super();
		this.producer = producer;
		this.productType = productType;
		this.lastProduce = System.nanoTime();
		this.cooldown = cooldown;
	}

	public void checkProduce() {

		double deltaTime = (System.nanoTime() - getLastProduce()) / 1.0e9;
		if (deltaTime > cooldown) {
			switch (this.productType) {
			case 0:
				TankManager.produceMoney(new SilverCoin("SilverCoin", producer.getCenterX(), producer.getCenterY()));
				setLastProduceNow();
				break;
			case 1:


				break;
			case 2:

				break;
			}
		}
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

}
