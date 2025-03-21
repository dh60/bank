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

public class App extends Application {
    Bank bank;
    User currentUser;
    Label balanceLabel;
    Label messageLabel;
    TextField depositField;
    TextField withdrawField;
    Label statusLabel; // Added statusLabel as a class-level variable

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
        usernameField.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        grid.add(usernameField, 1, 1);

        // Password field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, FontPosture.ITALIC, 14));
        passwordLabel.setTextFill(Color.RED);
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        grid.add(passwordField, 1, 2);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> login(usernameField.getText(), passwordField.getText()));
        loginButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");
        grid.add(loginButton, 1, 3);

        // Register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> register());
        registerButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");
        grid.add(registerButton, 1, 4);

        // Status label for login feedback
        statusLabel = new Label(); // Initialize statusLabel
        statusLabel.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 12));
        statusLabel.setTextFill(Color.RED);
        grid.add(statusLabel, 1, 5);

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

    private void login(String username, String password) {
        System.out.println("Login attempt with username: " + username + ", password: " + password); // Debugging
        currentUser = bank.authenticate(username, password);
        if (currentUser != null) {
            statusLabel.setText("Login successful! Welcome, " + currentUser.getName());
            statusLabel.setTextFill(Color.GREEN);
            showAccountPage(new Stage());
        } else {
            statusLabel.setText("Username or Password is incorrect. Please try again.");
            statusLabel.setTextFill(Color.RED);
        }
    }

    private void register() {
        RegistrationForm registrationForm = new RegistrationForm(bank);
        registrationForm.show();
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
        balanceLabel = new Label("Balance: $" + String.format("%.2f", currentUser.getAccounts().get(0).getBalance()));
        balanceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        balanceLabel.setTextFill(Color.DARKSLATEGRAY);
        grid.add(balanceLabel, 0, 1, 2, 1);

        // Deposit field and button
        depositField = new TextField();
        depositField.setPromptText("Enter deposit amount");
        grid.add(depositField, 0, 2);

        Button depositButton = new Button("Deposit");
        depositButton.setOnAction(e -> deposit(depositField.getText()));
        depositButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold; -fx-border-radius: 5;");
        grid.add(depositButton, 1, 2);

        // Withdraw field and button
        withdrawField = new TextField();
        withdrawField.setPromptText("Enter withdraw amount");
        grid.add(withdrawField, 0, 3);

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setOnAction(e -> withdraw(withdrawField.getText()));
        withdrawButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold; -fx-border-radius: 5;");
        grid.add(withdrawButton, 1, 3);

        // Transfer button
        Button transferButton = new Button("Transfer");
        transferButton.setOnAction(e -> transfer());
        transferButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold; -fx-border-radius: 5;");
        grid.add(transferButton, 0, 4);

        // Transaction history button
        Button transactionHistoryButton = new Button("Transaction History");
        transactionHistoryButton.setOnAction(e -> showTransactionHistory(accountStage));
        transactionHistoryButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold; -fx-border-radius: 5;");
        grid.add(transactionHistoryButton, 1, 4);

        // Logout button
        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> logout(accountStage));
        logoutButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold; -fx-border-radius: 5;");
        grid.add(logoutButton, 0, 5, 2, 1);

        // Message label
        messageLabel = new Label();
        messageLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        messageLabel.setTextFill(Color.DARKRED);
        grid.add(messageLabel, 0, 6, 2, 1);

        // Set the scene and show the stage
        Scene scene = new Scene(grid, 400, 350);
        accountStage.setScene(scene);
        accountStage.show();
    }

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

    private void withdraw(String amount) {
        try {
            double withdrawAmount = Double.parseDouble(amount);
            if (withdrawAmount > 0) {
                double withdrawn = currentUser.getAccounts().get(0).withdraw(withdrawAmount);
                if (withdrawn > 0) {
                    balanceLabel.setText("Balance: $" + String.format("%.2f", currentUser.getAccounts().get(0).getBalance()));
                    showMessage("Withdrawal Successful", "Removed $" + withdrawAmount + " from your account.");
                } else {
                    showMessage("Insufficient Funds", "You cannot withdraw $" + withdrawAmount);
                }
            } else {
                showMessage("Invalid Amount", "Please enter a positive amount.");
            }
        } catch (NumberFormatException ex) {
            showMessage("Invalid Input", "Please enter a valid number.");
        }
    }

    private void transfer() {
        TextField recipientField = new TextField();
        recipientField.setPromptText("Recipient Account ID");
    
        TextField amountField = new TextField();
        amountField.setPromptText("Transfer Amount");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));
        grid.add(new Label("Recipient Account ID:"), 0, 0);
        grid.add(recipientField, 1, 0);
        grid.add(new Label("Amount:"), 0, 1);
        grid.add(amountField, 1, 1);

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Transfer");
        dialog.getDialogPane().setContent(grid);
    
        // Add transfer and cancel buttons
        ButtonType transferButton = new ButtonType("Transfer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(transferButton, ButtonType.CANCEL);
    
        // Handle the transfer action
        dialog.setResultConverter(buttonType -> {
            if (buttonType == transferButton) {
                try {
                    int recipientId = Integer.parseInt(recipientField.getText());
                    double amount = Double.parseDouble(amountField.getText());
    
                    if (amount <= 0) {
                        showMessage("Invalid Amount", "Please enter a positive amount.");
                        return null;
                    }
    
                    // Find the recipient account
                    Account recipientAccount = null;
                    for (User user : bank.getUsers()) {
                        for (Account account : user.getAccounts()) {
                            if (account.getID() == recipientId) {
                                recipientAccount = account;
                                break;
                            }
                        }
                        if (recipientAccount != null) break;
                    }
    
                    if (recipientAccount == null) {
                        showMessage("Invalid Recipient", "Recipient account not found.");
                        return null;
                    }
    
                    // Perform the transfer
                    Account senderAccount = currentUser.getAccounts().get(0);
                    if (senderAccount.transfer(recipientAccount, amount)) {
                        balanceLabel.setText("Balance: $" + String.format("%.2f", senderAccount.getBalance()));
                        showMessage("Transfer Successful", "Sent $" + amount + " to account " + recipientId);
                    } else {
                        showMessage("Transfer Failed", "Insufficient funds or invalid amount.");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid Input", "Please enter valid numbers.");
                }
            }
            return null;
        });
    
        // Show the dialog
        dialog.showAndWait();
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
            transactionList.getItems().add(t.toString());
        }

        grid.add(transactionList, 0, 0);
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold; -fx-border-radius: 5;");
        grid.add(closeButton, 0, 2);

        closeButton.setOnAction(e -> historyStage.close());

        Scene scene = new Scene(grid, 400, 300);
        historyStage.setScene(scene);
        historyStage.show();
    }

    private void logout(Stage accountStage) {
        try {
            bank.save("data.ser");
        } catch (IOException ex) {
            System.err.println("ERROR: Bank not saved!!! " + ex.getMessage());
        }
        accountStage.close();
        start(new Stage());
    }

    private void showMessage(String title, String message) {
        messageLabel.setText(title + ": " + message);
    }
}