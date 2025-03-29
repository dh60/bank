
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WithdrawView {
    private Account account;
    private ChequingView parentView;

    public WithdrawView(Account account, ChequingView parentView) {
        this.account = account;
        this.parentView = parentView;
    }

    public void show() {
        Stage stage = new Stage();

        // Main layout with padding and centered alignment
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        // Title
        Label titleLabel = new Label("Withdraw Money");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Amount field
        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();
        amountField.setPromptText("Enter Amount");
        amountField.setMaxWidth(150);

        // Submit button
        Button submitButton = new Button("Withdraw");
        submitButton.setMinWidth(100);

        // Add elements to layout
        layout.getChildren().addAll(titleLabel, amountLabel, amountField, submitButton);

        // Submit button action
        submitButton.setOnAction(e -> {
            String amountText = amountField.getText().trim();

            if (amountText.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter an amount!").show();
            } else {
                try {
                    double amount = Double.parseDouble(amountText);
                    if (amount <= 0) {
                        new Alert(Alert.AlertType.ERROR, "Amount must be greater than zero!").show();
                    } else if (account.withdraw(amount) > 0) {
                        parentView.update(account);
                        new Alert(Alert.AlertType.INFORMATION, "Withdrawal successful!").show();
                        stage.close();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Not enough money in account!").show();
                    }
                } catch (NumberFormatException ex) {
                    new Alert(Alert.AlertType.ERROR, "Invalid amount: Use numbers only.").show();
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "An error occurred. Please try again.").show();
                }
            }
        });

        // Set up the scene
        Scene scene = new Scene(layout, 250, 200);
        stage.setScene(scene);
        stage.setTitle("Withdraw");
        stage.show();
    }
}