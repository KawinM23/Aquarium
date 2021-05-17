package manager;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController {
	private static ArrayList<Scene> sceneList = new ArrayList();
	private static ArrayList<String> nameList = new ArrayList();
	private static Stage stage;

	// Declare a primary stage to change scenes in
	public SceneController() {
	}

	public SceneController(Stage stage) {
		this.stage = stage;
	}

	// Add scene in ArrayList to call later (call in names as String)
	public void addScene(String name, Scene scene) {
		sceneList.add(scene);
		nameList.add(name);
	}

	// Remove scene in ArrayList collection
	public void removeScreen(String name) {
		int tempIndex = nameList.indexOf(name);
		sceneList.remove(tempIndex);
		nameList.remove(tempIndex);
	}

	// Change scene of primary stage to another scene
	public static void changeScene(String name) {
		int tempIndex = nameList.indexOf(name);
		Scene scene = sceneList.get(tempIndex);
		stage.setScene(scene);
	}

	public boolean sceneExist(String name) {
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).equals(name))
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
	
	
}