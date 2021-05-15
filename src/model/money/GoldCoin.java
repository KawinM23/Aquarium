package model.money;

import model.base.Money;

public class GoldCoin extends Money{

	public GoldCoin(String name, double posX, double posY) {
		super(name, posX, posY);
		//TODO IMAGE
		
		this.setValue(15);
	}

}
