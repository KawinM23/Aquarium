package model.money;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import model.base.Money;
import properties.Renderable;

public class Diamond extends Money implements Renderable{

	private int type;
	private double fallAcc;
	
	private static final Image DiamondImage = new Image(ClassLoader.getSystemResource("SilverCoin.png").toString());

	public Diamond(String name, double posX, double posY, int type) {
		super(name, posX, posY);
		this.type = type;
		if (type == 0) {
			this.setVel(0, 50);
		} else if (type == 1) {
			this.setVelY(-120); // TODO Spit Diamond Fall
		}
		
		this.setValue(200);
	}
	
	@Override
	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
		if (getPosX() <= 0) {
			setPosX(0);
		} else if (getPosX() + getWidth() >= GameManager.getWIDTH()) {
			setPosX(GameManager.getWIDTH() - getWidth());
		}
		if (getPosY() <= 0) {
			setPosY(0);
		}
//		else if (getPosY() + getHeight() >= GameManager.getBOTTOMHEIGHT()) {
//			setPosX(GameManager.getBOTTOMHEIGHT() - getHeight());
//		}
	}
	
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(DiamondImage, getPosX(), getPosY(), getWidth(), getHeight());
	}

}
