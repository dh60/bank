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
  }
}