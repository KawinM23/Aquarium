package model.money;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.base.Money;
import properties.Renderable;

public class Beetle extends Money implements Renderable{

	private static final Image BeetleImage = new Image(ClassLoader.getSystemResource("Beetle.png").toString());
	
	public Beetle(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setSize(45, 40);
		
		this.setVelY(-55);
		
		this.setValue(150);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(BeetleImage, getPosX(), getPosY(), getWidth(), getHeight());
	}

}
