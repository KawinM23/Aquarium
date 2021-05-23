package manager.base;

import javafx.scene.image.Image;

public class Gun {

	private static Image Gun2Image = new Image(ClassLoader.getSystemResource("FishGun2.png").toString());
	private static Image Gun3Image = new Image(ClassLoader.getSystemResource("FishGun3.png").toString());
	private static Image Gun4Image = new Image(ClassLoader.getSystemResource("FishGun4.png").toString());
	private static Image Gun5Image = new Image(ClassLoader.getSystemResource("FishGun5.png").toString());
	private static Image Gun6Image = new Image(ClassLoader.getSystemResource("FishGun6.png").toString());
	private static Image Gun7Image = new Image(ClassLoader.getSystemResource("FishGun7.png").toString());
	private static Image Gun8Image = new Image(ClassLoader.getSystemResource("FishGun8.png").toString());
	private static Image Gun9Image = new Image(ClassLoader.getSystemResource("FishGun9.png").toString());
	private static Image Gun10Image = new Image(ClassLoader.getSystemResource("FishGun10.png").toString());

	public static Image getGunImage(int gunNumber) {
		switch (gunNumber) {
		case 2:
			return Gun2Image;
		case 3:
			return Gun3Image;
		case 4:
			return Gun4Image;
		case 5:
			return Gun5Image;
		case 6:
			return Gun6Image;
		case 7:
			return Gun7Image;
		case 8:
			return Gun8Image;
		case 9:
			return Gun9Image;
		case 10:
			return Gun10Image;
		default:
		}
		return null;
	}

}
