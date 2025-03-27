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

        // Main layout with padding and centered alignment
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
	}

}
