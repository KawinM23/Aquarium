package model.money;

import javafx.scene.image.Image;
import model.base.Money;

public class Star extends Money {

	public Star(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setWidth(36);
		this.setHeight(40);
		
		this.setImg(new Image("file:res/image/Star.png"));
	}

}
