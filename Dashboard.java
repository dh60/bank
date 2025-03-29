import javafx.application.Application;
import javafx.stage.Stage;

public class Dashboard extends Application {
    public void start(Stage stage) {
        Bank bank = Bank.load("data.ser");
        LoginView loginView = new LoginView(bank, stage);
        loginView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
