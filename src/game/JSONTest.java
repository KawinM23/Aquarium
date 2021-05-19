package game;

import org.json.simple.JSONObject;

import manager.JSONManager;

public class JSONTest {

	public static void main(String[] args) {

		boolean read = true;
		if(read) {
			JSONManager.readJSON();
		}
		
//		JSONManager.addToPlayerList("MrTest1", false, 1, 1);
//		JSONManager.setLevel(1);
		JSONManager.changePlayer("MrTest1");
		
		boolean write = true;
		if (write) {
			// TODO Auto-generated method stub
			// First Employee

			JSONManager.writeJSON();
		}

		
		

	}

}
