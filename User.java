import java.util.ArrayList;

public class User {
  private String username;
  private String password;
  private String name;
  private String email;
  private String phone;
  private String address;
  private ArrayList<Account> accounts;
  
  public User(String username, String password, String name,
              String email, String phone, String address) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    accounts = new ArrayList<>();
    Account chequing = new Account();
    accounts.add(chequing);
  }
  
  public boolean authenticate(String username, String password) {
    if (this.username == username && this.password == password) {
      return true;
    }
    return false;
  }
}