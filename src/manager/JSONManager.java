package manager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONManager {

	private static String workingDir = System.getProperty("user.dir");

	private static String playerName = null;
	private static boolean currentPlayer = true;
	private static long tank = 0;
	private static long level = 0;

	private static int moneyGained;
	private static int fishBought;
	private static long playTime;
	private static int foodBought;
	private static int monsterDefeated;

	private static JSONArray playerList = new JSONArray();
	private static ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();

	@SuppressWarnings("unchecked")
	public static void readJSON() {
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(workingDir + "/src/jsonFiles/players.json")) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray playerList = (JSONArray) obj;
			System.out.println(playerList);

			// Iterate over employee array
			playerList.forEach(emp -> JSONManager.parsePlayerObject((JSONObject) emp));

		} catch (FileNotFoundException e) {
			// write new file
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void parsePlayerObject(JSONObject player) {
		// Get employee object within list
		JSONObject playerObject = (JSONObject) player.get("player");
		if (playerObject != null) {
			// Get employee first name
			String eplayerName = (String) playerObject.get("playerName");

			boolean ecurrentPlayer = (boolean) playerObject.get("currentPlayer");

			// Get employee last name
			long etank = (long) playerObject.get("tank");

			// Get employee website name
			long elevel = (long) playerObject.get("level");

			if (ecurrentPlayer) {
				playerName = eplayerName;
				currentPlayer = ecurrentPlayer;
				tank = etank;
				level = elevel;
			} else {
				jsonList.add(player);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void changePlayer(String playerName) {
		// TODO Auto-generated method stub
		for (JSONObject eachJSONobj : jsonList) {
			JSONObject eachPlayer = (JSONObject) eachJSONobj.get("player");
			if (eachPlayer.get("playerName").equals(playerName)) {
				eachPlayer.remove("currentPlayer");
				eachPlayer.put("currentPlayer", true);
				setCurrentPlayer(false);
			}
		}
		writeJSON();
		readJSON();
	}

	public static void removePlayer(String playerName) {
		// TODO Auto-generated method stub
		JSONObject removedJSONobj = null;
		for (JSONObject eachJSONobj : jsonList) {
			JSONObject eachPlayer = (JSONObject) eachJSONobj.get("player");
			if (eachPlayer.get("playerName").equals(playerName) || eachPlayer.get("playerName").equals(null)) {
				removedJSONobj = eachJSONobj;
			}
		}
		if (removedJSONobj != null) {
			jsonList.remove(removedJSONobj);
		}
	}

	public static void writeJSON() {

		addCurrentPlayer();
		addOtherPlayer(jsonList);

		System.out.println(playerList);
		// Write JSON file
		try (FileWriter file = new FileWriter(workingDir + "/src/jsonFiles/players.json")) {
			// We can write any JSONArray or JSONObject instance to the file

			file.write(playerList.toJSONString());
			file.flush();
			playerList.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void addCurrentPlayer() {
		JSONObject playerDetails = new JSONObject();
		if(playerName == null) {
			return;
		}
		playerDetails.put("playerName", playerName);
		playerDetails.put("currentPlayer", currentPlayer);
		playerDetails.put("tank", tank);// Next Level
		playerDetails.put("level", level);
		playerDetails.put("moneyGained", moneyGained);
		playerDetails.put("fishBought", fishBought);
		playerDetails.put("playTime", playTime);
		playerDetails.put("foodBought", foodBought);
		playerDetails.put("monsterDefeated", monsterDefeated);
		JSONObject playerObject = new JSONObject();
		playerObject.put("player", playerDetails);

		playerList.add(playerObject);
	}

	@SuppressWarnings("unchecked")
	public static void addToPlayerList(String playerName, boolean currentPlayer, int tank, int level, int moneyGained,
			int fishBought, int playTime, int foodBought, int monsterDefeated) {
		JSONObject playerDetails = new JSONObject();
		playerDetails.put("playerName", playerName);
		playerDetails.put("currentPlayer", currentPlayer);
		playerDetails.put("tank", tank);// Next Level
		playerDetails.put("level", level);
		playerDetails.put("moneyGained", moneyGained);
		playerDetails.put("fishBought", fishBought);
		playerDetails.put("playTime", playTime);
		playerDetails.put("foodBought", foodBought);
		playerDetails.put("monsterDefeated", monsterDefeated);
		JSONObject playerObject = new JSONObject();
		playerObject.put("player", playerDetails);

		playerList.add(playerObject);
	}

	@SuppressWarnings("unchecked")
	public static boolean addNewPlayer(String playerName) {
		boolean nameExist = false;
		if (JSONManager.playerName.equals(playerName)) {
			return false;
		}
		for (JSONObject eachJSONobj : jsonList) {
			JSONObject eachPlayer = (JSONObject) eachJSONobj.get("player");
			if (eachPlayer.get("playerName").equals(playerName)) {
				return false;
			}
		}

		JSONObject playerDetails = new JSONObject();
		playerDetails.put("playerName", playerName);
		playerDetails.put("currentPlayer", false);
		playerDetails.put("tank", 1);// Next Level
		playerDetails.put("level", 1);
		playerDetails.put("moneyGained", 0);
		playerDetails.put("fishBought", 0);
		playerDetails.put("playTime", 0);
		playerDetails.put("foodBought", 0);
		playerDetails.put("monsterDefeated", 0);
		JSONObject playerObject = new JSONObject();
		playerObject.put("player", playerDetails);
		playerList.add(playerObject);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public static void addOtherPlayer(ArrayList<JSONObject> jsonList) {
		for (JSONObject eachJSONObj : jsonList) {
			JSONObject eachPlayer = (JSONObject) eachJSONObj.get("player");
			if (eachPlayer.get("playerName").equals(playerName)) {
				continue;
			}
			playerList.add(eachJSONObj);
		}
	}

	public static void clear() {

		try (FileWriter file = new FileWriter(workingDir + "/src/jsonFiles/players.json")) {
			// We can write any JSONArray or JSONObject instance to the file

			file.write(playerList.toJSONString());
			file.flush();
			playerList.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPlayerName() {
		return playerName;
	}

	public static void setPlayerName(String playerName) {
		JSONManager.playerName = playerName;
	}

	public static long getTank() {
		return tank;
	}

	public static void setTank(long tank) {
		JSONManager.tank = tank;
	}

	public static long getLevel() {
		return level;
	}

	public static void setLevel(long level) {
		JSONManager.level = level;
	}

	public static JSONArray getPlayerList() {
		return playerList;
	}

	public static void setPlayerList(JSONArray playerList) {
		JSONManager.playerList = playerList;
	}

	public static ArrayList<JSONObject> getJsonList() {
		return jsonList;
	}

	public static void setJsonList(ArrayList<JSONObject> jsonList) {
		JSONManager.jsonList = jsonList;
	}

	public static boolean isCurrentPlayer() {
		return currentPlayer;
	}

	public static void setCurrentPlayer(boolean currentPlayer) {
		JSONManager.currentPlayer = currentPlayer;
	}

	public static int getMoneyGained() {
		return moneyGained;
	}

	public static void setMoneyGained(int moneyGained) {
		JSONManager.moneyGained = moneyGained;
	}

	public static int getFishBought() {
		return fishBought;
	}

	public static void setFishBought(int fishBought) {
		JSONManager.fishBought = fishBought;
	}

	public static long getPlayTime() {
		return playTime;
	}

	public static void setPlayTime(long playTime) {
		JSONManager.playTime = playTime;
	}

	public static int getFoodBought() {
		return foodBought;
	}

	public static void setFoodBought(int foodBought) {
		JSONManager.foodBought = foodBought;
	}

	public static int getMonsterDefeated() {
		return monsterDefeated;
	}

	public static void setMonsterDefeated(int monsterDefeated) {
		JSONManager.monsterDefeated = monsterDefeated;
	}

}
