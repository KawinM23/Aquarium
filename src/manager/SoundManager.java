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
	// 0 Mute, 1 Quiet, 2 Normal, 3 Loud
	private static int clickVolumeLevel = 2;
	private static int bgmVolumeLevel = 2;
	private static MediaPlayer currentBgmPlayer;

	public static void playClickSound() {
		setVolume(buttonClickPlayer, clickVolumeLevel);
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

	public static void playBgm() {
		setVolume(currentBgmPlayer, bgmVolumeLevel);
		currentBgmPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				currentBgmPlayer.seek(Duration.ZERO);
			}
		});
		Thread thread = new Thread(() -> {
			try {
				currentBgmPlayer.play();
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

	public static void playMainMenuBgm() {
		setVolume(mainMenuBgmPlayer, bgmVolumeLevel);
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

	public static void stopBgm() {
		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						currentBgmPlayer.stop();
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

	public static void setVolume(MediaPlayer mediaplayer, int volumeLevel) {
		double volume;
		switch (volumeLevel) {
		case 0:
			volume = 0;
			break;
		case 1:
			volume = 0.25;
			break;
		case 2:
			volume = 0.50;
			break;
		case 3:
			volume = 0.75;
			break;
		default:
			volume = 0.50;
		}
		mediaplayer.setVolume(volume);
	}

	public static void nextBgmVolumeLevel() {
		if (bgmVolumeLevel == 3)
			bgmVolumeLevel = 0;
		else if (bgmVolumeLevel < 3)
			bgmVolumeLevel++;
		setVolume(currentBgmPlayer,bgmVolumeLevel);
	}

	public static void setBgm(MediaPlayer mediaPlayer) {
		currentBgmPlayer = mediaPlayer;
	}

	public static String getVolumeLevelWord(int currentVolumeLevel) {
		switch (currentVolumeLevel) {
		case 0:
			return "Mute";
		case 1:
			return "Quiet";
		case 2:
			return "Normal";
		case 3:
			return "Loud";
		default:
			return "";
		}
	}

	public static MediaPlayer getMainmenubgmplayer() {
		return mainMenuBgmPlayer;
	}

	public static int getBgmVolumeLevel() {
		return bgmVolumeLevel;
	}

	public static void setBgmVolumeLevel(int level) {
		bgmVolumeLevel = level;
	}

}
