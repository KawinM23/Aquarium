package manager;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import template.PlayerMenu;
import template.stats;

public class ButtonManager {

	static String buttonTextDefaultColor = "rgb(97,44,16)";
	static String buttonTextHoverColor = "moccasin";

	// Set Button Fonts
	public static void setFont(Button button, int fontSize) {
		Font font = new Font("Arial Black", fontSize);
		button.setFont(font);
	}

	// Set Button Highlight Colors And Default Colors
	public static void setHighlightProperty(Button button, int borderRadius) {

		button.setStyle("-fx-background-radius: " + borderRadius + "px;" + "-fx-border-color: transparent;"
				+ "-fx-background-color: transparent;" + "-fx-text-fill: " + buttonTextDefaultColor);

		button.hoverProperty().addListener((event) -> {
			button.setStyle("-fx-background-radius: " + borderRadius + "px;" + "-fx-border-color: transparent;"
					+ "-fx-background-color: transparent;" + "-fx-text-fill: " + buttonTextHoverColor);
		});

		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				button.setStyle("-fx-background-radius: " + borderRadius + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: " + buttonTextHoverColor);
			}
		});

		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				button.setStyle("-fx-background-radius: " + borderRadius + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: " + buttonTextDefaultColor);
			}
		});

		button.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				button.setStyle("-fx-background-radius: " + borderRadius + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: " + buttonTextHoverColor);
			}
		});
	}

	// Set button location on an AnchorPane
	// Pixel from Platform
	public static void setButtonLocationNoZoom(AnchorPane ac, Button button, double[] position) {

		button.setPrefSize((position[2] - position[0]), (position[3] - position[1]));

		AnchorPane.setTopAnchor(button, position[1]);
		AnchorPane.setLeftAnchor(button, position[0]);

		ac.getChildren().addAll(button);
	}

	// Set button location on an AnchorPane
	// Pixel from 640x480
	public static void setButtonLocation(AnchorPane ac, Button button, double[] position) {

		button.setPrefSize((position[2] - position[0]) * 1.5, (position[3] - position[1]) * 1.5);

		AnchorPane.setTopAnchor(button, position[1] * 1.5);
		AnchorPane.setLeftAnchor(button, position[0] * 1.5);

		ac.getChildren().addAll(button);
	}

	// Set up global button handlers
	public static void setMenu1ButtonHandler(Button button) {

		EventHandler<MouseEvent> buttonHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				SoundManager.playClickSound();
				String key = button.getText();

				try {
					if ((key.substring(0, 7)).equals("Sound: ")) {
						key = "Sound: ";
					} else if ((key.substring(0, 7)).equals("Music: ")) {
						key = "Music: ";
					}
				} catch (Exception e) {

				}

				Thread thread;

				switch (key) {
				case "Start":
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.startTankLevel(1, 1);
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				case "Continue":
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.startLatestLevel();
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				case "Select Level":
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.changeScene("TankAll");
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				case "Credits":
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.changeScene("Credits");
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				case "Change Player":
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									PlayerMenu.display();
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				case "Statistics":
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.changeScene("Stats");
									stats.setStats();
									stats.drawStats();
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				case "Sound: ":
					thread = new Thread(() -> {
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
					break;
				case "Music: ":
					thread = new Thread(() -> {
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
					break;
				case "Quit":
					thread = new Thread(() -> {
						try {
							Thread.sleep(100);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									JSONManager.writeJSON();
									System.exit(0);
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				default:
				}

			}
		};

		button.addEventHandler(MouseEvent.MOUSE_CLICKED, buttonHandler);
	}

	public static void setMenu2ButtonHandler(Button button) {

		EventHandler<MouseEvent> buttonHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				SoundManager.playClickSound();
				String key = button.getText();

				if (key.equals("Back")) {
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

				} else if (key.equals("Tank 1")) {
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
				} else if (key.equals("Tank 2")) {
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
				} else if (key.equals("Tank 3")) {
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
				} else if (key.equals("Tank 4")) {
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
				} else if (key.equals("Tank 1-1")) {
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
				} else if (key.equals("Tank 1-2")) {
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
				} else if (key.equals("Tank 1-3")) {
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
									manager.startLevel(LevelManager.getLevel1_3());
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
		};

		button.addEventHandler(MouseEvent.MOUSE_CLICKED, buttonHandler);
	}

	public static void setWinLoseButtonHandler(Button button) {
		EventHandler<MouseEvent> buttonHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				SoundManager.playClickSound();
				String key = button.getText();

				Thread thread;

				switch (key) {
				case "Next Level":
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.getManager().clearLevel();
									ViewManager.clearLevel();
									
									SceneController.startLatestLevel();
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				case "Back to Menu":
					SoundManager.stopBgm();
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.getManager().clearLevel();
									ViewManager.clearLevel();

									SoundManager.setBgm(1);
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
					break;
				case "Try Again":
					thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SceneController.getManager().clearLevel();
									ViewManager.clearLevel();
									
									SceneController.startTankLevel(ViewManager.getCurrentTank(),
											ViewManager.getCurrentLevel());
								}
							});

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();
					break;
				default:
				}

			}
		};

		button.addEventHandler(MouseEvent.MOUSE_CLICKED, buttonHandler);
	}

}
