package model.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Unit {
	private String name;
	private double posX;
	private double posY;

	private Image img;

	public abstract void update(long t);

	public abstract void draw(GraphicsContext gc);

	public double distance(Unit u) {
		double deltaX = u.getPosX() - this.getPosX();
		double deltaY = u.getPosY() - this.getPosY();
		double dis = Math.hypot(deltaX, deltaY);
		return dis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

}
