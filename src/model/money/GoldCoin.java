package model.money;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.base.Money;
import properties.Renderable;

public class GoldCoin extends Money implements Renderable{
	
	private Image GoldCoinImage = new Image(ClassLoader.getSystemResource("SilverCoin.png").toString());

	public GoldCoin(String name, double posX, double posY) {
		super(name, posX, posY);
		
		this.setValue(15);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(GoldCoinImage, getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	
}
