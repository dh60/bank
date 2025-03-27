import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.stage.Stage; 
public class ChequingView {
    private BankService bankService; 
    private AccountService accountService; 
    private User user; 
    private Stage stage;
    public ChequingView(BankService bankService, User user, Stage stage) { 
        this.bankService = bankService; 
        this.accountService = new AccountService(bankService.getBank()); 
        this.user = user; 
        this.stage = stage; 
    } 
    public void show() { 
        Account chequing = user.getAccounts().get(0);
        VBox mainLayout = new VBox(20); 
        mainLayout.setPadding(new Insets(15)); 
        VBox infoBox = new VBox(5); 
    }    
}
