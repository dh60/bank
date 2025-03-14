import java.util.ArrayList;
import java.io.Serializable;

public class User {
  private String username;
  private String password;
  private String name;
  private String email;
  private String phone;
  private String address;
  private ArrayList<Account> accounts;
  private Account chequing;
  
  public User(String username, String password, String name,
              String email, String phone, String address) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    accounts = new ArrayList<>();
    chequing = new Account();
    accounts.add(chequing);
  }
  
  public String getUsername() {
    return username;
  }
  
  public String getName() {
    return name;
  }
  public double balance(){
    return chequing.getBalance();
  }
  
  public boolean authenticate(String username, String password) {
    return username.equals(this.username) && password.equals(this.password);
  }
}