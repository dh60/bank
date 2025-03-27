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
	
	
}
