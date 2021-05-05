package testing;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScreenControllerTest1 {
    private ArrayList<Scene> sceneList = new ArrayList();
    private ArrayList<String> nameList = new ArrayList();
    private Stage stage;
	private ArrayList<String> arrayList;

    // Declare a primary stage to change scenes in
    public ScreenControllerTest1(Stage stage) {
        this.stage = stage;
    }
    
    // Add scene in ArrayList to call later (call in names as String)
    public void addScene(String name, Scene scene){
         sceneList.add(scene);
         nameList.add(name);
    }

    // Remove scene in ArrayList collection
    public void removeScreen(String name){
        int tempIndex = nameList.indexOf(name);
        sceneList.remove(tempIndex);
        nameList.remove(tempIndex);
    }

    // Change scene of primary stage to another scene
    public void changeScene(String name){
    	int tempIndex = nameList.indexOf(name);
    	Scene scene = sceneList.get(tempIndex);
        stage.setScene(scene);
    }
}