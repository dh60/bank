import java.util.ArrayList;
import java.io.Serializable;

public class Bank implements Serializable {
  private static final long serialVersionUID = 1L;
  private ArrayList<User> users;
  private ArrayList<Account> accounts;
  
  public Bank() {
    users = new ArrayList<>();
    accounts = new ArrayList<>();
  }
  
  public void addUser(User newUser) {
    users.add(newUser);
  }
  
  public User authenticate(String username, String password) {
    for (User curUser : users) {
      if (username.equals(curUser.getUsername()) && curUser.authenticate(username, password)) {
        return curUser;
      }
    }
    return null;
  }
}