package template;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.JSONManager;

public class PlayerMenu {

	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Player Menu");
		window.setMinWidth(250);
		Label label = new Label();
		label.setText("Are you gay?");

		ComboBox comboBox = new ComboBox();
		System.out.println(JSONManager.getJsonNameList());
		for (int i = 0; i < JSONManager.getJsonNameList().size(); i++) {
			comboBox.getItems().add(JSONManager.getJsonNameList().get(i));
		}

		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, comboBox, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}

}
