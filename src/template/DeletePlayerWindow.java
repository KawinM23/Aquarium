package template;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.JSONManager;

public class DeletePlayerWindow {
	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("ARE YOU SURE?");
		window.setWidth(450);
		window.setHeight(200);

		Label warningText = new Label();
		warningText.setText("WARNING: ALL YOUR PROGRESS WILL BE LOST!");

		Label instructionText = new Label();
		instructionText.setText("Type the player name ( " + JSONManager.getPlayerName()
				+ " ) below and click \"Delete\" to delete this player");

		TextField confirmationField = new TextField();

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> {
			if (confirmationField.getText().equals(JSONManager.getPlayerName())) {
				JSONManager.changePlayer(JSONManager.getJsonNameList().get(0));
				PlayerMenu.setCurrentPlayer(JSONManager.getPlayerName());
				JSONManager.removePlayer(confirmationField.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				PlayerMenu.setDropDown(PlayerMenu.getCurrentComboBox());
				alert.setTitle("Deleted Successfully");
				alert.setHeaderText(null);
				alert.setContentText("Player " + confirmationField.getText() + " removed from database.");
				alert.showAndWait();
				window.close();
			} else if (!confirmationField.getText().equals(JSONManager.getPlayerName())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Player name doesn't match. Please try again.");
				alert.showAndWait();
			}
		});

		// Set up everything
		VBox layout = new VBox(10);
		layout.getChildren().addAll(warningText, instructionText, confirmationField, deleteButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}

}
