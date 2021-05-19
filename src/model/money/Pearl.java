package model.money;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.base.Money;
import properties.Renderable;

public class Pearl extends Money implements Renderable{
	
	private Image PearlImage = new Image(ClassLoader.getSystemResource("SilverCoin.png").toString());

	public Pearl(String name, double posX, double posY) {
		super(name, posX, posY);
		
		this.setSize(52, 52);
		this.setValue(500);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(PearlImage, getPosX(), getPosY(), getWidth(), getHeight());
	}

}
