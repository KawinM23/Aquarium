package model;

import properties.Position;

public abstract class VerticalUnit {
	private Position pos;
	
	public VerticalUnit(Position p){
		this.setPos(p);
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	
}
