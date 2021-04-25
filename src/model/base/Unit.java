package model.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Unit {
	private String name;
	private double posX;
	private double posY;
	private double width;
	private double height;
	private double velX;	
	private double velY;
	private int speed;

	private Image img;
	
	

	public Unit(String name, double posX, double posY, double width, double height, int speed) {
		super();
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}

	public abstract void update(long t);

	public abstract void draw(GraphicsContext gc);

	public double distance(Unit u) {
		double deltaX = u.getPosX() - this.getPosX();
		double deltaY = u.getPosY() - this.getPosY();
		return Math.hypot(deltaX, deltaY);
	}

	//HeadToUnit = set velocity
	
	//HeadToPoint
	
	//GETTER SETTER
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
	
	public void setPos(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	



}
