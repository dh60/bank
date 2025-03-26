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
}