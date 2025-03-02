import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
    Bank bank = new Bank();
    User currentUser;

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
    VBox vbox = new VBox(10,
      usernameLabel, usernameField,
      passwordLabel, passwordField,
      buttonBox, statusLabel
    );
    Scene scene = new Scene(vbox, 320, 160);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    // Creating a dummy Bank for testing purposes.
    
    // EventHandler for the loginButton. Sends to the Bank authenticate method, sets status to True or False.
    loginButton.setOnAction(e -> {
      currentUser = bank.authenticate(usernameField.getText(), passwordField.getText());
      if (currentUser != null) {
        statusLabel.setText("Login successful! Welcome, " + currentUser.getName());
      }
      else {
        statusLabel.setText("Username or Password is incorrect. Please try again.");
      }
    });
    
    // EventHandler for the Registration. CREATES A NEW STAGE.
    registerButton.setOnAction(e -> {
      Stage registerStage = new Stage();
      registerStage.setTitle("User Registration");
      
      // Create UI Elements
      Label registerUsernameLabel = new Label("Username:");
      TextField registerUsernameField = new TextField();
      Label registerPasswordLabel = new Label("Password:");
      TextField registerPasswordField = new TextField();     
      Label registerNameLabel = new Label("Name:");
      TextField registerNameField = new TextField();
      Label registerEmailLabel = new Label("Email:");
      TextField registerEmailField = new TextField();
      Label registerPhoneLabel = new Label("Phone:");
      TextField registerPhoneField = new TextField();
      Label registerAddressLabel = new Label("Address:");
      TextField registerAddressField = new TextField();
      Button submitButton = new Button("Submit");
      
      // Layout elements
      VBox registerVbox = new VBox(10,
        registerUsernameLabel, registerUsernameField, 
        registerPasswordLabel, registerPasswordField, 
        registerNameLabel, registerNameField,
        registerEmailLabel, registerEmailField,
        registerPhoneLabel, registerPhoneField,
        registerAddressLabel, registerAddressField,
        submitButton
      );

      // Create window for Registration and put the layout in it.
      Scene registerScene = new Scene(registerVbox, 320, 420);
      registerStage.setScene(registerScene);
      registerStage.show();
      
      // EventHandler for the Submit button. Creates new User and adds it to the Bank user list.
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
    });
  }
}