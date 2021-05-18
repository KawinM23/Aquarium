package model.base;

import manager.GameManager;
import manager.PlayerController;
import manager.TankManager;

public abstract class Money extends Unit {

	private int value;

	public Money(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setVel(0, 50);
		this.setWidth(30);
		this.setHeight(30);

		this.setValue(0);
	}

	@Override
	public void update(int fr) {
		if (this.getPosY() + this.getHeight() >= GameManager.getBOTTOMHEIGHT()) {
			TankManager.remove(this);
			return;
		} else if (this.getPosY() <= GameManager.getTOPHEIGHT()) {
			TankManager.remove(this);
			return;
		}
		this.move(fr);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void collected() {
		TankManager.remove(this);
		PlayerController.addMoney(value);
	}

}
