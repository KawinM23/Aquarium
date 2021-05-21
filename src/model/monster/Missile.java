package model.monster;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import manager.PlayerController;
import manager.StatTracker;
import manager.TankManager;
import model.base.Fish;
import model.base.Monster;
import model.base.Unit;
import model.money.Diamond;
import properties.Hunger;
import properties.Idle;
import properties.Renderable;

public class Missile extends Monster implements Renderable {
	
	private static final Image MissileImage = new Image(ClassLoader.getSystemResource("SilverCoin.png").toString());

	private Unit targetFish;

	public Missile(String name, double posX, double posY, Unit targetFish) {
		super(name, posX, posY);
		// TODO Auto-generated constructor stub
		this.setSize(60, 60);
		this.setInner(20, 20);
		this.setSpeed(80);

		this.setHealth(10);
		this.setHunger(new Hunger(0, 0));
		this.setIdle(new Idle(this, 30));

		this.setTargetFish(targetFish);
	}

	@Override
	public void attack() {

		if (this.getInnerHitbox(getInnerX(), getInnerY()).contains(targetFish.getCenterX(), targetFish.getCenterY())) {
			System.out.println(this.getName() + " kill " + targetFish.getName());
			this.destroy(targetFish);
		} else {
			// Go to food
			this.headToUnit(targetFish);
		}

	}

	private void destroy(Unit targetFish) {
		TankManager.remove(targetFish);
		TankManager.remove(this);
	}

	@Override
	public void getHit() {
		// TODO Auto-generated method stub
		this.decreaseHealth(PlayerController.getGunDamage());
	}

	@Override
	public void continuePause(long duration) {
		// TODO Auto-generated method stub
	}
	
	public void defeated() {
		TankManager.remove(this);
	}

	@Override
	public void update(int fr) {
		if (this.getHealth() <= 0) {
			// TODO Defeat
			defeated();
			return;
		}
		this.attack();
		this.move(fr);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(MissileImage, getPosX(), getPosY(),getWidth(),getHeight());
		gc.setStroke(new Color(1, 0, 0, 1));
		gc.strokeOval(targetFish.getCenterX() - 20, targetFish.getCenterY() - 20, 40, 40);
	}

	public Unit getTargetFish() {
		return targetFish;
	}

	public void setTargetFish(Unit targetFish) {
		this.targetFish = targetFish;
	}

}
