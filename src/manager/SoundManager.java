package manager;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundManager {
	private static final String BUTTON_CLICK_PATH = ClassLoader.getSystemResource("buttonclick.mp3").toString();
	private static final Media BUTTON_CLICK_SOUND = new Media(BUTTON_CLICK_PATH);
	private static final String BGM_PATH = ClassLoader.getSystemResource("mainmenu.mp3").toString();
	private static final Media MAIN_MENU_BGM_SOUND = new Media(BGM_PATH);
	private static final MediaPlayer mainMenuBgmPlayer = new MediaPlayer(MAIN_MENU_BGM_SOUND);
	private static final MediaPlayer buttonClickPlayer = new MediaPlayer(BUTTON_CLICK_SOUND);
	
	public static void playClickSound() {
		Thread thread = new Thread(() -> {

			MediaPlayer buttonClickPlayer = new MediaPlayer(BUTTON_CLICK_SOUND);

			try {
				
				buttonClickPlayer.play();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.start();
	}

	public static void playMainMenuBgm() {
		mainMenuBgmPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mainMenuBgmPlayer.seek(Duration.ZERO);
			}
		});
		Thread thread = new Thread(() -> {
			try {
				mainMenuBgmPlayer.play();
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

	public static void stopMainMenuBgm() {
		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mainMenuBgmPlayer.stop();
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
