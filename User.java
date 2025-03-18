import java.util.ArrayList;
import java.io.*;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private String username;
  private String password;
  private String gender;
  private String name;
  private String email;
  private String phone;
  private String address;
  private ArrayList<Account> accounts;
  
  public User(String username, String password, String name,
              String email, String phone, String address) {
    this.username = username;
    this.password = password;
	  this.gender = gender;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    accounts = new ArrayList<>();
    Account chequing = new Account();
    accounts.add(chequing);
  }
  
  public String getUsername() {
    return username;
  }
  
  public String getName() {
    return name;
  }
  
  public String getGender() {
    return gender;
  }
  
  public boolean authenticate(String username, String password) {
    if (username.equals(this.username) && password.equals(this.password)) {
      return true;
    }
    return false;
  }
  
  public ArrayList<Account> getAccounts() {
  	return accounts;
  }

}
