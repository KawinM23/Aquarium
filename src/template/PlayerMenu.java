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
import manager.SceneController;

public class PlayerMenu {
	static Label currentPlayerText = new Label();

	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Player Menu");
		window.setWidth(400);
		window.setHeight(300);
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
			SceneController.updatePlayerSettings();
			SceneController.changeScene("MainMenu");
		});

		changePlayer.getChildren().addAll(changePlayerText, comboBox1, confirmButton);
		changePlayer.setAlignment(Pos.CENTER);

		// NEW PLAYER LINE

		HBox newPlayer = new HBox(10);

		Label newPlayerText = new Label("New Player Name: ");

		TextField newPlayerName = new TextField();
		newPlayerName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 12)
				newPlayerName.setText(oldValue);
		});

		Button newButton = new Button("New");
		newButton.setOnAction(e -> {
			// TODO IF ELSE FOR BLANK NAME
			String playerName = newPlayerName.getText();
			if (!JSONManager.isNameExist(playerName) && !playerName.equals("")) {
				JSONManager.addNewPlayer(playerName);
				JSONManager.changePlayer(playerName);
				currentPlayerText.setText("Current Player: " + JSONManager.getPlayerName());
				SceneController.updatePlayerSettings();
				SceneController.changeScene("MainMenu");
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
		changePlayerName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 12)
				newPlayerName.setText(oldValue);
		});

		Button changeButton = new Button("Change");
		changeButton.setOnAction(e -> {
			if (!JSONManager.isNameExist(changePlayerName.getText()) && !changePlayerName.getText().equals("")) {
				System.out.println("CAME");
				JSONManager.setPlayerName(changePlayerName.getText());
				JSONManager.writeJSON();
				currentPlayerText.setText("Current Player: " + JSONManager.getPlayerName());
			}
			changePlayerName.clear();
		});

		changePlayerNameBox.getChildren().addAll(changePlayerNameText, changePlayerName, changeButton);
		changePlayerNameBox.setAlignment(Pos.CENTER);

		// DELETE
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> {
			// TODO MAKE ANOTHER WINDOW TO COMFIRM DELETION
			if (JSONManager.getJsonNameList().size() >= 1) {
				DeletePlayerWindow.display();
			} else if (JSONManager.getJsonNameList().size() <= 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText(
						"Cannot delete while there are no other players. Please create another player first.");
				alert.showAndWait();
			}
		});

		// BACK TO MENU
		Button backToMenuButton = new Button("Back to Menu");
		backToMenuButton.setOnAction(e -> {
			window.close();
		});

		// Set up everything
		VBox layout = new VBox(10);
		layout.getChildren().addAll(currentPlayerText, changePlayer, newPlayer, changePlayerNameBox, deleteButton,
				backToMenuButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}

	public static void setDropDown(ComboBox comboBox) {
		comboBox.getItems().clear();
		for (int i = 0; i < JSONManager.getJsonNameList().size(); i++) {
			comboBox.getItems().add(JSONManager.getJsonNameList().get(i));
		}
	}

	public static void setCurrentPlayer(String playerName) {
		currentPlayerText.setText("Current Player: " + playerName);
	}

}
