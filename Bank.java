import java.io.*;
import java.util.ArrayList;

public class Bank implements Serializable {
  private ArrayList<User> users;
  private transient String filename;
  
  public Bank(String filename) {
    this.filename = filename;
    users = new ArrayList<>();
  }
  
  public void addUser(User newUser) {
    users.add(newUser);
    for (Account account : newUser.getAccounts()) {
      account.setOnChangeCallback(this::save);
    }
    save();
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
  
  public Account getAccountByID(int id) {
    for (User user : users) {
        for (Account account : user.getAccounts()) {
            if (account.getID() == id) {
                return account;
            }
        }
    }
    return null;
  }
  
  public static Bank load(String filename) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      Bank bank = (Bank) ois.readObject();
      bank.filename = filename;
      int highestID = 100;
      for (User user : bank.users) {
        for (Account account : user.getAccounts()) {
          if (account.getID() >= highestID) {
            highestID = account.getID() + 1;
          }
        }
      }
      Account.setNextID(highestID);
      for (User user : bank.users) {
        for (Account account : user.getAccounts()) {
          account.setOnChangeCallback(bank::save);
        }
      }
      System.out.println("Data loaded! Users: " + bank.getUsers().size());
      return bank;
    }
    catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading file: " + e.getMessage());
      System.out.println("Creating new bank.");
      Bank bank = new Bank(filename);
      return bank;
    }
  }
  
  public void save() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
      oos.writeObject(this);
      System.out.println("Data saved!");
    }
    catch (IOException ioe) {
      System.err.println("Error saving file: " + ioe.getMessage());
    }
  }
}