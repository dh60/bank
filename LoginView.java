import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private BankService bankService;
    private Stage stage;

    public LoginView(BankService bankService, Stage stage) {
        this.bankService = bankService;
        this.stage = stage;
    }

    public void show() {
        // Create layout with padding for better spacing
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20)); // Add space around the edges

        // Title
        Label titleLabel = new Label("Login to Your Account");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        // Fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        usernameField.setMaxWidth(200); // Limit width for neatness

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(200);

        // Buttons
        Button loginButton = new Button("Login");
        loginButton.setMinWidth(100); // Make button wider
        Button registerButton = new Button("Register");
        registerButton.setMinWidth(100);

        // Add all elements to layout
        layout.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, registerButton);

        // Login button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter both username and password").show();
            } else {
                User user = bankService.login(username, password);
                if (user != null) {
                    ChequingView chequingView = new ChequingView(bankService, user, stage);
                    chequingView.show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Incorrect username or password").show();
                }
            }
        });

        // Register button action
        registerButton.setOnAction(e -> {
            RegistrationView regView = new RegistrationView(bankService, stage);
            regView.show();
        });

        // Set up the scene
        Scene scene = new Scene(layout, 240, 240);
        stage.setScene(scene);
        stage.setTitle("Bank Login");
        stage.show();
    }
}