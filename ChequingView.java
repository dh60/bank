import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.stage.Stage;

public class ChequingView {
    private Bank bank; 
    private User user; 
    private Stage stage;
    
    public ChequingView(Bank bank, User user, Stage stage) { 
        this.bank = bank;
        this.user = user;
        this.stage = stage; 
    }
    
    public void show() { 
        Account chequing = user.getAccounts().get(0);
        VBox mainLayout = new VBox(20); 
        mainLayout.setPadding(new Insets(15)); 
        VBox infoBox = new VBox(5);
        infoBox.setPadding(new Insets(10)); 
        infoBox.setStyle("-fx-border-color: blue; -fx-border-width: 1;"); 

        Label titleLabel = new Label("Chequing Account"); 
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); 

        Label nameLabel = new Label("Name: " + user.getName()); 
        Label idLabel = new Label("Account ID: " + chequing.getID()); 
        Label balanceLabel = new Label(String.format("Balance: $%.2f", chequing.getBalance())); 
        balanceLabel.setId("balanceLabel"); 
        balanceLabel.setStyle("-fx-font-size: 16;"); 

        infoBox.getChildren().addAll(titleLabel, nameLabel, idLabel, balanceLabel); 
        infoBox.setAlignment(Pos.TOP_LEFT); 

        VBox transactionBox = new VBox(5); 
        Label transactionsLabel = new Label("Recent Transactions"); 
        transactionsLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;"); 

        ListView<String> transactionList = new ListView<String>(); 
        transactionList.setPrefHeight(150); 
        updateTransactions(transactionList, chequing); 
        transactionBox.getChildren().addAll(transactionsLabel, transactionList); 

        HBox buttonBox = new HBox(10); 
        buttonBox.setAlignment(Pos.CENTER); 
        Button depositButton = new Button("Deposit"); 
        depositButton.setMinWidth(90); 
        Button withdrawButton = new Button("Withdraw"); 
        withdrawButton.setMinWidth(90); 
        Button transferButton = new Button("Transfer to Savings"); 
        transferButton.setMinWidth(100); 
        Button transferByIdButton = new Button("Transfer to ID"); 
        transferByIdButton.setMinWidth(100); 
        Button allTransactionsButton = new Button("All Transactions"); 
        allTransactionsButton.setMinWidth(100); 
        Button savingsButton = new Button("Savings"); 
        savingsButton.setMinWidth(90); 
        Button logoutButton = new Button("Logout"); 
        logoutButton.setMinWidth(90); 
  
        buttonBox.getChildren().addAll(depositButton, withdrawButton, transferByIdButton, transferButton,
                allTransactionsButton, savingsButton, logoutButton); 
        mainLayout.getChildren().addAll(infoBox, transactionBox, buttonBox); 

        depositButton.setOnAction(e -> new DepositView(chequing, this).show()); 
        withdrawButton.setOnAction(e -> new WithdrawView(chequing, this).show()); 
        transferButton.setOnAction(e -> new TransferView(chequing, user.getAccounts().get(1), this).show()); 
        transferByIdButton.setOnAction(e -> new TransferByIdView(bank, chequing, this).show()); 
        allTransactionsButton.setOnAction(e -> showAllTransactions(chequing)); 
        savingsButton.setOnAction(e -> new SavingsView(bank, user, stage).show()); 
        logoutButton.setOnAction(e -> new LoginView(bank, stage).show()); 

        Scene scene = new Scene(mainLayout, 800, 360); 
        stage.setScene(scene); 
        stage.setTitle("Chequing Dashboard"); 
        stage.show();
    }
    
    public void update(Account account) { 
        Label balanceLabel = (Label) stage.getScene().lookup("#balanceLabel"); 
        if (balanceLabel != null) { 
            balanceLabel.setText(String.format("Balance: $%.2f", account.getBalance())); 
            System.out.println("ChequingView: Updated balance to $" + account.getBalance() + " for ID " + account.getID()); 
        } else { 
            System.out.println("ChequingView: balanceLabel not found!"); 
        } 
        ListView<String> transactionList = (ListView<String>) stage.getScene().lookup("#transactionList"); 
        if (transactionList != null) { 
            updateTransactions(transactionList, account); 
        } 
    }
    
    private void updateTransactions(ListView<String> list, Account account) { 
        list.getItems().clear(); 
        for (Transaction t : account.getTransactions()) { 
            list.getItems().add(t.toString());
        } 
        list.setId("transactionList"); 
    }
    
    private void showAllTransactions(Account account) { 
        Stage newStage = new Stage(); 
        VBox transactionLayout = new VBox(10); 
        transactionLayout.setPadding(new Insets(10)); 

        Label title = new Label("All Transactions"); 
        title.setStyle("-fx-font-size: 16; -fx-font-weight: bold;"); 
        ListView<String> allList = new ListView<String>(); 
        for (Transaction t : account.getTransactions()) { 
            allList.getItems().add(t.toString()); 
        } 
        allList.setPrefHeight(320);
        
        transactionLayout.getChildren().addAll(title, allList); 
        Scene scene = new Scene(transactionLayout, 800, 360); 
        newStage.setScene(scene); 
        newStage.setTitle("All Transactions"); 
        newStage.show(); 
    }
}