import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DepositView {
    private AccountService accountService;
    private Account account;
    private Object parentView;

    public DepositView(AccountService accountService, Account account, Object parentView) {
        this.accountService = accountService;
        this.account = account;
        this.parentView = parentView;
    }

    public void show() {
        Stage stage = new Stage();
        
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);


        Label titleLabel = new Label("Deposit Money");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        
        Label amountLabel = new Label("Enter Amount:");
        TextField amountField = new TextField();
        amountField.setPromptText("e.g., 50.00");
        amountField.setMaxWidth(150);
        
        Button submitButton = new Button("Deposit");
        submitButton.setMinWidth(100);
        
        layout.getChildren().addAll(titleLabel, amountLabel, amountField, submitButton);
        


        Scene scene = new Scene(layout, 250, 200);
        stage.setScene(scene);
        stage.setTitle("Deposit");
        stage.show();
    }
    
    }
}