

import java.util.ArrayList;
import java.io.*;

public class Bank implements Serializable {
  private ArrayList<User> users;
  private int nextID;  

  public Bank() {
    users = new ArrayList<>();
    nextID = 100; 
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public int getNextID() {
    return nextID++;
  }

  public void saveToFile(String filename) throws IOException {
    try {
      File file = new File(filename);
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
      out.writeObject(this);
      out.close();
      System.out.println("Data saved to: " + file.getAbsolutePath());
    } catch (IOException e) {
      System.out.println("Error saving data to " + filename + ": " + e.getMessage());
      throw e;
    }
  }

  public static Bank loadFromFile(String filename) {
    try {
      File file = new File(filename);
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
      Bank bank = (Bank) in.readObject();
      in.close();
      System.out.println("Data loaded from: " + file.getAbsolutePath());
      return bank;
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Could not load " + filename + ", starting new bank");
      return new Bank();
    }
  }
}