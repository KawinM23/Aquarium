package game;

import manager.ViewManager;
import javafx.application.Application;
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

public class menu1 extends Application {
	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomLeft, borderRadius,
	// FontSize }
	final double[][] buttonDetail = { { 354.0, 43.0, 576.0, 116.0, 100.0, 40.0 },
			{ 354.0, 138.0, 576.0, 192.0, 30.0, 40.0 }, { 354.0, 207.0, 576.0, 262.0, 30.0, 40.0 },
			{ 354.0, 282.0, 576.0, 357.0, 100.0, 40.0 }, { 402.0, 377.0, 529.0, 409.0, 80.0, 20.0 },
			{ 323.0, 412.0, 417.0, 443.0, 80.0, 20.0 }, { 416.0, 410.0, 512.0, 443.0, 10.0, 20.0 },
			{ 513.0, 410.0, 603.0, 443.0, 80.0, 20.0 } };

	@Override
	public void start(Stage stage) {
		AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Aquarium Demastered");
		stage.setResizable(false);
		Canvas canvas = new Canvas(640 * 1.5, 480 * 1.5);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		setBackGround(gc);
		String image_path = "file:res/image/menu.jpg";
		drawImage(gc, image_path);
		for (int i = 0; i < buttonDetail.length; i++) {
			addButtons(root, ("Button " + (i + 1)), buttonDetail[i]);
		}

		stage.show();
	}

	public void setBackGround(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	public void drawImage(GraphicsContext gc, String image_path) {
		System.out.println(image_path);
		Image javafx_logo = new Image(image_path);
		gc.drawImage(javafx_logo, 0, 0, 640 * 1.5, 480 * 1.5);
	}

	public void drawImageFixSize(GraphicsContext gc, String image_path) {
		System.out.println(image_path);
		Image javafx_logo = new Image(image_path);

		// image, x ,y, width, height
		gc.drawImage(javafx_logo, 40, 40, 600, 200);
	}

	public void addButtons(AnchorPane anchorpane, String buttonText, double[] position) {
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
			System.out.println("Hovered " + buttonText);
			button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
					+ "-fx-background-color: transparent;" + "-fx-text-fill: red");
		});

		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				System.out.println("Entered " + buttonText);
				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: red");
			}
		});
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				System.out.println("Exited " + buttonText);
				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: white");
			}
		});
		button.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				System.out.println("Exited " + buttonText);
				button.setStyle("-fx-background-radius: " + position[4] + "px;" + "-fx-border-color: transparent;"
						+ "-fx-background-color: transparent;" + "-fx-text-fill: yellow");
			}
		});
		button.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				System.out.println("Exited " + buttonText);
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

	public static void main(String[] args) {
		Application.launch(args);
	}
}