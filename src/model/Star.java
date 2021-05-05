package model;

import javafx.scene.image.Image;
import model.base.Money;

public class Star extends Money {

	public Star(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(20);
		this.setHeight(20);
		
		this.setImg(new Image("file:res/image/SilverCoin.png"));
	}

}
