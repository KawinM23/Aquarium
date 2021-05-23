package model.money;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.base.Money;
import properties.Renderable;

public class Star extends Money implements Renderable{

	private static final Image STAR_IMAGE = new Image(ClassLoader.getSystemResource("Star.png").toString());
	
	public Star(String name, double posX, double posY) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		
		this.setValue(40);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(STAR_IMAGE, getPosX(), getPosY(), getWidth(), getHeight());
	}

}
