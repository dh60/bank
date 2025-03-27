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

    public void show() {
        Stage stage = new Stage();
        
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Withdraw Money");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();
        amountField.setPromptText("Enter Amount");
        amountField.setMaxWidth(150);

        Button submitButton = new Button("Withdraw");
        submitButton.setMinWidth(100);


        layout.getChildren().addAll(titleLabel, amountLabel, amountField, submitButton);
    }