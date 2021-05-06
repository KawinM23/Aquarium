package model;

import javafx.scene.image.Image;
import model.base.Money;

public class Diamond extends Money {

	private int type;
	private double fallAcc;

	public Diamond(String name, double posX, double posY, int type) {
		super(name, posX, posY);
		this.type = type;
		
		this.setImg(new Image("file:res/image/SilverCoin.png"));
		if(type == 0) {
			this.setVel(0, 50);
		}else if(type ==1) {
			this.setVelY(-60); //TODO Spit Diamond Fall
		}
	}

}
