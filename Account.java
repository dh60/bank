import java.util.ArrayList;
import java.io.Serializable;

public class Account implements Serializable {
  private static int nextID = 1;
  private final int accountID;
  private double balance;
  private ArrayList<Transaction> transactions;
  
  public Account() {
    accountID = nextID++;
    balance = 0;
    transactions = new ArrayList<>();
  }
  
  public int getID() {
    return accountID;
  }
  
  public double getBalance() {
    return balance;
  }
  
  public void deposit(double amount) {
    balance += amount;
  }
  
  public double withdraw(double amount) {
    if ((balance - amount) >= 0) { 
      balance -= amount;
      return amount;
    }
    return 0;
  }
  
  public ArrayList<Transaction> getTransactions() {
  	return transactions;
  }
}
