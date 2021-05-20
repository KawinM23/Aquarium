package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Food;
import model.base.Fish;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Breeder extends Fish implements Renderable{
	
	private static final Image BreederImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Image getImage() {
		return BreederImage;
	}

	public Breeder(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setWidth(46);
		this.setHeight(42);
		this.setSpeed(70);
		this.setVelZero();

		this.setHunger(new Hunger(3, 10));
		this.setProduction(new Production(this, 8, 15));
		this.setIdle(new Idle(this, 20));
		this.setPrice(200);
	}

	
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isFacingLeft()) {
			gc.drawImage(BreederImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(BreederImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}
}
