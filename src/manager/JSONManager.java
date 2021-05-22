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
	private static int tank = 0;
	private static int level = 0;

	private static int moneyGained;
	private static int fishBought;
	private static long playTime;
	private static int foodBought;
	private static int monsterDefeated;

	private static JSONArray newPlayerList = new JSONArray();

	private static ArrayList<String> jsonNameList = new ArrayList<String>();
	private static ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();

	@SuppressWarnings("unchecked")
	public static void readJSON() {
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(workingDir + "/src/jsonFiles/players.json")) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray playerList = (JSONArray) obj;

			jsonList.clear();
			jsonNameList.clear();

			// Iterate over employee array
			playerList.forEach(emp -> JSONManager.parsePlayerObject((JSONObject) emp));

			if (playerList.size() == 0) {
				addNewPlayer("New Player");
				writeJSON();
				changePlayer("New Player");
			}
//			else if (playerList.size() == 1 && jsonList.size() == 1) {
//				JSONObject eachPlayer = (JSONObject) jsonList.get(0).get("player");
//				eachPlayer.remove("currentPlayer");
//				eachPlayer.put("currentPlayer", true);
//				setCurrentPlayer(false);
//				writeJSON();
//			}

		} catch (FileNotFoundException e) {
			// write new file
			addNewPlayer("New Player");
			writeJSON();
			changePlayer("New Player");

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

			long etank = (long) playerObject.get("tank");
			long elevel = (long) playerObject.get("level");
			long emoney = (long) playerObject.get("moneyGained");
			long efish = (long) playerObject.get("fishBought");
			long eplaytime = (long) playerObject.get("playTime");
			long efood = (long) playerObject.get("foodBought");
			long emonster = (long) playerObject.get("monsterDefeated");
			long emusic = (long) playerObject.get("musicLevel");
			long eeffect = (long) playerObject.get("effectLevel");

			if (ecurrentPlayer) {
				playerName = eplayerName;
				currentPlayer = ecurrentPlayer;
				tank = (int) etank;
				level = (int) elevel;
				moneyGained = (int) emoney;
				fishBought = (int) efish;
				playTime = eplaytime;
				foodBought = (int) efood;
				monsterDefeated = (int) emonster;
				SoundManager.setBgmVolumeLevel((int) emusic);
				SoundManager.setSoundVolumeLevel((int) eeffect);

			} else {
				jsonNameList.add(eplayerName);
				jsonList.add(player);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void changePlayer(String playerName) {
		// TODO Auto-generated method stub
		readJSON();
		for (JSONObject eachJSONobj : jsonList) {
			JSONObject eachPlayer = (JSONObject) eachJSONobj.get("player");
			if (eachPlayer.get("playerName").equals(playerName)) {
				eachPlayer.remove("currentPlayer");
				eachPlayer.put("currentPlayer", true);
				setCurrentPlayer(false);
			}
		}
		writeJSON();
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
			writeJSON();
		}
	}

	public static void writeJSON() {

		addCurrentPlayer();
		addOtherPlayer(jsonList);

		// Write JSON file
		try (FileWriter file = new FileWriter(workingDir + "/src/jsonFiles/players.json")) {
			// We can write any JSONArray or JSONObject instance to the file

			file.write(newPlayerList.toJSONString());
			file.flush();
			newPlayerList.clear();
			jsonNameList.clear();
			jsonList.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}

		readJSON();
	}

	@SuppressWarnings("unchecked")
	public static void addCurrentPlayer() {
		JSONObject playerDetails = new JSONObject();
		if (playerName == null) {
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
		playerDetails.put("musicLevel", SoundManager.getBgmVolumeLevel());
		playerDetails.put("effectLevel", SoundManager.getSoundVolumeLevel());
		JSONObject playerObject = new JSONObject();
		playerObject.put("player", playerDetails);

		newPlayerList.add(playerObject);
	}

	@SuppressWarnings("unchecked")
	public static void addToPlayerList(String playerName, boolean currentPlayer, int tank, int level, int moneyGained,
			int fishBought, int playTime, int foodBought, int monsterDefeated, int musicLevel, int effectLevel) {
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
		playerDetails.put("musicLevel", musicLevel);
		playerDetails.put("effectLevel", effectLevel);
		JSONObject playerObject = new JSONObject();
		playerObject.put("player", playerDetails);

		newPlayerList.add(playerObject);
	}

	@SuppressWarnings("unchecked")
	public static boolean addNewPlayer(String playerName) {
		if (JSONManager.playerName != null) {
			if (JSONManager.playerName.equals(playerName)) {
				return false;
			}
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
		playerDetails.put("musicLevel", 2);
		playerDetails.put("effectLevel", 2);

		JSONObject playerObject = new JSONObject();
		playerObject.put("player", playerDetails);
		newPlayerList.add(playerObject);
		writeJSON();
		return true;
	}

	@SuppressWarnings("unchecked")
	public static void addOtherPlayer(ArrayList<JSONObject> jsonList) {
		for (JSONObject eachJSONObj : jsonList) {
			JSONObject eachPlayer = (JSONObject) eachJSONObj.get("player");
			if (eachPlayer.get("playerName").equals(playerName)) {
				continue;
			}
			newPlayerList.add(eachJSONObj);
		}
	}

	public static boolean isNameExist(String name) {
		if (name.equals(playerName)) {
			return true;
		}
		for (JSONObject eachJSONobj : jsonList) {
			JSONObject eachPlayer = (JSONObject) eachJSONobj.get("player");
			if (eachPlayer.get("playerName").equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static void nextLevel() {
		if (level < 4) {
			setLevel(level + 1);
		} else if (level == 4 && tank != 4) {
			setLevel(1);
			setTank(tank + 1);
		}
	}

	public static boolean isPlayable(int tankNumber, int levelNumber) {

		if (tankNumber < getTank()) {
			return true;
		} else if (tankNumber > getTank()) {
			return false;
		} else {
			if (levelNumber <= getLevel()) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static void clear() {

		try (FileWriter file = new FileWriter(workingDir + "/src/jsonFiles/players.json")) {
			// We can write any JSONArray or JSONObject instance to the file

			file.write(newPlayerList.toJSONString());
			file.flush();
			newPlayerList.clear();
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

	public static int getTank() {
		return tank;
	}

	public static void setTank(int tank) {
		JSONManager.tank = tank;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		JSONManager.level = level;
	}

	public static JSONArray getNewPlayerList() {
		return newPlayerList;
	}

	public static void setNewPlayerList(JSONArray playerList) {
		JSONManager.newPlayerList = playerList;
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

	public static void addMoneyGained(int moneyGained) {
		JSONManager.moneyGained = JSONManager.moneyGained + moneyGained;
	}

	public static int getFishBought() {
		return fishBought;
	}

	public static void setFishBought(int fishBought) {
		JSONManager.fishBought = fishBought;
	}

	public static void addFishBought(int fishBought) {
		JSONManager.fishBought = JSONManager.fishBought + fishBought;
	}

	public static long getPlayTime() {
		return playTime;
	}

	public static void setPlayTime(long playTime) {
		JSONManager.playTime = playTime;
	}

	public static void addPlayTime(long duration) {
		JSONManager.playTime = JSONManager.playTime + duration;
	}

	public static int getFoodBought() {
		return foodBought;
	}

	public static void setFoodBought(int foodBought) {
		JSONManager.foodBought = foodBought;
	}

	public static void addFoodBought(int foodBought) {
		JSONManager.foodBought = JSONManager.foodBought + foodBought;
	}

	public static int getMonsterDefeated() {
		return monsterDefeated;
	}

	public static void setMonsterDefeated(int monsterDefeated) {
		JSONManager.monsterDefeated = monsterDefeated;
	}

	public static void addMonsterDefeated(int monsterDefeated) {
		JSONManager.monsterDefeated = JSONManager.monsterDefeated + monsterDefeated;
	}

	public static ArrayList<String> getJsonNameList() {
		return jsonNameList;
	}

	public static void setJsonNameList(ArrayList<String> jsonNameList) {
		JSONManager.jsonNameList = jsonNameList;
	}

}
