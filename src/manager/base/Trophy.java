package manager.base;

import javafx.scene.image.Image;

public class Trophy {
	private static Image bronzeTrophyImage = new Image(ClassLoader.getSystemResource("bronzetrophy.jpg").toString());
	private static Image silverTrophyImage = new Image(ClassLoader.getSystemResource("silvertrophy.jpg").toString());
	private static Image goldTrophyImage = new Image(ClassLoader.getSystemResource("goldtrophy.jpg").toString());

	public static Image getGoalImage(int goalLevel) {
		switch (goalLevel) {
		case 1:
			return bronzeTrophyImage;
		case 2:
			return silverTrophyImage;
		case 3:
			return goldTrophyImage;
		default:
		}
		return null;
	}

}
