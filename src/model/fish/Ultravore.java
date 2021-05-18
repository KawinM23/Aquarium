package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.base.Fish;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Ultravore extends Fish implements Renderable{

	private static final Image UltravoreImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public static Image getImage() {
		return UltravoreImage;
	}
	
	public Ultravore(String name, double posX, double posY) {
		super(name, posX, posY);
		this.setWidth(100);
		this.setHeight(70);
		this.setSpeed(100);
		this.setVelZero();

		this.setHunger(new Hunger(Carnivore.class, 5, 30));
		this.setProduction(new Production(this, 9, 10));
		this.setIdle(new Idle(this, 25));
		this.setPrice(10000);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isFacingLeft()) {
			gc.drawImage(UltravoreImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(UltravoreImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

}
