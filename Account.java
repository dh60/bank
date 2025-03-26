
import java.util.ArrayList;
import java.io.Serializable;

public class Account implements Serializable {
  private final int accountID;
  private double balance;
  private ArrayList<Transaction> transactions;

  public Account(int id) {  // Accepts int parameter
    this.accountID = id;
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
    transactions.add(new Transaction(0, accountID, amount));
  }

  public double withdraw(double amount) {
    if (balance >= amount) {
      balance -= amount;
      transactions.add(new Transaction(accountID, 0, amount));
      return amount;
    }
    return 0;
  }

  public boolean transfer(Account toAccount, double amount) {
    if (amount > 0 && balance >= amount) {
      balance -= amount;
      toAccount.balance += amount;
      Transaction transaction = new Transaction(accountID, toAccount.accountID, amount);
      transactions.add(transaction);
      toAccount.transactions.add(transaction);
      return true;
    }
    return false;
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public ArrayList<Transaction> getLatestTransactions() {
    int size = transactions.size();
    if (size <= 5) {
      return new ArrayList<>(transactions);
    }
    return new ArrayList<>(transactions.subList(size - 5, size));
  }
}