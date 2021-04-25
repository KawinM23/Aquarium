package game;

import manager.ViewManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class menu1 extends Application {
	@Override
	public void start(Stage stage) {
		AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Aquarium Demastered");
		
		Canvas canvas = new Canvas(640,480);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		
		setBackGround(gc);
		String image_path = "file:res/image/menu.jpg";
		drawImage(gc, image_path);
		
		stage.show();
	}
	
	public void setBackGround(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}
	
	public void drawImage(GraphicsContext gc, String image_path) {
		System.out.println(image_path);
		Image javafx_logo = new Image(image_path);
		gc.drawImage(javafx_logo,0,0);
	}
	
	public void drawImageFixSize(GraphicsContext gc, String image_path) {
		System.out.println(image_path);
		Image javafx_logo = new Image(image_path);
		
		//image, x ,y, width, height
		gc.drawImage(javafx_logo, 40, 40, 600, 200);
	}
	
	public void addButtons(AnchorPane anchorpane) {
		Button button = new Button("Add");
	    AnchorPane.setTopAnchor(button, 10.0);
	    AnchorPane.setRightAnchor(button, 10.0);
//	    AnchorPane.getChildren().addAll(list, button);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}