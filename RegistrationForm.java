import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class RegistrationForm {
    private Bank bank;

    public RegistrationForm(Bank bank) {
        this.bank = bank;
    }

    public void show() {
        Stage registerStage = new Stage();
        registerStage.setTitle("User Registration");

        GridPane registerGrid = new GridPane();
        registerGrid.setAlignment(Pos.CENTER);
        registerGrid.setHgap(10);
        registerGrid.setVgap(10);
        registerGrid.setPadding(new Insets(25, 25, 25, 25));
        registerGrid.setStyle("-fx-background-color: #f4f4f4;");

        // Registration title
        Text registerTitle = new Text("Create a New Account");
        registerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        registerTitle.setFill(Color.DARKBLUE);
        registerGrid.add(registerTitle, 0, 0, 2, 1);

        // Registration fields
        Label registerUsernameLabel = new Label("Username:");
        registerUsernameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        registerUsernameLabel.setTextFill(Color.DARKSLATEGRAY);
        registerGrid.add(registerUsernameLabel, 0, 1);

        TextField registerUsernameField = new TextField();
        registerUsernameField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc;");
        registerGrid.add(registerUsernameField, 1, 1);

        Label registerPasswordLabel = new Label("Password:");
        registerPasswordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        registerPasswordLabel.setTextFill(Color.DARKSLATEGRAY);
        registerGrid.add(registerPasswordLabel, 0, 2);

        PasswordField registerPasswordField = new PasswordField();
        registerPasswordField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc;");
        registerGrid.add(registerPasswordField, 1, 2);

        Label registerNameLabel = new Label("Name:");
        registerNameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        registerNameLabel.setTextFill(Color.DARKSLATEGRAY);
        registerGrid.add(registerNameLabel, 0, 3);

        TextField registerNameField = new TextField();
        registerNameField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc;");
        registerGrid.add(registerNameField, 1, 3);

        Label registerEmailLabel = new Label("Email:");
        registerEmailLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        registerEmailLabel.setTextFill(Color.DARKSLATEGRAY);
        registerGrid.add(registerEmailLabel, 0, 4);

        TextField registerEmailField = new TextField();
        registerEmailField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc;");
        registerGrid.add(registerEmailField, 1, 4);

        Label registerPhoneLabel = new Label("Phone:");
        registerPhoneLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        registerPhoneLabel.setTextFill(Color.DARKSLATEGRAY);
        registerGrid.add(registerPhoneLabel, 0, 5);

        TextField registerPhoneField = new TextField();
        registerPhoneField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc;");
        registerGrid.add(registerPhoneField, 1, 5);

        Label registerAddressLabel = new Label("Address:");
        registerAddressLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        registerAddressLabel.setTextFill(Color.DARKSLATEGRAY);
        registerGrid.add(registerAddressLabel, 0, 6);

        TextField registerAddressField = new TextField();
        registerAddressField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc;");
        registerGrid.add(registerAddressField, 1, 6);

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");
        registerGrid.add(submitButton, 1, 7);

        // EventHandler for the Submit button
        submitButton.setOnAction(event -> {
            User newUser = new User(
                    registerUsernameField.getText(),
                    registerPasswordField.getText(),
                    registerNameField.getText(),
                    registerEmailField.getText(),
                    registerPhoneField.getText(),
                    registerAddressField.getText()
            );
            bank.addUser(newUser);
            registerStage.close();
        });

        // Set the scene and show the stage
        Scene registerScene = new Scene(registerGrid, 400, 450);
        registerStage.setScene(registerScene);
        registerStage.show();
    }
}