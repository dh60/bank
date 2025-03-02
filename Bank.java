import java.util.ArrayList;
import java.util.Serializable;

public class Bank implements Serializable {
  private ArrayList<User> users;
  private ArrayList<Account> accounts;
  
  public Bank() {
    users = new ArrayList<>();
    accounts = new ArrayList<>();
  }
}