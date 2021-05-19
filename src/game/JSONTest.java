package game;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// First Employee
		JSONObject playerDetails = new JSONObject();
		playerDetails.put("playerName", "Lokesh");
		playerDetails.put("tank", 1);// Next Level
		playerDetails.put("level", 1);
		playerDetails.put("stat", "TestStat");

		JSONObject playerObject = new JSONObject();
		playerObject.put("employee", playerDetails);

		// Add employees to list
		JSONArray playerList = new JSONArray();
		playerList.add(playerObject);

		String workingDir = System.getProperty("user.dir");
		// Write JSON file
		try (FileWriter file = new FileWriter(workingDir + "/src/jsonFiles/players.json")) {
			// We can write any JSONArray or JSONObject instance to the file

			file.write(playerList.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
