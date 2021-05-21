package manager;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import template.Credits;
import template.WinLose;
import template.menu1;
import template.menu2;
import template.stats;

public class SceneController {
	private static ArrayList<Scene> sceneList = new ArrayList<Scene>();
	private static ArrayList<String> nameList = new ArrayList<String>();
	private static String currentSceneName;
	private static Stage stage;
	private static String[] startingMenuTexts = { "Continue", "Select Level", "Statistics", "Change Player", "Credits",
			"Music: Normal", "Sound: Normal", "Quit" };
	private static String[] tankLevelTexts = { "Tank 1", "Tank 2", "Tank 3", "Tank 4", "Back" };
	private static Scene tankLevelScene = (new menu2(tankLevelTexts)).getScene();
	private static String[] tankLevel1Texts = { "Tank 1-1", "Tank 1-2", "Tank 1-3", "Tank 1-4", "Back" };
	private static String[] tankLevel2Texts = { "Tank 2-1", "Tank 2-2", "Tank 2-3", "Tank 2-4", "Back" };
	private static String[] tankLevel3Texts = { "Tank 3-1", "Tank 3-2", "Tank 3-3", "Tank 3-4", "Back" };
	private static String[] tankLevel4Texts = { "Tank 4-1", "Tank 4-2", "Tank 4-3", "Tank 4-4", "Back" };
	private static Scene tankLevel1Scene = (new menu2(tankLevel1Texts)).getScene();
	private static Scene tankLevel2Scene = (new menu2(tankLevel2Texts)).getScene();
	private static Scene tankLevel3Scene = (new menu2(tankLevel3Texts)).getScene();
	private static Scene tankLevel4Scene = (new menu2(tankLevel4Texts)).getScene();
	private static String[] creditsTexts = { "Back" };
	private static Scene creditsScene = (new Credits(creditsTexts)).getScene();
	private static String[] statsTexts = { "Back" };
	private static Scene statsScene = (new stats(statsTexts)).getScene();
	private static Scene winScene = (new WinLose(1)).getScene();
	private static Scene loseScene = (new WinLose(0)).getScene();

	private static ViewManager manager = new ViewManager();

	// Declare a primary stage to change scenes in
	public SceneController() {
	}

	public static void initializeScenes() {
		Scene startingMenuScene = (new menu1(startingMenuTexts)).getScene();
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

	}

	// Add scene in ArrayList to call later (call in names as String)
	public static void addScene(String name, Scene scene) {
		sceneList.add(scene);
		nameList.add(name);
	}

	// Remove scene in ArrayList collection
	public static void removeScene(String name) {
		int tempIndex = nameList.indexOf(name);
		sceneList.remove(tempIndex);
		nameList.remove(tempIndex);
	}

	// Change scene of primary stage to another scene
	public static void changeScene(String name) {
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
						String tankCode = "Tank"+tankNumber;
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
		if (sceneExist("MainMenu")) {
			removeScene("MainMenu");
			Scene startingMenuScene = (new menu1(startingMenuTexts)).getScene();
			addScene("MainMenu", startingMenuScene);
		}

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

	public static ViewManager getManager() {
		return manager;
	}

	public static void setManager(ViewManager manager) {
		SceneController.manager = manager;
	}

}