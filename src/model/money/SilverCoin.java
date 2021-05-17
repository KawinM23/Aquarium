package model.money;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.base.Money;
import properties.Renderable;

public class SilverCoin extends Money implements Renderable{

	private Image SilverCoinImage = new Image(ClassLoader.getSystemResource("SilverCoin.png").toString());
	
	public SilverCoin(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO More Specfic Var
		
//		final String SilverCoinPath = ClassLoader.getSystemResource("SilverCoin.png").toString();

		
		this.setValue(15);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(SilverCoinImage, getPosX(), getPosY(), getWidth(), getHeight());
	}
	
}
