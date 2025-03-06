import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountGUI extends Application {
    private Account account = new Account();
    private Label balanceLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Account Manager");

        // Balance Display
        balanceLabel = new Label("Balance: $" + account.getBalance());

        // Input Fields
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        // Buttons
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");

        // Button Actions
        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    account.deposit(amount);
                    updateBalance();
                } else {
                    showAlert("Enter a positive amount.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid number format.");
            }
            amountField.clear();
        });

        withdrawButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    double withdrawn = account.withdraw(amount);
                    if (withdrawn == 0) {
                        showAlert("Insufficient funds.");
                    }
                    updateBalance();
                } else {
                    showAlert("Enter a positive amount.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid number format.");
            }
            amountField.clear();
        });

        // Layout
        VBox layout = new VBox(10, balanceLabel, amountField, depositButton, withdrawButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Scene Setup
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + account.getBalance());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
