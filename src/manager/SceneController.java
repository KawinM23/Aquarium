package manager;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import template.Credits;
import template.menu1;
import template.menu2;

public class SceneController {
	private static ArrayList<Scene> sceneList = new ArrayList<Scene>();
	private static ArrayList<String> nameList = new ArrayList<String>();
	private static String currentSceneName;
	private static Stage stage;
	private static String[] startingMenuTexts = { "Start", "Undefined", "Undefined", "Change Player", "Credits",
			"Music: Normal", "Sound: Normal", "Quit" };
	private static Scene startingMenuScene = (new menu1(startingMenuTexts)).getScene();
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

	// Declare a primary stage to change scenes in
	public SceneController() {
	}

	public static void initializeScenes() {
		addScene("MainMenu", startingMenuScene);
		addScene("TankAll", tankLevelScene);
		addScene("Tank1", tankLevel1Scene);
		addScene("Tank2", tankLevel2Scene);
		addScene("Tank3", tankLevel3Scene);
		addScene("Tank4", tankLevel4Scene);
		addScene("Credits", creditsScene);

	}

	// Add scene in ArrayList to call later (call in names as String)
	public static void addScene(String name, Scene scene) {
		sceneList.add(scene);
		nameList.add(name);
	}

	// Remove scene in ArrayList collection
	public static void removeScreen(String name) {
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

}