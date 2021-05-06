package model;

import javafx.scene.image.Image;
import model.base.Money;

public class SilverCoin extends Money {

	public SilverCoin(String name, double posX, double posY) {
		super(name, posX, posY);
		
		
		// TODO More Specfic Var
		this.setImg(new Image("file:res/image/SilverCoin.png"));
	}
	
}
