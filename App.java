import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.io.IOException;
import java.util.ArrayList;


import javafx.scene.control.ListView;

import javafx.scene.text.Text; // Add this line


//import org.w3c.dom.Text;

public class App extends Application {
    Bank bank;
    User currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
<<<<<<< HEAD
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
=======
        try {
            bank = Bank.load("data.ser");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("data.ser not found, creating new database.");
            bank = new Bank();
        }
        primaryStage.setTitle("CS2043 Bank - Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));

        grid.setStyle("-fx-background-color: #FFFFFF;");

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
        usernameField.setStyle("-fx-background-color: #FFFFFF;"+
                "-fx-border-color: #CCCCCC;");
        grid.add(usernameField, 1, 1);

        // Password field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, FontPosture.ITALIC, 14));
        passwordLabel.setTextFill(Color.RED);
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: #FFFFF;"+
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
>>>>>>> 7f174f345a21db1376c500ac66c5da9a1e320e80
        loginButton.setOnAction(e -> {
            currentUser = bank.authenticate(usernameField.getText(), passwordField.getText());
            if (currentUser != null) {
                statusLabel.setText("Login successful! Welcome, " + currentUser.getName());
<<<<<<< HEAD
=======
                statusLabel.setTextFill(Color.GREEN);
>>>>>>> 7f174f345a21db1376c500ac66c5da9a1e320e80
                showAccountPage(primaryStage);
                primaryStage.close();
            } else {
                statusLabel.setText("Username or Password is incorrect. Please try again.");
<<<<<<< HEAD
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
            PasswordField registerPasswordField = new PasswordField();
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
=======
                statusLabel.setTextFill(Color.RED);
            }
        });

        // EventHandler for the Register button
        registerButton.setOnAction(e -> {
            RegistrationForm registrationForm = new RegistrationForm(bank);
            registrationForm.show();
        });
        
        // SAVES UPON CLOSING
        primaryStage.setOnCloseRequest(e -> {
            try {
                bank.save("data.ser");
            } catch (IOException ex) {
                System.err.println("ERROR: Bank not saved!!! " + ex.getMessage());
            }
        });


        // Set the scene and show the stage
        Scene scene = new Scene(grid, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
>>>>>>> 7f174f345a21db1376c500ac66c5da9a1e320e80
    }

    private void showAccountPage(Stage primaryStage) {
        Stage accountStage = new Stage();
        accountStage.setTitle("Account Page");
<<<<<<< HEAD
        Label welcomeLabel = new Label("Welcome, " + currentUser.getName());
        Label balanceLabel = new Label("Balance: $" + String.format("%.2f", currentUser.balance()));
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button transferButton = new Button("Transfer");
        Button logoutButton = new Button("Log Out");

        // Logout action
        logoutButton.setOnAction(e -> {
            accountStage.close();
            start(new Stage()); // Restart login page
        });

        // Layout
        VBox layout = new VBox(10, welcomeLabel, balanceLabel, depositButton, withdrawButton, transferButton, logoutButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 250);
        accountStage.setScene(scene);
        accountStage.show();
    }
}
=======

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
        Label balanceLabel = new Label("Balance: $" + String.format("%.2f", currentUser.getAccounts().get(0).getBalance()));
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
            try {
                bank.save("data.ser");
            } catch (IOException ex) {
                System.err.println("ERROR: Bank not saved!!! " + ex.getMessage());
            }
            accountStage.close();
            start(new Stage());
        });
        
        // SAVE BANK ACCOUNT BEFORE CLOSING.
        primaryStage.setOnCloseRequest(e -> {
            try {
                bank.save("data.ser");
            } catch (IOException ex) {
                System.err.println("ERROR: Bank not saved!!! " + ex.getMessage());
            }
        });

        //transaction history 
        transactionHistoryButton.setOnAction(e->{
           showTransactionHistory(accountStage);
        });

        // Set the scene and show the stage
        Scene scene = new Scene(grid, 400, 300);
        accountStage.setScene(scene);
        accountStage.show();
    }
    private void showTransactionHistory(Stage primaryStage) {
        Stage historyStage = new Stage();
        historyStage.setTitle("Transaction History");
    
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #f4f4f4;");
    
        ArrayList<Transaction> transactions = currentUser.getAccounts().get(0).getTransactions();
        ListView<String> transactionList = new ListView<>();
        for (Transaction t : transactions) {
            transactionList.getItems().add(t.toString()); // Assuming Transaction has a toString() method
        }

        grid.add(transactionList, 0, 0);
        Button closeButton = new Button("Close");
        closeButton.setStyle( "-fx-background-color: #0078d7;"+
                "-fx-text-fill: white;"+
                "-fx-font-size: 14; "+
                "-fx-font-weight: bold;"+
                "-fx-border-radius: 5;");
        grid.add(closeButton, 0, 2);

        closeButton.setOnAction(e->{
            try {
                bank.save("data.ser");
            } catch (IOException ex) {
                System.err.println("ERROR: Bank not saved!!! " + ex.getMessage());
            }
            historyStage.close();
        });
    
        Scene scene = new Scene(grid, 400, 300);
        historyStage.setScene(scene);
        historyStage.show();
    }
    
}
>>>>>>> 7f174f345a21db1376c500ac66c5da9a1e320e80
