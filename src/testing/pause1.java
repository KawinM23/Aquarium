package testing;

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

public class pause1 {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	static final double[][] buttonDetail = { { 53, 138, 340, 217, 20, 40.0 }, { 54, 253, 340, 325, 20, 40.0 },
			{ 59, 363, 342, 434, 20.0, 40.0 }, { 63, 467, 342, 537, 100.0, 40.0 } };
	static String[] buttonTexts = {"Resume","Undefined","Undefined","Back to Menu"};
	private static final String IMAGE_PATH = ClassLoader.getSystemResource("pause.jpg").toString();
	private static Image image = new Image(IMAGE_PATH);
	private static final int posX = 275;
	private static final int posY = 50;
	private static final int width = 391;
	private static final int height = 576;
	static AnchorPane anchorPane;

	public static void drawPane(GraphicsContext gc) {
		DrawManager.drawImageFixSize(gc, image, posX, posY, width, height);
	}

	public static void setAllButton() {
		for (int i = 0; i <= 3; i++) {
			addButtons(buttonTexts[i],buttonDetail[i]);
		}
	}

	public static void setAnchorPane(AnchorPane newAnchorPane) {
		anchorPane = newAnchorPane;
	}

	private static void addButtons(String buttonText, double[] position) {
		Button button = new Button(buttonText);
		button.setPrefSize((position[2] - position[0]) * 1.5, (position[3] - position[1]) * 1.5);
		button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
				+ "-fx-background-color: transparent;" + "-fx-text-fill: white");
//			button.setBorder(null);
//			button.setBackground(null);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Pressed " + buttonText);
			}
		});

		Font font = new Font(position[5]);
		button.setFont(font);

		AnchorPane.setTopAnchor(button, position[1] * 1.5);
		AnchorPane.setLeftAnchor(button, position[0] * 1.5);

		button.hoverProperty().addListener((event) -> {
			button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
					+ "-fx-background-color: transparent;" + "-fx-text-fill: red");
		});

		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: red");
			}
		});
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: white");
			}
		});
		button.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: yellow");
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

				} else if (buttonText.equals("Quit")) {
					Thread thread = new Thread(() -> {
						try {
							Thread.sleep(100);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub

									System.exit(0);
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

				}
			}
		});
		button.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (button.isHover()) {
					button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
							+ "-fx-background-color: transparent;" + "-fx-text-fill: red");
				} else {
					button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
							+ "-fx-background-color: transparent;" + "-fx-text-fill: white");
				}

			}
		});

		anchorPane.getChildren().addAll(button);
	}

}