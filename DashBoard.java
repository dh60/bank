
import javafx.application.Application;
import javafx.stage.Stage;

public class DashBoard extends Application {
    public void start(Stage stage) {
        Bank bank = Bank.loadFromFile("data.ser");
        BankService bankService = new BankService(bank);
        LoginView loginView = new LoginView(bankService, stage);
        loginView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}