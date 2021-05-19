package model.base;

import manager.GameManager;
import manager.PlayerController;
import manager.StatTracker;
import manager.TankManager;

public abstract class Money extends Unit {

	private int value;
	private long disappearTime;

	public Money(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setVel(0, 40);
		this.setSize(45, 45);

		this.setValue(0);
		this.setDisappearTime(0);
	}

	@Override
	public void update(int fr) {
		if (this.getPosY() + this.getHeight() >= GameManager.getBOTTOMHEIGHT() && disappearTime == 0) {
			this.setDisappearTime((long) (System.nanoTime() + 5 * 1e9));
			return;
		} else if (this.getPosY() <= GameManager.getTOPHEIGHT()) {
			TankManager.remove(this);
			return;
		}
		if (System.nanoTime() >= disappearTime && disappearTime != 0) {
			TankManager.remove(this);
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
		StatTracker.addMoneyGain(value);
	}

	public long getDisappearTime() {
		return disappearTime;
	}

	public void setDisappearTime(long disappearTime) {
		this.disappearTime = disappearTime;
	}

	public void addDisappearTime(long disappearTime) {
		this.disappearTime = this.disappearTime + disappearTime;
	}

}
