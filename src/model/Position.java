package model;

public class Position {
	protected double x;
	protected double y;
	
	public Position(){
		this.setX(0);
		this.setY(0);
	}
	
	public Position(double x, double y) {
		super();
		this.setX(x);
		this.setY(y);
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	
}
