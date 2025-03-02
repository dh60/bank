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
  
  public User authenticate(String username, String password) {
    for (User user : users) {
      if (username.equals(user.getUsername()) && user.authenticate(username, password)) {
        return user;
      }
    }
    return null;
  }
}