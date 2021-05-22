package manager;

import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundManager {
	// Main Menu BGM & Click Sound
	private static final Media BUTTON_CLICK_SOUND = new Media(
			ClassLoader.getSystemResource("buttonclick.mp3").toString());
	private static final MediaPlayer buttonClickPlayer = new MediaPlayer(BUTTON_CLICK_SOUND);

	private static final MediaPlayer mainMenuBgmPlayer = new MediaPlayer(
			new Media(ClassLoader.getSystemResource("mainmenu.mp3").toString()));

	// Tank BGM
	// TODO CHANGE MUSIC TO CORRECT PATHS
	private static final MediaPlayer tank1BgmPlayer = new MediaPlayer(
			new Media(ClassLoader.getSystemResource("mainmenu.mp3").toString()));

	private static final MediaPlayer tank2BgmPlayer = new MediaPlayer(
			new Media(ClassLoader.getSystemResource("Tank2.mp3").toString()));

	private static final MediaPlayer tank3BgmPlayer = new MediaPlayer(
			new Media(ClassLoader.getSystemResource("Tank3.mp3").toString()));

	private static final MediaPlayer tank4BgmPlayer = new MediaPlayer(
			new Media(ClassLoader.getSystemResource("Tank4.mp3").toString()));

	private static final MediaPlayer alienBgmPlayer = new MediaPlayer(
			new Media(ClassLoader.getSystemResource("AlienBgm.mp3").toString()));

	// Sound Effects
	private static final Media DIE_SOUND = new Media(ClassLoader.getSystemResource("DIE.mp3").toString());

	private static final Media DROP_FOOD_SOUND = new Media(ClassLoader.getSystemResource("DROPFOOD.mp3").toString());

	private static final Media POINT_SOUND = new Media(ClassLoader.getSystemResource("POINTS.mp3").toString());

	private static final Media POINT2_SOUND = new Media(ClassLoader.getSystemResource("POINTS2.mp3").toString());

	private static final Media POINT3_SOUND = new Media(ClassLoader.getSystemResource("POINTS3.mp3").toString());

	private static final Media POINT4_SOUND = new Media(ClassLoader.getSystemResource("POINTS4.mp3").toString());

	private static final Media SLURP_SOUND = new Media(ClassLoader.getSystemResource("SLURP.mp3").toString());

	private static final Media SLURP2_SOUND = new Media(ClassLoader.getSystemResource("SLURP2.mp3").toString());

	private static final Media SLURP3_SOUND = new Media(ClassLoader.getSystemResource("SLURP3.mp3").toString());

	private static final Media SPLASH_SOUND = new Media(ClassLoader.getSystemResource("SPLASH.mp3").toString());

	private static final Media SPLASH2_SOUND = new Media(ClassLoader.getSystemResource("SPLASH2.mp3").toString());

	private static final Media SPLASH3_SOUND = new Media(ClassLoader.getSystemResource("SPLASH3.mp3").toString());

	private static final Media EAT_FISH1_SOUND = new Media(ClassLoader.getSystemResource("chomp.mp3").toString());

	private static final Media EAT_FISH2_SOUND = new Media(ClassLoader.getSystemResource("chomp2.mp3").toString());

	private static final Media WARNING_SOUND = new Media(ClassLoader.getSystemResource("AWOOGA.mp3").toString());

	private static final Media ERROR_SOUND = new Media(ClassLoader.getSystemResource("BUZZER.mp3").toString());

	private static final Media ALIEN_SCREAM_SOUND = new Media(ClassLoader.getSystemResource("bigchomp.mp3").toString());

	private static final Media ALIEN_DIE_SOUND = new Media(ClassLoader.getSystemResource("explode.mp3").toString());

	private static final Media HIT_SHIELD_SOUND = new Media(ClassLoader.getSystemResource("ShieldHit.mp3").toString());

	private static final Media HIT_BODY_SOUND = new Media(ClassLoader.getSystemResource("BodyHit.mp3").toString());

	private static final Media WIN_SOUND = new Media(ClassLoader.getSystemResource("win.mp3").toString());

	private static final Media LOSE_SOUND = new Media(ClassLoader.getSystemResource("lose.mp3").toString());

	private static final Media SHOOT_DIAMOND_SOUND = new Media(ClassLoader.getSystemResource("ShootDiamond.mp3").toString());
	
	// 0 Mute, 1 Quiet, 2 Normal, 3 Loud
	private static int bgmVolumeLevel = 2;
	private static int soundVolumeLevel = 2;
	private static MediaPlayer currentBgmPlayer;

	public static void playSoundEffect(Media media) {
		Thread thread = new Thread(() -> {

			MediaPlayer player = new MediaPlayer(media);

			try {
				setVolume(player, soundVolumeLevel);
				player.play();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.start();
	}

	public static void setBgm(int tankNumber) {
		switch (tankNumber) {
		case 0:
			currentBgmPlayer = mainMenuBgmPlayer;
			break;
		case 1:
			currentBgmPlayer = tank1BgmPlayer;
			break;
		case 2:
			currentBgmPlayer = tank2BgmPlayer;
			break;
		case 3:
			currentBgmPlayer = tank3BgmPlayer;
			break;
		case 4:
			currentBgmPlayer = tank4BgmPlayer;
			break;
		case 5:
			currentBgmPlayer = alienBgmPlayer;
		default:
		}

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

	public static void stopBgm() {
		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("STOP");
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

	public static void changeBgmTo(int tankNumber) {
		SoundManager.stopBgm();
		Thread thread = new Thread(() -> {
			try {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						// TODO Change Bgm later
						SoundManager.setBgm(tankNumber);
						SoundManager.playBgm();
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
		setVolume(currentBgmPlayer, bgmVolumeLevel);
		JSONManager.writeJSON();
	}

	public static void nextSoundVolumeLevel() {
		if (soundVolumeLevel == 3)
			soundVolumeLevel = 0;
		else if (soundVolumeLevel < 3)
			soundVolumeLevel++;
		JSONManager.writeJSON();
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

	public static void playClickSound() {
		playSoundEffect(BUTTON_CLICK_SOUND);
	}

	public static void playDieSound() {
		playSoundEffect(DIE_SOUND);
	}

	public static void playDropFoodSound() {
		playSoundEffect(DROP_FOOD_SOUND);
	}

	public static void playCollectSound() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		switch (randomNum) {
		case 1:
			playSoundEffect(POINT_SOUND);
			break;
		case 2:
			playSoundEffect(POINT2_SOUND);
			break;
		case 3:
			playSoundEffect(POINT3_SOUND);
			break;
		case 4:
			playSoundEffect(POINT4_SOUND);
			break;
		default:
		}
	}

	public static void playEatSound() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
		switch (randomNum) {
		case 1:
			playSoundEffect(SLURP_SOUND);
			break;
		case 2:
			playSoundEffect(SLURP2_SOUND);
			break;
		case 3:
			playSoundEffect(SLURP3_SOUND);
			break;
		default:
		}
	}

	public static void playEatFishSound() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		switch (randomNum) {
		case 1:
			playSoundEffect(EAT_FISH1_SOUND);
			break;
		case 2:
			playSoundEffect(EAT_FISH2_SOUND);
			break;
		default:
		}
	}
	
	public static void playShootDiamondSound() {
		playSoundEffect(SHOOT_DIAMOND_SOUND);
	}

	public static void playWarningSound() {
		Thread thread = new Thread(() -> {

			try {
				for (int i = 0; i < 3; i++) {
					if (PlayerController.isPlaying()) {
						MediaPlayer player = new MediaPlayer(WARNING_SOUND);
						setVolume(player, soundVolumeLevel);
						player.play();
						Thread.sleep(2000);
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.start();

	}

	public static void playErrorSound() {
		playSoundEffect(ERROR_SOUND);
	}

	public static void playAlienScreamSound() {
		playSoundEffect(ALIEN_SCREAM_SOUND);
	}

	public static void playAlienDieSound() {
		playSoundEffect(ALIEN_DIE_SOUND);
	}

	public static void playShieldHitSound() {
		playSoundEffect(HIT_SHIELD_SOUND);
	}

	public static void playBodyHitSound() {
		playSoundEffect(HIT_BODY_SOUND);
	}

	public static void playWinSound() {
		SoundManager.playSoundEffect(WIN_SOUND);
	}

	public static void playLoseSound() {
		SoundManager.playSoundEffect(LOSE_SOUND);
	}

	public static void playSplashSound() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
		switch (randomNum) {
		case 1:
			playSoundEffect(SPLASH_SOUND);
			break;
		case 2:
			playSoundEffect(SPLASH2_SOUND);
			break;
		case 3:
			playSoundEffect(SPLASH3_SOUND);
			break;
		default:
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

	public static int getSoundVolumeLevel() {
		return soundVolumeLevel;
	}

	public static void setSoundVolumeLevel(int soundVolumeLevel) {
		SoundManager.soundVolumeLevel = soundVolumeLevel;
	}

	public static MediaPlayer getCurrentBgmPlayer() {
		return currentBgmPlayer;
	}

}
