import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.util.ArrayList;

public class App extends Application {
    Bank bank = new Bank();
    User currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("CS2043 Bank - Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));

        grid.setStyle("-fx-background-color: #fde0e0;");

        // Add a logo or title
        Text title = new Text("Welcome to CS2043 Bank");
        title.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, FontPosture.ITALIC, 26));
        title.setFill(Color.RED);
        grid.add(title, 0, 0, 2, 1);

        // Username field
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, FontPosture.ITALIC, 14));
        usernameLabel.setTextFill(Color.RED);
        grid.add(usernameLabel, 0, 1);

        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-background-color: #ffffff;"+
                "-fx-border-color: #cccccc;");
        grid.add(usernameField, 1, 1);

        // Password field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, FontPosture.ITALIC, 14));
        passwordLabel.setTextFill(Color.RED);
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: #fffff;"+
                "-fx-border-color: #cccccc;");
        grid.add(passwordField, 1, 2);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #FF0000;" +
                "-fx-text-fill: white; -fx-font-size: 16;"+
                "-fx-font-weight: bold;");
        grid.add(loginButton, 1, 3);

        // Register button
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #FF0000;" +
                "-fx-text-fill: white; -fx-font-size: 16;"+
                "-fx-font-weight: bold;");
        grid.add(registerButton, 1, 4);

        // Status label for login feedback
        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 12));
        statusLabel.setTextFill(Color.RED);
        grid.add(statusLabel, 1, 5);

        // EventHandler for the loginButton
        loginButton.setOnAction(e -> {
            currentUser = bank.authenticate(usernameField.getText(), passwordField.getText());
            if (currentUser != null) {
                statusLabel.setText("Login successful! Welcome, " + currentUser.getName());
                statusLabel.setTextFill(Color.GREEN);
                showAccountPage(primaryStage);
                primaryStage.close();
            } else {
                statusLabel.setText("Username or Password is incorrect. Please try again.");
                statusLabel.setTextFill(Color.RED);
            }
        });

        // EventHandler for the Register button
        registerButton.setOnAction(e -> {
            RegistrationForm registrationForm = new RegistrationForm(bank);
            registrationForm.show();
        });

        // Set the scene and show the stage
        Scene scene = new Scene(grid, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAccountPage(Stage primaryStage) {
        Stage accountStage = new Stage();
        accountStage.setTitle("Account Page");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #f4f4f4;");

        // Welcome message
        Text welcomeText = new Text("Welcome, " + currentUser.getName());
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        welcomeText.setFill(Color.DARKBLUE);
        grid.add(welcomeText, 0, 0, 2, 1);

        // Balance display
        Label balanceLabel = new Label("Balance: $" + String.format("%.2f", currentUser.getAccount().get(0).getBalance()));
        balanceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        balanceLabel.setTextFill(Color.DARKSLATEGRAY);
        grid.add(balanceLabel, 0, 1, 2, 1);

        // Buttons for account actions
        Button depositButton = new Button("Deposit");
        depositButton.setStyle( "-fx-background-color: #0078d7;"+
                "-fx-text-fill: white;"+
                "-fx-font-size: 14; "+
                "-fx-font-weight: bold;"+
                "-fx-border-radius: 5;");
        grid.add(depositButton, 0, 2);

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setStyle( "-fx-background-color: #0078d7;"+
                "-fx-text-fill: white;"+
                "-fx-font-size: 14; "+
                "-fx-font-weight: bold;"+
                "-fx-border-radius: 5;");
        grid.add(withdrawButton, 1, 2);

        Button transferButton = new Button("Transfer");
        transferButton.setStyle( "-fx-background-color: #0078d7;"+
                "-fx-text-fill: white;"+
                "-fx-font-size: 14; "+
                "-fx-font-weight: bold;"+
                "-fx-border-radius: 5;");
        grid.add(transferButton, 0, 3);

        Button transactionHistoryButton = new Button("Transaction History");
        transactionHistoryButton.setStyle( "-fx-background-color: #0078d7;"+
                "-fx-text-fill: white;"+
                "-fx-font-size: 14; "+
                "-fx-font-weight: bold;"+
                "-fx-border-radius: 5;");
        grid.add(transactionHistoryButton, 1, 3);

        Button logoutButton = new Button("Log Out");
        logoutButton.setStyle( "-fx-background-color: #FF0000;"+
                "-fx-text-fill: white;"+
                "-fx-font-size: 14; "+
                "-fx-font-weight: bold;"+
                "-fx-border-radius: 5;");
        grid.add(logoutButton, 0, 4, 2, 1);

        // Logout action
        logoutButton.setOnAction(e -> {
            accountStage.close();
            start(new Stage());
        });

        // Set the scene and show the stage
        Scene scene = new Scene(grid, 400, 300);
        accountStage.setScene(scene);
        accountStage.show();
    }
}