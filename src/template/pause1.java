package template;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import manager.DrawManager;
import manager.PlayerController;
import manager.SceneController;
import manager.SoundManager;
import manager.ViewManager;

public class pause1 {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	static final double[][] buttonDetail = { { 53, 138, 340, 217, 20, 40.0 }, { 54, 253, 340, 325, 20, 30.0 },
			{ 59, 363, 342, 434, 20.0, 30.0 }, { 63, 467, 342, 537, 100.0, 30.0 } };
	static String[] buttonTexts = { "Resume", "Music: ", "Sound: ", "Back to Menu" };
	private static final String IMAGE_PATH = ClassLoader.getSystemResource("pause.jpg").toString();
	private static Image image = new Image(IMAGE_PATH);
	private static final int posX = 275;
	private static final int posY = 50;
	private static final int width = 391;
	private static final int height = 576;
	static AnchorPane anchorPane;
	static ArrayList<Button> buttonList = new ArrayList<Button>();

	public static void drawPane(GraphicsContext gc) {
		DrawManager.drawImageFixSize(gc, image, posX, posY, width, height);
	}

	public static void setPauseButtons() {
		for (int i = 0; i <= 3; i++) {
			addButtons(buttonTexts[i], buttonDetail[i]);
		}
	}

	public static void setAnchorPane(AnchorPane newAnchorPane) {
		anchorPane = newAnchorPane;
	}

	private static void addButtons(String buttonText, double[] position) {
		Button button = new Button(buttonText);

		if (buttonText == "Music: ") {
			button.setText("Music: " + SoundManager.getVolumeLevelWord(SoundManager.getBgmVolumeLevel()));
		} else if (buttonText == "Sound: ") {
			button.setText("Sound: " + SoundManager.getVolumeLevelWord(SoundManager.getSoundVolumeLevel()));
		}

		button.setPrefSize((position[2] - position[0]), (position[3] - position[1]));
		button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
				+ "-fx-background-color: transparent;" + "-fx-text-fill: rgb(97, 44, 16)");
//			button.setBorder(null);
//			button.setBackground(null);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Pressed " + buttonText);
			}
		});

		Font font = new Font("Arial Black", position[5]);
		button.setFont(font);

		AnchorPane.setTopAnchor(button, position[1] + posY);
		AnchorPane.setLeftAnchor(button, position[0] + posX);

		button.hoverProperty().addListener((event) -> {
			button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
					+ "-fx-background-color: transparent;" + "-fx-text-fill: moccasin");
		});

		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: moccasin");
			}
		});
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: rgb(97, 44, 16)");
			}
		});
		button.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: moccasin");
				SoundManager.playClickSound();
				if (buttonText.equals("Resume")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									PlayerController.togglePause();
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();

				} else if (buttonText.equals("Back to Menu")) {
					SoundManager.stopBgm();
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									SceneController.getManager().clearLevel();
									
									// TODO Auto-generated method stub
									SoundManager.setBgm(0);
									SoundManager.playBgm();
									SceneController.changeScene("MainMenu");

								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();

				} else if (((button.getText()).substring(0, 7)).equals("Sound: ")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SoundManager.nextSoundVolumeLevel();
									button.setText("Sound: "
											+ SoundManager.getVolumeLevelWord(SoundManager.getSoundVolumeLevel()));
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();

				} else if (((button.getText()).substring(0, 7)).equals("Music: ")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SoundManager.nextBgmVolumeLevel();
									button.setText("Music: "
											+ SoundManager.getVolumeLevelWord(SoundManager.getBgmVolumeLevel()));
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();

				} else if (buttonText.equals("Tank 3")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.changeScene("Tank3");
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();

				} else if (buttonText.equals("Tank 4")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.changeScene("Tank4");
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();

				}
			}
		});
		button.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (button.isHover()) {
					button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
							+ "-fx-background-color: transparent;" + "-fx-text-fill: moccasin");
				} else {
					button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
							+ "-fx-background-color: transparent;" + "-fx-text-fill: rgb(97, 44, 16)");
				}

			}
		});

		buttonList.add(button);

		anchorPane.getChildren().addAll(button);
	}

	public static void hideButtons() {
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setVisible(false);
		}
	}

	public static void showButtons() {
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setVisible(true);
		}
	}

}
