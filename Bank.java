import java.util.ArrayList;
import java.io.Serializable;

public class Bank implements Serializable {
  private ArrayList<User> users;
  private ArrayList<Account> accounts;
  
  public Bank() {
    users = new ArrayList<>();
    accounts = new ArrayList<>();
  }
  
  public void addUser(User newUser) {
    users.add(newUser);
  }
}