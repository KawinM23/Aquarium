package model.fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.InvasionManager;
import manager.TankManager;
import model.base.Fish;
import model.base.Unit;
import properties.Hunger;
import properties.Idle;
import properties.Production;
import properties.Renderable;

public class Guppycruncher extends Fish implements Renderable{

	private static final Image GuppycruncherImage = new Image(ClassLoader.getSystemResource("Guppy.png").toString());

	public Image getImage() {
		return GuppycruncherImage;
	}
	
	private boolean isJumping;
	private final double fallAcc = 120;
	private final double velYJump = -150;
	private final double reachHeight = 160;
	private final double reachDistance = 80;

	public Guppycruncher(String name, double posX, double posY) {
		super(name, posX, posY);

		this.setSize(100, 100);
		this.setMouthPos(20, 10);
		
		this.setSpeed(40);
		this.setVelZero();

		this.setJumping(false);

		this.setHunger(new Hunger(Guppy.class, 6, 30));
		this.setProduction(new Production(this, 6, 5));
		this.setIdle(new Idle(this, 20));
		this.setPrice(750);
	}

	public void findFood() {
		if (TankManager.getFishList().size() != 0) {
			Unit nearestFood = null;
			// Find NearestFood
			for (Fish f : TankManager.getFishList()) {
				if (f instanceof Guppy) {
					if (((Guppy) f).getGrowth() < 100) {
						if (nearestFood == null) {
							nearestFood = f;
						} else if (this.distance(f) < this.distance(nearestFood)) {
							nearestFood = f;
						}
					}
				}
			}

			// Check Food position and Fish
			if (nearestFood != null) {
				if (this.intersects(nearestFood)) {
					TankManager.remove(nearestFood);
					this.feed(nearestFood);
					return;
				}
				if (Math.abs(distanceX(nearestFood)) > 20) {
					headToUnitX(nearestFood);
				} else {
					setVelX(getVelX() / Math.abs(getVelX()) * getSpeed() * 0.8);
				}
				if (!isJumping && Math.abs(distanceY(nearestFood)) < reachHeight
						&& Math.abs(distanceX(nearestFood)) < reachDistance) {
					jump();
				}
			} else {
				if (!isJumping) {
					getIdle().checkIdleX();
				}
			}
		} else {
			if (!isJumping) {
				getIdle().checkIdleX();
			}
		}
	}

	private void jump() {
		// jump
		System.out.println("Jump");
		setVelY(velYJump);
		setJumping(true);
	}

	@Override
	public void update(int fr) {
		if (!InvasionManager.isInvaded()) {
			switch (getHunger().checkHunger()) {
			case 0:
				// idle
				if (!isJumping) {
					this.getIdle().checkIdleX();
				}
				break;
			case 1:
				// find food
				this.findFood();
				break;
			case 2:
				// Very Hungry TODO ChangePic
				this.findFood();
				break;
			case 3:
				// die
				this.die();
				return;
			}

			this.getProduction().checkProduce();
		} else {
			getIdle().checkIdleX();
		}
		this.move(fr);
		this.checkFacingLeft();
	}

	public void move(int fr) {
		//Jumping GC
		double deltaTime = 1.0 / fr;
		this.setPosX(this.getPosX() + this.getVelX() * deltaTime);
		if(getPosY() < GameManager.getBOTTOMHEIGHT() - getHeight()) {
			setJumping(true);
		}
		if (isJumping) {
			this.setPosY(this.getPosY() + this.getVelY() * deltaTime);
			this.setVelY(getVelY() + (fallAcc * deltaTime));
			if (getPosY() >= GameManager.getBOTTOMHEIGHT() - getHeight()) {
				this.getIdle().randomVel();
				this.getIdle().setNextIdleRandom(1, 2);
				this.setVelX(getVelX() * 0.8);
				this.getIdle().setVelX(getVelX());
				this.setVelY(0);
				this.setJumping(false);
			}
		}
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isFacingLeft()) {
			gc.drawImage(GuppycruncherImage, getPosX(), getPosY(), getWidth(), getHeight());
		} else {
			gc.drawImage(GuppycruncherImage, getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

}
