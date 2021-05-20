package template;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.JSONManager;

public class PlayerMenu {

	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Player Menu");
		window.setWidth(400);
		window.setHeight(200);
		Label currentPlayerText = new Label();
		currentPlayerText.setText("Current Player: " + JSONManager.getPlayerName());

		// CHANGE PLAYER LINE
		HBox changePlayer = new HBox(10);

		Label changePlayerText = new Label("Change Player to: ");

		ComboBox comboBox1 = new ComboBox();
		setDropDown(comboBox1);

		Button confirmButton = new Button("Confirm");
		confirmButton.setOnAction(e -> {
			JSONManager.changePlayer((String) comboBox1.getValue());
			currentPlayerText.setText("Current Player: " + JSONManager.getPlayerName());
			setDropDown(comboBox1);
		});

		changePlayer.getChildren().addAll(changePlayerText, comboBox1, confirmButton);
		changePlayer.setAlignment(Pos.CENTER);

		// NEW PLAYER LINE

		HBox newPlayer = new HBox(10);

		Label newPlayerText = new Label("New Player Name: ");

		TextField newPlayerName = new TextField();

		Button newButton = new Button("New");
		newButton.setOnAction(e -> {
			// TODO IF ELSE FOR BLANK NAME
			String playerName = newPlayerName.getText();
			if (!JSONManager.isNameExist(playerName) && !playerName.equals("")) {
				JSONManager.addNewPlayer(playerName);
				JSONManager.changePlayer(playerName);
				currentPlayerText.setText("Current Player: " + JSONManager.getPlayerName());
			}
			setDropDown(comboBox1);
			newPlayerName.clear();
		});
		newPlayer.getChildren().addAll(newPlayerText, newPlayerName, newButton);
		newPlayer.setAlignment(Pos.CENTER);

		// CHANGE NAME
		HBox changePlayerNameBox = new HBox(10);

		Label changePlayerNameText = new Label("Change Name");

		TextField changePlayerName = new TextField();

		Button changeButton = new Button("Change");
		changeButton.setOnAction(e -> {
			if (!JSONManager.isNameExist(changePlayerName.getText()) && !newPlayerName.getText().equals("")) {
				JSONManager.setPlayerName(changePlayerName.getText());
				currentPlayerText.setText("Current Player: " + JSONManager.getPlayerName());
			}
			newPlayerName.clear();
		});

		changePlayerNameBox.getChildren().addAll(changePlayerNameText, changePlayerName, changeButton);
		changePlayerNameBox.setAlignment(Pos.CENTER);
		// DELETE
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> {
			// TODO MAKE ANOTHER WINDOW TO COMFIRM DELETION
			System.out.println("Pressed Delete");
		});

		// Set up everything
		VBox layout = new VBox(10);
		layout.getChildren().addAll(currentPlayerText, changePlayer, newPlayer, changePlayerNameBox);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}

	public static void setDropDown(ComboBox comboBox) {
		comboBox.getItems().clear();
		System.out.println(JSONManager.getJsonNameList().size());
		for (int i = 0; i < JSONManager.getJsonNameList().size(); i++) {
			comboBox.getItems().add(JSONManager.getJsonNameList().get(i));
		}
	}

}
