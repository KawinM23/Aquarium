package properties;

public class Position {
	protected double x;
	protected double y;

	public Position() {
		this.setX(0);
		this.setY(0);
	}

	public Position(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	public Position(double x) {
		this.setX(x);
		this.setY(x);
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

	public double distance(Position anotherPos) {
		double deltaX = Math.abs(anotherPos.getX() - this.getX());
		double deltaY = Math.abs(anotherPos.getY() - this.getY());
		return Math.sqrt(deltaY * deltaY + deltaX * deltaX);
	}

	public void moveTo(Position dt) {
		double plusX = (double) ((dt.getX() - this.getX()) / 50);
		double plusY = (double) ((dt.getY() - this.getY()) / 50);

		this.setX(this.getX() + plusX);
		this.setY(this.getY() + plusY);
	}

	public void move(Position mv) {

		this.setX(this.getX() + mv.getX());
		this.setY(this.getY() + mv.getY());
	}

	public boolean arrived(Position checkPos) {
		if (Math.abs(this.getX() - checkPos.getX()) < 1e-5 || Math.abs(this.getY() - checkPos.getY()) < 1e-5) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

}
