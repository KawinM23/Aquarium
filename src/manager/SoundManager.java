package manager;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	private static final String BUTTON_CLICK_PATH = ClassLoader.getSystemResource("buttonclick.mp3").toString();
	private static final Media BUTTON_CLICK_SOUND = new Media(BUTTON_CLICK_PATH);
	
	public static void playClickSound() {
		Thread thread = new Thread(() -> {
			try {
				MediaPlayer newMediaPlayer = new MediaPlayer(BUTTON_CLICK_SOUND);
				newMediaPlayer.play();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub

					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.start();
	}
}
