import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }
  
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Login");
    primaryStage.show();
  }
}