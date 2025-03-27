import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class TransferByIdView {
    private AccountService accountService;
    private Account fromAccount;
    private Object parentView;

    public TransferByIdView(AccountService accountService, Account fromAccount, Object parentView) {
        this.accountService = accountService;
        this.fromAccount = fromAccount;
        this.parentView = parentView;
    }

    public void show() {
		
		Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
		
		Label idLabel = new Label("Enter Account ID:");
		TextField idField = new TextField();
		Label amountLabel = new Label("Enter Amount:");
		TextField amountField = new TextField();
		Button submitButton = new Button("Submit");

		layout.getChildren().addAll(idLabel, idField, amountLabel, amountField, submitButton);

		submitButton.setOnAction(e -> {
            try {
                int toAccountId = Integer.parseInt(idField.getText());
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Amount must be greater than zero!").show();
                } else if (!accountService.transferById(fromAccount, toAccountId, amount)) {
                    new Alert(Alert.AlertType.ERROR, "Transfer failed: Check ID or balance.").show();
                } else {
                    System.out.println("TransferByIdView: Transfer completed, updating UI");
                    updateParent();
                    new Alert(Alert.AlertType.INFORMATION, "Transfer successful!").show();
                    stage.close();
                }
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid ID and amount.").show();
            }
        });
		
	    Scene scene = new Scene(layout, 200, 200);
        stage.setScene(scene);
        stage.setTitle("Transfer by ID");
        stage.show();
	}
}

private void updateParent() {
    if (parentView instanceof ChequingView) {
		((ChequingView) parentView).update(fromAccount);
        System.out.println("UI updated for account " + fromAccount.getID());
    }
}

    
