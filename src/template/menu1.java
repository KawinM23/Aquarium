package template;

import game.tank0;
import game.tank2;
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
import javafx.scene.text.FontWeight;
import manager.GameManager;
import manager.JSONManager;
import manager.SceneController;
import manager.SoundManager;
import manager.ViewManager;

public class menu1 {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	final double[][] buttonDetail = { { 354.0, 43.0, 576.0, 116.0, 100.0, 50.0 },
			{ 354.0, 138.0, 576.0, 192.0, 30.0, 40.0 }, { 354.0, 207.0, 576.0, 262.0, 30.0, 40.0 },
			{ 354.0, 282.0, 576.0, 357.0, 100.0, 40.0 }, { 402.0, 377.0, 529.0, 409.0, 80.0, 20.0 },
			{ 323.0, 410.0, 417.0, 443.0, 78.0, 15.0 }, { 416.0, 410.0, 512.0, 443.0, 10.0, 15.0 },
			{ 513.0, 410.0, 603.0, 443.0, 80.0, 20.0 } };
	final String IMAGE_PATH = ClassLoader.getSystemResource("menu_editted5.jpg").toString();
	Scene scene;
	String[] buttonTexts;
	// ClassLoader.getSystemResource("").toString();

	public menu1(String[] buttonTexts) {
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
		button.setPrefSize((position[2] - position[0]) * 1.5, (position[3] - position[1]) * 1.5);
		button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
				+ "-fx-background-color: transparent;" + "-fx-text-fill: rgb(97, 44, 16)");
//			button.setBorder(null);
//			button.setBackground(null);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Pressed " + button.getText());
			}
		});

		Font font = new Font("Arial Black", position[5]);
		button.setFont(font);

		AnchorPane.setTopAnchor(button, position[1] * 1.5);
		AnchorPane.setLeftAnchor(button, position[0] * 1.5);

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
				if (buttonText.equals("Start")) {
					Thread thread = new Thread(() -> {
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

				} else if (buttonText.equals("Quit")) {
					Thread thread = new Thread(() -> {
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

				} else if (((button.getText()).substring(0, 7)).equals("Sound: ")) {
					Thread thread = new Thread(() -> {
						try {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									SoundManager.nextClickVolumeLevel();
									button.setText("Sound: "
											+ SoundManager.getVolumeLevelWord(SoundManager.getClickVolumeLevel()));
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

				} else if (buttonText.equals("Credits")) {
					Thread thread = new Thread(() -> {
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
