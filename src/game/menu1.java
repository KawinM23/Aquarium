package game;

import manager.ViewManager;
import manager.GameManager;
import manager.ScreenController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class menu1 extends Application {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	final double[][] buttonDetail = { { 354.0, 43.0, 576.0, 116.0, 100.0, 40.0 },
			{ 354.0, 138.0, 576.0, 192.0, 30.0, 40.0 }, { 354.0, 207.0, 576.0, 262.0, 30.0, 40.0 },
			{ 354.0, 282.0, 576.0, 357.0, 100.0, 40.0 }, { 402.0, 377.0, 529.0, 409.0, 80.0, 20.0 },
			{ 323.0, 412.0, 417.0, 443.0, 80.0, 20.0 }, { 416.0, 410.0, 512.0, 443.0, 10.0, 20.0 },
			{ 513.0, 410.0, 603.0, 443.0, 80.0, 20.0 } };
	private ScreenController screenController;
	private MediaPlayer mediaPlayer;
	private Media sound;
	private tank2 tank2;

	@Override
	public void start(Stage stage) {
		// Add ScreenController and set main stage to our stage
		screenController = new ScreenController(stage);
		// Create main menu scene
		AnchorPane root = new AnchorPane();
		Scene menuScene = new Scene(root);
		stage.setScene(menuScene);
		stage.setTitle("Aquarium Demastered");
		stage.setResizable(false);
		Canvas canvas = new Canvas(GameManager.getWIDTH(), GameManager.getHEIGHT());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		resetBackGround(gc);
		final String IMAGE_PATH = "file:res/image/menu_editted2.jpg";
		setBackGroundImage(gc, IMAGE_PATH);
		// Set sound effect for button click
		final String BUTTON_CLICK_PATH = "res/sound/buttonclick.mp3";

		sound = new Media(new File(BUTTON_CLICK_PATH).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);

		// Add buttons to current menu scene
		for (int i = 0; i < buttonDetail.length; i++) {
			addButtons(root, ("Button " + (i + 1)), buttonDetail[i], mediaPlayer);
		}
		;
		// Add menuScene in ArrayList in String "menu"
		screenController.addScene("menu", menuScene);
		// Set current scene to "menu"
		screenController.changeScene("menu");
		stage.show();
	}

	// Set background to BLACK
	private void resetBackGround(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	// Set background to an image
	private void setBackGroundImage(GraphicsContext gc, String image_path) {
		System.out.println(image_path);
		Image javafx_logo = new Image(image_path);
		gc.drawImage(javafx_logo, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	// Draw an image on the screen at specific place
	private void drawImage(GraphicsContext gc, String image_path, int xPos, int yPos, int width, int height) {
		System.out.println(image_path);
		Image javafx_logo = new Image(image_path);

		// image, x ,y, width, height
		gc.drawImage(javafx_logo, xPos, yPos, width, height);
	}

	// Add buttons and set their event listeners
	private void addButtons(AnchorPane anchorpane, String buttonText, double[] position, MediaPlayer mediaPlayer) {
		Button button = new Button(buttonText);
		button.setPrefSize((position[2] - position[0]) * 1.5, (position[3] - position[1]) * 1.5);
		button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
				+ "-fx-background-color: transparent;" + "-fx-text-fill: white");
//		button.setBorder(null);
//		button.setBackground(null);
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
				playClickSound(mediaPlayer);
				if (buttonText.equals("Button 1")) {
					Thread thread = new Thread(() -> {
						try {
							if (!screenController.sceneExist("tank0")) {
								tank0 tank0 = new tank0();
								Scene tank0Scene = tank0.getScene();
								screenController.addScene("tank0", tank0Scene);
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									System.out.println("ran");
									screenController.changeScene("tank0");
								}
							});
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
					thread.start();

				} else if (buttonText.equals("Button 2")) {
					Thread newTankthread = new Thread(() -> {
						try {
							if (!screenController.sceneExist("tank1")) {
								ViewManager vm1 = new ViewManager();
								
								Scene tank1 = vm1.getTankScene();
								screenController.addScene("tank1", tank1);
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									if(GameManager.isLOG()) System.out.println("Change To Tank1");
									screenController.changeScene("tank1");
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}

					});
					newTankthread.start();
				} else if (buttonText.equals("Button 3")) {
					Thread newTankthread = new Thread(() -> {
						try {
							if (!screenController.sceneExist("tank2")) {
								
								tank2 = new tank2();
								Scene sceneTank2 = tank2.getScene();
								screenController.addScene("tank2", sceneTank2);
							} else {
								
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									if(GameManager.isLOG()) System.out.println("Change To Tank2");
									screenController.changeScene("tank2");
									if(tank2.getThreadTank().getState() == Thread.State.WAITING) {
										synchronized (tank2.getThreadTank()) {
											tank2.getThreadTank().notify();
										}
									}
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}

					});
					newTankthread.start();
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

		anchorpane.getChildren().addAll(button);
	}

	private void playClickSound(MediaPlayer mediaPlayer) {
		Thread thread = new Thread(() -> {
			try {
				MediaPlayer newMediaPlayer = new MediaPlayer(sound);
				newMediaPlayer.play();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub

					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.start();

		// System.out.println("played");
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}