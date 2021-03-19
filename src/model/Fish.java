package model;

import properties.Hunger;
import properties.Position;

public abstract class Fish {
	private String name;
	private Position pos;
	private Hunger hunger;
	private boolean isMoving;
	private Position desti;
	private Position movingVec;

//	public Fish() {
//
//	}

	public Fish(String n, Position p, Hunger h) {
		this.setName(n);
		this.setPos(p);
		this.setHunger(h);
		this.setMovingVec(new Position(0));;
	}

	public void Move() {
		// already move ; continue move
		// Time > 0 random move
		//
		if (isMoving && this.pos.arrived(desti)) {
			return;
		} else if (isMoving) {
			this.pos.move(movingVec);
		} else {
			this.MoveTo(new Position(100, 100));
		}
	}

	public void FindFood(Tank t) {
		try {
			Food nearestFood = t.getFoodList().get(0);
			double nearestDis = this.getPos().distance(nearestFood.getPos());
			for (Food ef : t.getFoodList()) {
				if (this.getPos().distance(ef.getPos()) < nearestDis) {
					nearestFood = ef;
				}
			}
			if (this.getPos().distance(nearestFood.getPos()) < 1e-5) {
				// EAT();
				this.stopMoving();
			} else {
				this.MoveTo(nearestFood.getPos());
			}
		} catch (IndexOutOfBoundsException e) {
			this.setMovingVec(new Position(0));
			this.Move();
		}
	}

	public void CheckHunger(Tank t) {
		this.hunger.setDeltaTime(System.currentTimeMillis()-this.hunger.getStartingTime());
		if (hunger.isHungry()) {
			FindFood(t);
		} else {
			Move();
		}
	}

	public void MoveTo(Position desti) {
		this.setDesti(desti);
		this.setMovingVec(desti);
		this.pos.move(movingVec);
		this.setMoving(true);
	}

	public void stopMoving() {
		this.setMovingVec(new Position(0));
	}

	public String toString() {
		return this.name + " " + this.pos + " "+ this.hunger;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public Hunger getHunger() {
		return hunger;
	}

	public void setHunger(Hunger hunger) {
		this.hunger = hunger;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public Position getDesti() {
		return desti;
	}

	public void setDesti(Position desti) {
		this.desti = desti;
	}

	public Position getMovingVec() {
		return movingVec;
	}

	public void setMovingVec(Position mv) {
		this.movingVec = mv;
	}

	public void setMovingVecBy50(Position mv) {
		this.movingVec.setX(mv.getX() / 50);
		this.movingVec.setY(mv.getY() / 50);
	}

}
