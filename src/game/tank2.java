package game;

import manager.GameManager;
import manager.PlayerController;
import manager.ScreenController;
import manager.TankManager;
import manager.ViewManager;
import model.Food;
import model.fish.Guppy;
import model.monster.Sylvester;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class tank2 {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	final double[][] buttonDetail = { { 22.0, 3.0, 72.0, 46.0, 100.0, 20.0 }, { 91.0, 3.0, 141.0, 46.0, 80.0, 20.0 },
			{ 148.0, 3.0, 198.0, 46.0, 80.0, 20.0 }, { 221.0, 3.0, 271.0, 46.0, 100.0, 20.0 },
			{ 295.0, 3.0, 344.0, 46.0, 80.0, 20.0 }, { 367.0, 3.0, 417.0, 46.0, 80.0, 20.0 },
			{ 440.0, 3.0, 490.0, 46.0, 80.0, 20.0 }, { 532.0, 3.0, 617.0, 25.0, 80.0, 20.0 } };
	private Scene scene;
	private static ScreenController screenController;
	private MediaPlayer mediaPlayer;
	private Media sound;
	private Thread threadTank;

	public tank2() {
		screenController = new ScreenController();
		AnchorPane root = new AnchorPane();
		scene = new Scene(root);
		Canvas canvas = new Canvas(640 * 1.5, 480 * 1.5);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		setBackGround(gc);
		
		String imagePathMenubar = "file:res/image/menubar.jpg";
		String imagePathBackground = "file:res/image/aquarium1.jpg";
//		drawImageFixSize(gc, imagePathBackground, 640.0 * 1.5, 480.0 * 1.5);
		drawImageFixSize(gc, imagePathMenubar, 640.0 * 1.5, 75.0 * 1.5);
		String BUTTON_CLICK_PATH = "res/sound/buttonclick.mp3"; // For example

		sound = new Media(new File(BUTTON_CLICK_PATH).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		for (int i = 0; i < buttonDetail.length; i++) {
			addButtons(root, ("Button " + (i + 1)), buttonDetail[i], mediaPlayer);
		}
		
		
		///////////////////////// TEST
		PlayerController.setPlaying(true);
		PlayerController.setMaxFood(3);
		
		Image bc = new Image("file:res/image/aquarium1.jpg");

		Guppy g1 = new Guppy("g1", 500, 300);
		TankManager.add(g1);

		Food f1 = new Food("f1", 200, 20, 1);
		TankManager.add(f1);

		Food f2 = new Food("f2", 800, 200, 1);
		TankManager.add(f2);
//		
//		Carnivore c1 = new Carnivore("c1", 300, 200);
//		TankManager.add(c1);

//		Starcatcher sc1 = new Starcatcher("sc1", 300, GameManager.getBOTTOMHEIGHT()-40);
//		TankManager.add(sc1);
//		
//		Star s1 = new Star("s1", 400, 200);
//		TankManager.add(s1);

		Sylvester sv = new Sylvester("Sv", 400, 500);
		TankManager.add(sv);
		
		//////////////////////////
		threadTank = new Thread(new Runnable() {

			@Override
			public void run() {
				Runnable updater = new Runnable() {

					@Override
					public void run() {
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//						gc.drawImage(bc, 0, 0, canvas.getWidth(), canvas.getHeight());
						gc.setFill(Color.rgb(102, 204, 255));
						gc.fillRect(0, 0, canvas.getWidth(), GameManager.getBOTTOMHEIGHT());
						TankManager.update(gc);
						drawImageFixSize(gc, imagePathMenubar, 640.0 * 1.5, 75.0 * 1.5);
					}
				};

				while (PlayerController.isPlaying()) {
					try {
						Thread.sleep(1000 / GameManager.getFRAMERATE());// 50hz ,1000/50 = 20
					} catch (InterruptedException ex) {
					}
					Platform.runLater(updater);
				}
				if(!PlayerController.isPlaying()) {
					System.out.println("LOSE");
				}
			}

		});

		// don't let thread prevent JVM shutdown
		threadTank.setDaemon(true);
		threadTank.start();

		//MouseClick Position
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Click "+event.getSceneX()+" "+event.getSceneY());
				//Add Food
				TankManager.addFood(new Food("Food",event.getSceneX(),event.getSceneY(), 1));
			}
		};
		
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

	}



	public void addScreen(ScreenController screen) {
		AnchorPane root = new AnchorPane();
		Canvas canvas = new Canvas(640 * 1.5, 480 * 1.5);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		setBackGround(gc);
		String imagePathMenubar = "file:res/image/menubar.jpg";
		String imagePathBackground = "file:res/image/aquarium1.jpg";
		drawImageFixSize(gc, imagePathBackground, 640.0 * 1.5, 480.0 * 1.5);
		drawImageFixSize(gc, imagePathMenubar, 640.0 * 1.5, 75.0 * 1.5);
		String musicFile = "res/sound/buttonclick.mp3"; // For example

		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		for (int i = 0; i < buttonDetail.length; i++) {
			addButtons(root, ("Button " + (i + 1)), buttonDetail[i], mediaPlayer);
		}
	}

	private void setBackGround(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	private void drawImage(GraphicsContext gc, String image_path) {
		System.out.println(image_path);
		Image javafx_logo = new Image(image_path);
		gc.drawImage(javafx_logo, 0, 0);
	}

	private void drawImageFixSize(GraphicsContext gc, String image_path, double x, double y) {
		System.out.println(image_path);
		Image javafx_logo = new Image(image_path);

		// image, x ,y, width, height
		gc.drawImage(javafx_logo, 0, 0, x, y);
	}

	private void addButtons(AnchorPane anchorpane, String buttonText, double[] position, MediaPlayer mediaPlayer) {
		Button button = new Button(buttonText);
		button.setPrefSize((position[2] - position[0]) * 1.5, (position[3] - position[1]) * 1.5);
		button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
				+ "-fx-background-color: transparent;" + "-fx-text-fill: white");
		button.setStyle("-fx-background-radius: " + position[4] + "px;");
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
				playClickSound(mediaPlayer);
				if (buttonText.equals("Button 8")) {
					try {
						synchronized (threadTank) {
							threadTank.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					screenController.changeScene("menu");
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

	public Scene getScene() {
		return scene;
	}
	
	public Thread getThreadTank() {
		return threadTank;
	}
	
	


}
