import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }
  
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Login");
    
    // Create UI elements
    Label usernameLabel = new Label("Username:");
    TextField usernameField = new TextField();
    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();
    Button loginButton = new Button("Login");
    Button registerButton = new Button("Register");
    Label statusLabel = new Label();
    
    // Layout the elements
    HBox buttonBox = new HBox(10, loginButton, registerButton);
    VBox vbox = new VBox(10, usernameLabel, usernameField, passwordLabel, passwordField, buttonBox, statusLabel);
    Scene scene = new Scene(vbox, 640, 480);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    // Creating a dummy account for testing purposes.
    User testUser = new User("dh", "greatpassword", "Daniel Hogarth", "d.h@unb.ca", "555-555-5555", "265 Regent St.");
    
    // EventHandler for the loginButton. Sends to the User authenticate method.
    loginButton.setOnAction(e -> {
      boolean loginResult = testUser.authenticate(usernameField.getText(), passwordField.getText());
      statusLabel.setText(String.valueOf(loginResult));
    });
  }
}