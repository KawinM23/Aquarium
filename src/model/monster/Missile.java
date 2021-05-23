package model.monster;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import manager.PlayerController;
import manager.SoundManager;
import manager.TankManager;
import model.base.Monster;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Renderable;

public class Missile extends Monster implements Renderable {

	private static final Image MissileImage = new Image(ClassLoader.getSystemResource("Missile.png").toString());
	private static final Image MissileTargetImage = new Image(
			ClassLoader.getSystemResource("MissileTarget.png").toString());

	private Unit targetFish;

	public Missile(String name, double posX, double posY, int health, Unit targetFish) {
		super(name, posX, posY);
		this.setSize(60, 60);
		this.setSpeed(80);

		this.setMaxHealth(10);
		this.setMaxHealth(health);
		this.setHealth(getMaxHealth());

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
		this.decreaseHealth(PlayerController.getGunDamage());
	}

	@Override
	public void continuePause(long duration) {
	}

	public void defeated() {
		System.out.println(getName() + " destroyed");
		SoundManager.playAlienDieSound();
		TankManager.remove(this);
	}

	@Override
	public void update(int fr) {
		if (this.getHealth() <= 0) {
			// Defeat
			defeated();
			return;
		}
		this.attack();
		this.move(fr);
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(MissileImage, getPosX(), getPosY(), getWidth(), getHeight());
		gc.drawImage(MissileTargetImage, targetFish.getCenterX() - 50, targetFish.getCenterY() - 50, 100, 100);
	}

	public Unit getTargetFish() {
		return targetFish;
	}

	public void setTargetFish(Unit targetFish) {
		this.targetFish = targetFish;
	}

}
