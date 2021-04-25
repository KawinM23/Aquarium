package properties;

import model.base.Unit;

public class Production {
	
	private Unit product;
	private long lastProduce;
	private double cooldown;
	
	
	
	
	public Production(Unit product, double cooldown) {
		super();
		this.product = product;
		this.lastProduce = System.nanoTime();
		this.cooldown = cooldown;
	}
	public Unit getProduct() {
		return product;
	}
	public void setProduct(Unit product) {
		this.product = product;
	}
	public long getLastProduce() {
		return lastProduce;
	}
	public void setLastProduce(long lastProduce) {
		this.lastProduce = lastProduce;
	}
	public double getCooldown() {
		return cooldown;
	}
	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}
	
	
	
	
}
