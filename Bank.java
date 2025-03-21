import java.io.*;
import java.util.ArrayList;

public class Bank implements Serializable {
  private static final long serialVersionUID = 1L;
  private ArrayList<User> users;
  
  public Bank() {
    users = new ArrayList<>();
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
  
  public ArrayList<User> getUsers() {
    return users;
  }

  public ArrayList<Account> getAccounts() {
    ArrayList<Account> allAccounts = new ArrayList<>();
    for (User user : users) {
      allAccounts.addAll(user.getAccounts());
    }
    return allAccounts;
  }
  
  public static Bank load(String filename) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      Bank bank = (Bank) ois.readObject();
      System.out.println("Data loaded! Users: " + bank.getUsers().size() + " Accounts: " + bank.getAccounts().size());
      return bank;
    }
  }
  
  public void save(String filename) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
      oos.writeObject(this);
      System.out.println("Data saved! Users: " + users.size() + " Accounts: " + getAccounts().size());
    }
  }
}