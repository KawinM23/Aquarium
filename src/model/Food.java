package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import manager.GameManager;
import manager.PlayerController;
import manager.TankManager;
import model.base.Unit;
import properties.Renderable;

public class Food extends Unit implements Renderable {

	private int foodType; // 1 Normal , 2 Potion
	private int foodLevel; // 1,2,3
	private int maxFood; //

	// TODO DifferentPic + LAG WHEN FIRST CALL GETTER FUNCTION !!!BUG!!!
	private static final Image Food1Image = new Image(ClassLoader.getSystemResource("Food1.png").toString());
	private static final Image Food2Image = new Image(ClassLoader.getSystemResource("Food2.png").toString());
	private static final Image Food3Image = new Image(ClassLoader.getSystemResource("Food3.png").toString());
	private static final Image Food4Image = new Image(ClassLoader.getSystemResource("Food3.png").toString());// TODO
																												// Image

	// 1 2 3 : Food Types, 4 : Potion
	public Image getImage(int foodNumber) {
		// TODO Add the rest of images
		if (foodNumber == 1) {
			return Food1Image;
		} else if (foodNumber == 2) {
			return Food2Image;
		} else if (foodNumber == 3) {
			return Food3Image;
		} else if (foodNumber == 4) {
			return Food4Image;
		}
		return null;

	}

	// 1 2 3 : Food Types, 4 : Potion
	public static Image getStaticImage(int foodNumber) {
		// TODO Add the rest of images
		if (foodNumber == 1) {
			return Food1Image;
		} else if (foodNumber == 2) {
			return Food2Image;
		} else if (foodNumber == 3) {
			return Food3Image;
		} else if (foodNumber == 4) {
			return null;
		}
		return null;
	}
	
	public Image getPotionImage() {
		// TODO Add the rest of images
		return Food4Image;
	}

	public Food(String name, double posX, double posY, int foodType) {
		super(name, posX, posY);
		this.setFoodType(foodType);
		if (foodType == 1) {
			this.setWidth(30);
			this.setHeight(30);
			this.setFoodLevel(PlayerController.getFoodLevel());
		} else if (foodType == 2) {
			this.setWidth(40);
			this.setHeight(40);
			this.setFoodLevel(0);
		}
		this.setPos(posX - (getWidth() / 2), posY - (getHeight() / 2)); // Slide to match mouse position

		this.setVel(0, 40);
	}

	@Override
	public void update(int fr) {
		if (this.getPosY() + this.getHeight() >= GameManager.getBOTTOMHEIGHT()) {
			TankManager.remove(this);
			return;
		}
		this.move(fr);
	}

	@Override
	public void render(GraphicsContext gc) {
		if (getFoodType() == 2) {
			gc.drawImage(Food4Image, getPosX(), getPosY(), getWidth(), getHeight());
		} else if (getFoodLevel() == 1) {
			gc.drawImage(Food1Image, getPosX(), getPosY(), getWidth(), getHeight());
		} else if (getFoodLevel() == 2) {
			gc.drawImage(Food2Image, getPosX(), getPosY(), getWidth(), getHeight());
		} else if (getFoodLevel() == 3) {
			gc.drawImage(Food3Image, getPosX(), getPosY(), getWidth(), getHeight());
		}

	}

	public int getFoodType() {
		return foodType;
	}

	public void setFoodType(int foodType) {
		this.foodType = foodType;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	public int getMaxFood() {
		return maxFood;
	}

	public void setMaxFood(int maxFood) {
		this.maxFood = maxFood;
	}

}
