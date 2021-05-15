package model.money;

import model.base.Money;

public class Beetle extends Money {

	public Beetle(String name, double posX, double posY) {
		super(name, posX, posY);
		
		this.setVelY(-50);
		
		this.setValue(150);
	}

}
