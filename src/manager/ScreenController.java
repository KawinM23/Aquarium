package manager;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScreenController {
	private ArrayList<Scene> sceneList = new ArrayList();
	private ArrayList<String> nameList = new ArrayList();
	private Stage stage;
	private ArrayList<String> arrayList;

	// Declare a primary stage to change scenes in
	public ScreenController(Stage stage) {
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
	public void changeScene(String name) {
		int tempIndex = nameList.indexOf(name);
		Scene scene = sceneList.get(tempIndex);
		stage.setScene(scene);
	}
	
	public boolean sceneExist(String name) {
		for (int i=0;i<nameList.size();i++) {
			if (nameList.get(i).equals(name)) return true;
		}  return false;
	}
}