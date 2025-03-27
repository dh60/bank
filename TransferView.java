import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TransferView {
    private AccountService accountService;
    private Account fromAccount;
    private Account toAccount;
    private Object parentView;

    public TransferView(AccountService accountService, Account fromAccount, Account toAccount, Object parentView) {
        this.accountService = accountService;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.parentView = parentView;
    }
	
	public void show() {
        Stage stage = new Stage();

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
		
        Label titleLabel = new Label("Transfer Money");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
		
        Label amountLabel = new Label("Enter Amount:");
        TextField amountField = new TextField();
        amountField.setPromptText("e.g., 50.00");
        amountField.setMaxWidth(150);
		
        Button submitButton = new Button("Transfer");
        submitButton.setMinWidth(100);
		
        layout.getChildren().addAll(titleLabel, amountLabel, amountField, submitButton);
		
        submitButton.setOnAction(e -> {
            String input = amountField.getText().trim();
            if (input.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter an amount!").show();
            } else {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount <= 0) {
                        new Alert(Alert.AlertType.ERROR, "Amount must be greater than zero!").show();
                    } else if (accountService.transfer(fromAccount, toAccount, amount)) {
                        new Alert(Alert.AlertType.INFORMATION, "Transfer successful!").show();
                        updateParent();
                        stage.close();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Not enough money in the account!").show();
                    }
                } catch (NumberFormatException ex) {
                    new Alert(Alert.AlertType.ERROR, "Invalid amount! Use numbers only (e.g., 50.00).").show();
                }
            }
        });
		
        Scene scene = new Scene(layout, 250, 200);
        stage.setScene(scene);
        stage.setTitle("Transfer");
        stage.show();
	}
		
	private void updateParent() {
        if (parentView instanceof ChequingView) {
            ((ChequingView) parentView).update(fromAccount);
        } else if (parentView instanceof SavingsView) {
            ((SavingsView) parentView).update(fromAccount);
        }
    }

}
