package model.money;

import javafx.scene.image.Image;
import model.base.Money;

public class SilverCoin extends Money {

	public SilverCoin(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO More Specfic Var
		
		final String SilverCoinPath = ClassLoader.getSystemResource("SilverCoin.png").toString();
		this.setImg(new Image(SilverCoinPath));
		
		this.setValue(15);
	}
	
}
