package template;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import manager.ButtonManager;
import manager.DrawManager;
import manager.GameManager;
import manager.JSONManager;
import manager.SceneController;
import manager.SoundManager;

public class Stats {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	// THIS IS MEASURED IN 940 x 720 PIXELS
	private final double[][] BUTTON_DETAIL = { { 578, 597, 882, 681, 40, 40 } };

	private final static String IMAGE_PATH = ClassLoader.getSystemResource("BlankMenu.jpg").toString();
	private Scene scene;
	private String[] buttonTexts;

	// Get Infomation from JSONManager
	private static int tank;
	private static int level;

	private static int moneyGained;
	private static int fishBought;
	private static long playTime;
	private static int foodBought;
	private static int monsterDefeated;

	private static GraphicsContext gcc;
	// ClassLoader.getSystemResource("").toString();

	public Stats(String[] buttonTexts) {
		this.buttonTexts = buttonTexts;
		AnchorPane root = new AnchorPane();
		this.scene = new Scene(root);
		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		DrawManager.resetBackGround(gc);

		DrawManager.setBackGroundImage(gc, IMAGE_PATH);

		for (int i = 0; i < BUTTON_DETAIL.length; i++) {
			Button button = new Button(buttonTexts[i]);
			ButtonManager.setFont(button, (int) BUTTON_DETAIL[i][5]);
			ButtonManager.setHighlightProperty(button, (int) BUTTON_DETAIL[i][4]);
			ButtonManager.setStatsButtonHandler(button);
			ButtonManager.setButtonLocationNoZoom(root, button, BUTTON_DETAIL[i]);
			// addButtons(root, buttonTexts[i], BUTTON_DETAIL[i]);
		}
		;

		gcc = gc;

	}

	public Scene getScene() {
		return this.scene;
	}

	public static void setStats() {
		tank = JSONManager.getTank();
		level = JSONManager.getLevel();
		moneyGained = JSONManager.getMoneyGained();
		fishBought = JSONManager.getFishBought();
		playTime = JSONManager.getPlayTime();
		foodBought = JSONManager.getFoodBought();
		monsterDefeated = JSONManager.getMonsterDefeated();
	}

	public static void drawStats() {
		DrawManager.resetBackGround(gcc);
		DrawManager.setBackGroundImage(gcc, IMAGE_PATH);
		String tankLevelText = "Progress: Tank " + tank + "-" + level;
		String line = "_______________________________________________";
		String moneyGainedText = "Total Money Gained: " + moneyGained;
		String fishBoughtText = "Total Fish Bought: " + fishBought;
		String playTimeText = "Total Playtime: " + convertPlayTime(playTime);
		String foodBoughtText = "Total Food Bought: " + foodBought;
		String monsterDefeatedText = "Total Monsters Defeated: " + monsterDefeated;
		String[] statsList = { tankLevelText, line, moneyGainedText, fishBoughtText, playTimeText, foodBoughtText,
				monsterDefeatedText };

		DrawManager.drawGuraGreeting(gcc, "Statistics");

		for (int i = 0; i < statsList.length; i++) {
			// GraphicsContext, Font Size, posX, posY, Width
			if (i != 1) {
				DrawManager.drawStatsText(gcc, statsList[i], 32, 500, 100 + (70 * i), 450);
			} else if (i == 1) {
				DrawManager.drawStatsText(gcc, statsList[i], 32, 500, 100 + (70 * i) - 20, 450);
			}
		}
	}

	// TODO MAKE PLAYTIME UPDATE REALTIME
	public static String convertPlayTime(long nanoTime) {
		int seconds = (int) (nanoTime / 1e9);
		int hr = Math.floorDiv(seconds, 60 * 60);
		seconds -= hr * 60 * 60;
		int min = Math.floorDiv(seconds, 60);
		seconds -= min * 60;
		String answer = hr + " hr " + min + " min " + seconds + " sec ";
		return answer;
	}

}
