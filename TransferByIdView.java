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
		Label amountLabel = new Label("Enter Amount:");
	}
}