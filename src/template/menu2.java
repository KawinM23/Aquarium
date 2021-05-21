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
import manager.GameManager;
import manager.Level;
import manager.LevelManager;
import manager.SceneController;
import manager.SoundManager;
import manager.ViewManager;
import testing.tank0;

public class menu2 {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	// THIS IS MEASURED IN 940 x 720 PIXELS
	final double[][] buttonDetail = { { 553, 68, 899, 157, 20, 40 }, { 557, 203, 897, 291, 20, 40 },
			{ 563, 338, 898, 423, 20, 40 }, { 564, 460, 903, 543, 20, 40 }, { 578, 597, 882, 681, 40, 40 } };

	final String IMAGE_PATH = ClassLoader.getSystemResource("menu2_editted3.jpg").toString();
	Scene scene;
	String[] buttonTexts;
	// ClassLoader.getSystemResource("").toString();

	public menu2(String[] buttonTexts) {
		this.buttonTexts = buttonTexts;
		AnchorPane root = new AnchorPane();
		this.scene = new Scene(root);
		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		resetBackGround(gc);

		setBackGroundImage(gc, IMAGE_PATH);

		for (int i = 0; i < buttonDetail.length; i++) {
			addButtons(root, buttonTexts[i], buttonDetail[i]);
		}
		;
	}

	// Set background to BLACK
	private void resetBackGround(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	// Set background to an image
	private void setBackGroundImage(GraphicsContext gc, String image_path) {
		Image image = new Image(image_path);
		gc.drawImage(image, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	// Add buttons and set their event listeners
	private void addButtons(AnchorPane anchorpane, String buttonText, double[] position) {
		Button button = new Button(buttonText);
		button.setPrefSize((position[2] - position[0]), (position[3] - position[1]));
		button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
				+ "-fx-background-color: transparent;" + "-fx-text-fill: rgb(97, 44, 16)");
//				button.setBorder(null);
//				button.setBackground(null);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Pressed " + buttonText);
			}
		});

		Font font = new Font("Arial Black", position[5]);
		button.setFont(font);

		AnchorPane.setTopAnchor(button, position[1]);
		AnchorPane.setLeftAnchor(button, position[0]);

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
				if (buttonText.equals("Back")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (SceneController.isAtAllTank()) {
										SceneController.changeScene("MainMenu");
									} else if (SceneController.isAtNumberTank()) {
										SceneController.changeScene("TankAll");
									}
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();

				} else if (buttonText.equals("Tank 1")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.changeScene("Tank1");
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
				} else if (buttonText.equals("Tank 2")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.changeScene("Tank2");
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
				} else if (buttonText.equals("Tank 1-1")) {
					SoundManager.stopBgm();
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									// TODO Change Bgm later
									SoundManager.setBgm(1);
									SoundManager.playBgm();

									ViewManager manager = new ViewManager();
									manager.startLevelTest(1, 1);
									SceneController.changeScene(manager.getTankScene());
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
				} else if (buttonText.equals("Tank 1-2")) {
					SoundManager.stopBgm();
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									// TODO Change Bgm later
									SoundManager.setBgm(0);
									SoundManager.playBgm();

									ViewManager manager = new ViewManager();
									manager.startLevel(LevelManager.getLevel1_2());
									SceneController.changeScene(manager.getTankScene());
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

		anchorpane.getChildren().addAll(button);
	}

	public Scene getScene() {
		return this.scene;
	}
}
