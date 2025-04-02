import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistrationView {
    private Bank bank;
    private Stage stage;

    public RegistrationView(Bank bank, Stage stage) {
        this.bank = bank;
        this.stage = stage;
    }

    public void show() {
        // Create layout with padding for better spacing
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20)); // Add space around the edges

        // Title
        Label titleLabel = new Label("Create New Account");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        // Fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        usernameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(250);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Full Name");
        nameField.setMaxWidth(250);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");
        emailField.setMaxWidth(250);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter Phone Number");
        phoneField.setMaxWidth(250);

        TextField addressField = new TextField();
        addressField.setPromptText("Enter Address");
        addressField.setMaxWidth(250);

        // Button
        Button submitButton = new Button("Submit");
        submitButton.setMinWidth(100);

        // Add all elements to layout
        layout.getChildren().addAll(titleLabel, usernameField, passwordField, nameField,
                emailField, phoneField, addressField, submitButton);
        
        // Set up the scene
        Scene scene = new Scene(layout, 280, 350);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Bank Registration");
        newStage.show();
        
        // Submit button action
        submitButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            // Check for empty fields
            if (username.isEmpty() || password.isEmpty() || name.isEmpty() ||
                    email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "All fields must be filled").show();
            } else {
                try {
                    User newUser = new User(username, password, name, email, phone, address);
                    bank.addUser(newUser);
                    newStage.close();
                    LoginView loginView = new LoginView(bank, stage);
                    loginView.show();
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "Error during registration. Try again.").show();
                }
            }
        });

    }
}