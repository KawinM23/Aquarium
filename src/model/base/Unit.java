package model.base;

import javafx.geometry.Rectangle2D;
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

	public Unit(String name, double posX, double posY) {
		super();
		this.name = name;
		this.posX = posX;
		this.posY = posY;
	}

	public abstract void update(int fr);

	public abstract void draw(GraphicsContext gc);

	public double distance(Unit u) {
		double deltaX = u.getPosX() - this.getPosX();
		double deltaY = u.getPosY() - this.getPosY();
		return Math.hypot(deltaX, deltaY);
	}
	
	public double distance(double x,double y) {
		double deltaX = x - this.getPosX();
		double deltaY = y - this.getPosY();
		return Math.hypot(deltaX, deltaY);
	}

	// HeadToUnit = set velocity
	public void headToUnit(Unit u) {
		this.setVelX(((u.getPosX() - this.getPosX()) / this.distance(u))*this.getSpeed());
		this.setVelY(((u.getPosY() - this.getPosY()) / this.distance(u))*this.getSpeed());
	}

	// HeadToPoint
	public void headToPos(double x,double y) {
		this.setVelX((x - this.getPosX() / this.distance(x,y)*this.getSpeed()));
		this.setVelY((y - this.getPosY() / this.distance(x,y)*this.getSpeed()));
	}
	
	public void move(int fr) {
		
		double deltaTime = 1.0/fr;
		this.setPosX(this.getPosX() + this.getVelX()*deltaTime);
		this.setPosY(this.getPosY() + this.getVelY()*deltaTime);
	}
	
	
	public Rectangle2D getBoundary() {
        return new Rectangle2D(posX, posY, width, height);
    }
	
	public boolean intersects(Unit u) {
        return this.getBoundary().intersects(u.getBoundary());
    }
	
	
	//GETTER SETTER
	@SuppressWarnings("unused")
	private int _____GETTER_SETTER_____;
	
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

	public double getCenterX() {
		// TODO Auto-generated method stub
		return posX+(width/2.0);
	}

	public double getCenterY() {
		// TODO Auto-generated method stub
		return posY+(height/2.0);
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
	
	public void setVel(double velX,double velY){
		this.velX = velX;
		this.velY = velY;
	}
	
	public void setVelZero() {
		this.velX = 0;
		this.velY = 0;
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
