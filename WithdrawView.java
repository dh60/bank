import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WithdrawView {
    private AccountService accountService;
    private Account account;
    private ChequingView parentView;

    public WithdrawView(AccountService accountService, Account account, ChequingView parentView) {
        this.accountService = accountService;
        this.account = account;
        this.parentView = parentView;
    }

   