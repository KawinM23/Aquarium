package manager.base;

import javafx.scene.image.Image;

public class Trophy {
	private static Image Trophy1Image = new Image(ClassLoader.getSystemResource("Trophy1.png").toString());
	private static Image Trophy2Image = new Image(ClassLoader.getSystemResource("Trophy2.png").toString());
	private static Image Trophy3Image = new Image(ClassLoader.getSystemResource("Trophy3.png").toString());

	public static Image getGoalImage(int goalLevel) {
		switch (goalLevel) {
		case 1:
			return Trophy1Image;
		case 2:
			return Trophy2Image;
		case 3:
			return Trophy3Image;
		default:
		}
		return null;
	}

}
