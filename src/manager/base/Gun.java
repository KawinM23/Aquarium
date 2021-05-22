package manager.base;

import javafx.scene.image.Image;

public class Gun {
	private static int level;
	private static int damage;
	private static int price = 1000;

	private static Image gun1Image = new Image(ClassLoader.getSystemResource("gun1.png").toString());
	private static Image gun2Image = new Image(ClassLoader.getSystemResource("gun2.png").toString());
	private static Image gun3Image = new Image(ClassLoader.getSystemResource("gun3.png").toString());
	private static Image gun4Image = new Image(ClassLoader.getSystemResource("gun4.png").toString());
	private static Image gun5Image = new Image(ClassLoader.getSystemResource("gun5.png").toString());
	private static Image gun6Image = new Image(ClassLoader.getSystemResource("gun6.png").toString());
	private static Image gun7Image = new Image(ClassLoader.getSystemResource("gun7.png").toString());
	private static Image gun8Image = new Image(ClassLoader.getSystemResource("gun8.png").toString());
	private static Image gun9Image = new Image(ClassLoader.getSystemResource("gun9.png").toString());
	private static Image gun10Image = new Image(ClassLoader.getSystemResource("gun10.png").toString());

	public static Image getGunImage(int gunNumber) {
		switch (gunNumber) {
		case 1:
			return gun1Image;
		case 2:
			return gun2Image;
		case 3:
			return gun3Image;
		case 4:
			return gun4Image;
		case 5:
			return gun5Image;
		case 6:
			return gun6Image;
		case 7:
			return gun7Image;
		case 8:
			return gun8Image;
		case 9:
			return gun9Image;
		case 10:
			return gun10Image;
		default:
		}
		return null;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Gun.level = level;
	}

	public static int getDamage() {
		// TODO Gun Damage
		switch (level) {
		case 1:
			return 10;
		case 2:
			return 13;
		case 3:
			return 17;
		case 4:
			return 20;
		case 5:
			return 24;
		case 6:
			return 27;
		case 7:
			return 31;
		case 8:
			return 34;
		case 9:
			return 38;
		case 10:
			return 41;
		}
		return 0;
	}

	public static void setDamage(int damage) {
		Gun.damage = damage;
	}

	public static int getPrice() {
		return price;
	}

	public static void setPrice(int price) {
		Gun.price = price;
	}

}
