import java.util.ArrayList;
import java.io.Serializable;

public class User implements Serializable {
  private String username;
  private String password;
  private String name;
  private String email;
  private String phone;
  private String address;
  private ArrayList<Account> accounts;

  public User(String username, String password, String name, String email, String phone, String address, Bank bank) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    accounts = new ArrayList<>();
    accounts.add(new Account(bank.getNextID()));        
    accounts.add(new SavingsAccount(bank.getNextID())); 
  }

  public String getUsername() {
    return username;
  }

  public String getName() {
    return name;
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

  public ArrayList<Account> getAccounts() {
    return accounts;
  }
}