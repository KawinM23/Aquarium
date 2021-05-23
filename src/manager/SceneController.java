package manager;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import template.Credits;
import template.WinLose;
import template.Menu1;
import template.Menu2;
import template.Stats;

public class SceneController {
	private static ArrayList<Scene> sceneList = new ArrayList<Scene>();
	private static ArrayList<String> nameList = new ArrayList<String>();
	private static String currentSceneName;
	private static String lastPlayedTank;
	private static Stage stage;
	private static String[] startingMenuTexts = { "Continue", "Select Level", "Statistics", "Change Player", "Credits",
			"Music: Normal", "Sound: Normal", "Quit" };
	private static String[] tankLevelTexts = { "Tank 1", "Tank 2", "Tank 3", "Tank 4", "Back" };
	private static Scene tankLevelScene = (new Menu2(tankLevelTexts)).getScene();
	private static String[] tankLevel1Texts = { "Tank 1-1", "Tank 1-2", "Tank 1-3", "Tank 1-4", "Back" };
	private static String[] tankLevel2Texts = { "Tank 2-1", "Tank 2-2", "Tank 2-3", "Tank 2-4", "Back" };
	private static String[] tankLevel3Texts = { "Tank 3-1", "Tank 3-2", "Tank 3-3", "Tank 3-4", "Back" };
	private static String[] tankLevel4Texts = { "Tank 4-1", "Tank 4-2", "Tank 4-3", "Tank 4-4", "Back" };
	private static Scene tankLevel1Scene = (new Menu2(tankLevel1Texts)).getScene();
	private static Scene tankLevel2Scene = (new Menu2(tankLevel2Texts)).getScene();
	private static Scene tankLevel3Scene = (new Menu2(tankLevel3Texts)).getScene();
	private static Scene tankLevel4Scene = (new Menu2(tankLevel4Texts)).getScene();
	private static String[] creditsTexts = { "Back" };
	private static Scene creditsScene = (new Credits(creditsTexts)).getScene();
	private static String[] statsTexts = { "Back" };
	private static Scene statsScene = (new Stats(statsTexts)).getScene();
	private static Scene winScene = (new WinLose(1)).getScene();
	private static Scene loseScene = (new WinLose(0)).getScene();

	private static GameManager manager = new GameManager();

	public static void initializeScenes() {
		Scene startingMenuScene = (new Menu1(startingMenuTexts)).getScene();
		addScene("MainMenu", startingMenuScene);
		addScene("TankAll", tankLevelScene);
		addScene("Tank1", tankLevel1Scene);
		addScene("Tank2", tankLevel2Scene);
		addScene("Tank3", tankLevel3Scene);
		addScene("Tank4", tankLevel4Scene);
		addScene("Credits", creditsScene);
		addScene("Stats", statsScene);
		addScene("Win", winScene);
		addScene("Lose", loseScene);
		ButtonManager.updateTankLevelButtons();

	}

	// Add scene in ArrayList to call later (call in names as String)
	public static void addScene(String name, Scene scene) {
		sceneList.add(scene);
		nameList.add(name);
	}

	// Remove scene in ArrayList collection
	public static void removeScene(String name) {
		if (sceneExist(name)) {
			int tempIndex = nameList.indexOf(name);
			sceneList.remove(tempIndex);
			nameList.remove(tempIndex);
		}
	}

	// Change scene of primary stage to another scene
	public static void changeScene(String name) {
		ButtonManager.updateTankLevelButtons();
		updatePlayerSettings();
		refreshScene(name);
		int tempIndex = nameList.indexOf(name);
		Scene scene = sceneList.get(tempIndex);
		stage.setScene(scene);
		setCurrentSceneName(name);
	}

	public static void changeScene(Scene scene) {
		stage.setScene(scene);
		setCurrentSceneName("Playing");
	}

	public static boolean sceneExist(String name) {
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).equals(name))
				return true;
		}
		return false;
	}

	public static boolean isAtNumberTank() {
		if (getCurrentSceneName().equals("Tank1") || getCurrentSceneName().equals("Tank2")
				|| getCurrentSceneName().equals("Tank3") || getCurrentSceneName().equals("Tank4")) {
			return true;
		}
		return false;
	}

	public static boolean isAtAllTank() {
		if (getCurrentSceneName().equals("TankAll")) {
			return true;
		}
		return false;
	}

	public static void startTankLevel(int tankNumber, int levelNumber) {
		SoundManager.changeBgmTo(tankNumber);
		Thread thread = new Thread(() -> {
			try {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						// TODO Change Bgm later

						manager.startLevel(LevelManager.getLevel(tankNumber, levelNumber));
						SceneController.changeScene(manager.getTankScene());
						setLastPlayedTank(tankNumber, levelNumber);
					}
				});

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.start();
	}

	public static void startLatestLevel() {
		SoundManager.changeBgmTo(LevelManager.getLatestLevel().getId()[0]);
		Thread thread = new Thread(() -> {
			try {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						// TODO Change Bgm later

						manager.startLevel(LevelManager.getLatestLevel());
						SceneController.changeScene(manager.getTankScene());
						setLastPlayedTank(LevelManager.getLatestLevel().getId()[0],
								LevelManager.getLatestLevel().getId()[1]);

					}
				});

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.start();
	}

	public static void changeToTank(int tankNumber) {
		Thread thread = new Thread(() -> {
			try {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						String tankCode = "Tank" + tankNumber;
						// TODO Auto-generated method stub
						SceneController.changeScene(tankCode);
					}
				});

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.start();
	}

	public static void updatePlayerSettings() {
		if (JSONManager.getTank() == 1 && JSONManager.getLevel() == 1) {
			startingMenuTexts[0] = "Start";
		} else {
			startingMenuTexts[0] = "Continue";
		}
		startingMenuTexts[5] = "Music: " + SoundManager.getVolumeLevelWord(SoundManager.getBgmVolumeLevel());
		startingMenuTexts[6] = "Sound: " + SoundManager.getVolumeLevelWord(SoundManager.getSoundVolumeLevel());
		SoundManager.setVolume(SoundManager.getCurrentBgmPlayer(), SoundManager.getBgmVolumeLevel());
		refreshScene("MainMenu");
	}

	public static void refreshScene(String sceneName) {
		if (sceneName.equals("MainMenu")) {
			removeScene("MainMenu");
			Scene startingMenuScene = (new Menu1(startingMenuTexts)).getScene();
			addScene("MainMenu", startingMenuScene);
		} else if (sceneName.equals("TankAll")) {
			removeScene("TankAll");
			Scene tankLevelScene = (new Menu2(tankLevelTexts)).getScene();
			addScene("TankAll", tankLevelScene);
		} else if (sceneName.equals("Tank1")) {
			removeScene("Tank1");
			Scene tankLevel1Scene = (new Menu2(tankLevel1Texts)).getScene();
			addScene("Tank1", tankLevel1Scene);
		} else if (sceneName.equals("Tank2")) {
			removeScene("Tank2");
			Scene tankLevel2Scene = (new Menu2(tankLevel2Texts)).getScene();
			addScene("Tank2", tankLevel2Scene);
		} else if (sceneName.equals("Tank3")) {
			removeScene("Tank3");
			Scene tankLevel3Scene = (new Menu2(tankLevel3Texts)).getScene();
			addScene("Tank3", tankLevel3Scene);
		} else if (sceneName.equals("Tank4")) {
			removeScene("Tank4");
			Scene tankLevel4Scene = (new Menu2(tankLevel4Texts)).getScene();
			addScene("Tank4", tankLevel4Scene);
		} else if (sceneName.equals("Credits")) {
			removeScene("Credits");
			Scene creditsScene = (new Credits(creditsTexts)).getScene();
			addScene("Credits", creditsScene);
		} else if (sceneName.equals("Statistics")) {
			removeScene("Statistics");
			Scene statsScene = (new Stats(statsTexts)).getScene();
			addScene("Statistics", statsScene);
		}

	}

	public static void refreshAllScenes() {
		Scene startingMenuScene = (new Menu1(startingMenuTexts)).getScene();
		Scene tankLevelScene = (new Menu2(tankLevelTexts)).getScene();
		Scene tankLevel1Scene = (new Menu2(tankLevel1Texts)).getScene();
		Scene tankLevel2Scene = (new Menu2(tankLevel2Texts)).getScene();
		Scene tankLevel3Scene = (new Menu2(tankLevel3Texts)).getScene();
		Scene tankLevel4Scene = (new Menu2(tankLevel4Texts)).getScene();
		Scene creditsScene = (new Credits(creditsTexts)).getScene();
		Scene statsScene = (new Stats(statsTexts)).getScene();

		removeAllScenes();

		addScene("MainMenu", startingMenuScene);
		addScene("TankAll", tankLevelScene);
		addScene("Tank1", tankLevel1Scene);
		addScene("Tank2", tankLevel2Scene);
		addScene("Tank3", tankLevel3Scene);
		addScene("Tank4", tankLevel4Scene);
		addScene("Credits", creditsScene);
		addScene("Stats", statsScene);

	}

	public static void removeAllScenes() {
		sceneList.clear();
		nameList.clear();
	}

	public static ArrayList<Scene> getSceneList() {
		return sceneList;
	}

	public static void setSceneList(ArrayList<Scene> sceneList) {
		SceneController.sceneList = sceneList;
	}

	public static ArrayList<String> getNameList() {
		return nameList;
	}

	public static void setNameList(ArrayList<String> nameList) {
		SceneController.nameList = nameList;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		SceneController.stage = stage;
	}

	public static String getCurrentSceneName() {
		return currentSceneName;
	}

	public static void setCurrentSceneName(String name) {
		currentSceneName = name;
	}

	public static GameManager getManager() {
		return manager;
	}

	public static void setManager(GameManager manager) {
		SceneController.manager = manager;
	}

	public static void setLastPlayedTank(int tankNumber, int levelNumber) {
		lastPlayedTank = "" + tankNumber + levelNumber;
	}
	
	public static String getLastPlayedTank() {
		return lastPlayedTank;
	}

}