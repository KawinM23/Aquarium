package model.money;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import jdk.jfr.SettingControl;
import manager.GameManager;
import model.base.Money;
import properties.Renderable;

public class Diamond extends Money implements Renderable {

	private int type;

	private static final Image DiamondImage = new Image(ClassLoader.getSystemResource("Diamond.png").toString());

	public Diamond(String name, double posX, double posY, int type) {
		super(name, posX, posY);
		
		this.setSize(45, 40);
		this.type = type;
		if (type == 0) {
			this.setVel(0, 40);
		} else if (type == 1) {
			this.setVelY(-220); // TODO Spit Diamond Fall
		}

		this.setValue(200);
	}

	@Override
	public void move(int fr) {
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
		if (type == 1 && this.getPosY() <= GameManager.getTOPHEIGHT() + getHeight() + 30) {
			setType(0);
			setVelY(40);
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(DiamondImage, getPosX(), getPosY(), getWidth(), getHeight());
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
