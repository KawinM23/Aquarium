package model;

import model.base.Money;

public class Diamond extends Money {

	private int type;
	private double fallAcc;

	public Diamond(String name, double posX, double posY, int type) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.type = type;
	}

}
