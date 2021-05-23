package model.money;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.base.Money;
import properties.Renderable;

public class TreasureChest extends Money implements Renderable{

	private static final Image TREASURE_CHEST_IMAGE = new Image(ClassLoader.getSystemResource("TreasureChest.png").toString());
	
	public TreasureChest(String name, double posX, double posY) {
		super(name, posX, posY);
		
		this.setSize(70, 70);
		
		this.setValue(2000);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(TREASURE_CHEST_IMAGE, getPosX(), getPosY(), getWidth(), getHeight());
	}
	
}
