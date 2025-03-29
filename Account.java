import java.util.ArrayList;
import java.io.Serializable;

public class Account implements Serializable {
  private static int nextID = 100;
  private final int accountID;
  private double balance;
  private ArrayList<Transaction> transactions;
  private transient Runnable onChangeCallback;
  
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
    transactions.add(new Transaction(0, accountID, amount));
    notifyChange();
  }
  
  public double withdraw(double amount) {
    if ((balance - amount) >= 0) { 
      balance -= amount;
      transactions.add(new Transaction(accountID, 0, amount));
      notifyChange();
      return amount;
    }
    return 0;
  }
  
  public boolean transfer(Account recipient, double amount){
    if(amount>0 && this.balance>=amount){
      this.balance-=amount;
      recipient.balance+=amount;

      Transaction transaction=new Transaction(this.accountID, recipient.accountID, amount);
      this.transactions.add(transaction);
      recipient.transactions.add(transaction);
      notifyChange();
      recipient.notifyChange();
      return true;
    }
    return false;
  }
  
  public ArrayList<Transaction> getTransactions() {
  	return transactions;
  }
  
  public static void setNextID(int id){
    nextID = id;
  }
  
  public void setOnChangeCallback(Runnable callback) {
    this.onChangeCallback = callback;
  }

  private void notifyChange() {
    if (onChangeCallback != null) {
      onChangeCallback.run();
    }
  }
}
