import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SavingsView {
    private BankService bankService;
    private AccountService accountService;
    private User user;
    private Stage stage;

    public SavingsView(BankService bankService, User user, Stage stage) {
        this.bankService = bankService;
        this.accountService = new AccountService(bankService.getBank());
        this.user = user;
        this.stage = stage;
    }

    public void show() {
        SavingsAccount savings = (SavingsAccount) user.getAccounts().get(1);

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(15));

        VBox infoBox = new VBox(5);
        infoBox.setPadding(new Insets(10));
        infoBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");

        Label titleLabel = new Label("Savings Account");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Label nameLabel = new Label("Name: " + user.getName());
        Label idLabel = new Label("Account ID: " + savings.getID());
        Label balanceLabel = new Label("Balance: $" + savings.getBalance());
        balanceLabel.setId("balanceLabel");
        balanceLabel.setStyle("-fx-font-size: 16;");
        Label interestLabel = new Label("Interest Rate: " + savings.getInterestRate() + "%");

        infoBox.getChildren().addAll(titleLabel, nameLabel, idLabel, balanceLabel, interestLabel);
        infoBox.setAlignment(Pos.TOP_LEFT);

        VBox transactionBox = new VBox(5);
        Label transactionsLabel = new Label("Recent Transactions");
        transactionsLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        ListView<String> transactionList = new ListView<>();
        transactionList.setPrefHeight(150);
        updateTransactions(transactionList, savings);

        transactionBox.getChildren().addAll(transactionsLabel, transactionList);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button depositButton = new Button("Deposit");
        depositButton.setMinWidth(100);
        Button transferButton = new Button("To Chequing");
        transferButton.setMinWidth(100);
        Button requestButton = new Button("Request from Chequing");
        requestButton.setMinWidth(100);
        Button allTransactionsButton = new Button("All Transactions");
        allTransactionsButton.setMinWidth(100);
        Button chequingButton = new Button("Go to Chequing");
        chequingButton.setMinWidth(100);
        Button logoutButton = new Button("Logout");
        logoutButton.setMinWidth(100);

        buttonBox.getChildren().addAll(depositButton, transferButton, requestButton,
                allTransactionsButton, chequingButton, logoutButton);

        mainLayout.getChildren().addAll(infoBox, transactionBox, buttonBox);

        depositButton.setOnAction(e -> new DepositView(accountService, savings, this).show());
        transferButton.setOnAction(e -> new TransferView(accountService, savings, user.getAccounts().get(0), this).show());
        requestButton.setOnAction(e -> new RequestView(accountService, user.getAccounts().get(0), savings, this).show());
        allTransactionsButton.setOnAction(e -> showAllTransactions(savings));
        chequingButton.setOnAction(e -> new ChequingView(bankService, user, stage).show());
        logoutButton.setOnAction(e -> new LoginView(bankService, stage).show());

        Scene scene = new Scene(mainLayout, 800, 360);
        stage.setScene(scene);
        stage.setTitle("Savings Dashboard");
        stage.show();
    }

    public void update(Account account) {
        Label balanceLabel = (Label) stage.getScene().lookup("#balanceLabel");
        if (balanceLabel != null) {
            balanceLabel.setText("Balance: $" + account.getBalance());
        }
        ListView<String> transactionList = (ListView<String>) stage.getScene().lookup("#transactionList");
        if (transactionList != null) {
            updateTransactions(transactionList, account);
        }
    }

    private void updateTransactions(ListView<String> list, Account account) {
        list.getItems().clear();
        for (Transaction t : account.getLatestTransactions()) {
            list.getItems().add(t.toString(account.getID()));  // Pass account ID for perspective
        }
        list.setId("transactionList");
    }

    private void showAllTransactions(Account account) {
        Stage newStage = new Stage();
        VBox transactionLayout = new VBox(10);
        transactionLayout.setPadding(new Insets(10));

        Label title = new Label("All Transactions");
        title.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        ListView<String> allList = new ListView<>();
        for (Transaction t : account.getTransactions()) {
            allList.getItems().add(t.toString(account.getID()));  // Pass account ID for perspective
        }
        allList.setPrefHeight(200);

        transactionLayout.getChildren().addAll(title, allList);
        Scene scene = new Scene(transactionLayout, 350, 250);
        newStage.setScene(scene);
        newStage.setTitle("All Transactions");
        newStage.show();
    }
}