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



import javafx.scene.text.Text;
import javafx.scene.control.ListView;

public class App extends Application {
    Bank bank;
    User currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
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
        loginButton.setOnAction(e -> login(usernameField.getText(), passwordField.getText()));
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
        private void login(String username, String password) {
            currentUser = bank.authenticate(username, password);
            if (currentUser != null) {
                statusLabel.setText("Login successful! Welcome, " + currentUser.getName());
                statusLabel.setTextFill(Color.GREEN);
                showAccountPage(primaryStage);
                primaryStage.close();
            } else {
                statusLabel.setText("Username or Password is incorrect. Please try again.");
                statusLabel.setTextFill(Color.RED);
            }
        }

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
        welcomeText.setFill(Color.RED);
        grid.add(welcomeText, 0, 0, 2, 1);

        // Balance display
        Label balanceLabel = new Label("Balance: $" + String.format("%.2f", currentUser.getAccounts().get(0).getBalance()));
        balanceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        balanceLabel.setTextFill(Color.RED);
        grid.add(balanceLabel, 0, 1, 2, 1);

        // Buttons for account actions
        Button depositButton = new Button("Deposit");
        depositButton.setOnAction(e -> deposit(depositField.getText()));
        depositButton.setStyle( "-fx-background-color: #0078d7;"+
                "-fx-text-fill: white;"+
                "-fx-font-size: 14; "+
                "-fx-font-weight: bold;"+
                "-fx-border-radius: 5;");
        grid.add(depositButton, 0, 2);

        TextField depositField = new TextField();
        depositField.setPromptText("Enter deposit amount");
        grid.add(depositField, 1, 2);

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

        private void deposit(String amount) {
            try {
                double depositAmount = Double.parseDouble(amount);
                if (depositAmount > 0) {
                    currentUser.getAccounts().get(0).deposit(depositAmount);
                    balanceLabel.setText("Balance: $" + String.format("%.2f", currentUser.getAccounts().get(0).getBalance()));
                    showMessage("Deposit Successful", "Added $" + depositAmount + " to your account.");
                } else {
                    showMessage("Invalid Amount", "Please enter a positive amount.");
                }
            } catch (NumberFormatException ex) {
                showMessage("Invalid Input", "Please enter a valid number.");
            }
        }

    
        Scene scene = new Scene(grid, 400, 300);
        historyStage.setScene(scene);
        historyStage.show();
    }
    
}