import javafx.scene.Scene; 

import javafx.scene.control.*; 

import javafx.scene.layout.VBox; 

import javafx.stage.Stage; 

  

public class RequestView { 

    private AccountService accountService; 

    private Account fromAccount; // Chequing 

    private Account toAccount;   // Savings 

    private Object parentView; 

  

    public RequestView(AccountService accountService, Account fromAccount, Account toAccount, Object parentView) { 

        this.accountService = accountService; 

        this.fromAccount = fromAccount; 

        this.toAccount = toAccount; 

        this.parentView = parentView; 

    } 
    public void show() { 

        Stage stage = new Stage(); 

        VBox layout = new VBox(10); 

  

        Label label = new Label("Request Amount:"); 

        TextField amountField = new TextField(); 

        Button submitButton = new Button("Submit"); 

  

        layout.getChildren().addAll(label, amountField, submitButton);
        Scene scene = new Scene(layout, 200, 150); 

        stage.setScene(scene); 

        stage.setTitle("Request from Chequing"); 

        stage.show();  
    }
}