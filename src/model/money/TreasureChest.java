package model.money;

import model.base.Money;

public class TreasureChest extends Money {

	public TreasureChest(String name, double posX, double posY) {
		super(name, posX, posY);
		
		this.setValue(2000);
	}

}
