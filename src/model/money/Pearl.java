package model.money;

import model.base.Money;

public class Pearl extends Money {

	public Pearl(String name, double posX, double posY) {
		super(name, posX, posY);
		
		this.setValue(500);
	}

}
