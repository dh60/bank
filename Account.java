import java.util.ArrayList;
import java.io.Serializable;

public class Account implements Serializable {
  private static int nextID = 100;
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
<<<<<<< HEAD
    transactions.add(new Transaction(-1, accountID, amount));
=======
    transactions.add(new Transaction(0, accountID, amount));//sender is 0 because we are the sender
>>>>>>> 7f174f345a21db1376c500ac66c5da9a1e320e80
  }
  
  public double withdraw(double amount) {
    if ((balance - amount) >= 0) { 
      balance -= amount;
<<<<<<< HEAD
      transactions.add(new Transaction(accountID,-1, amount));
=======
      transactions.add(new Transaction(accountID, 0, amount));//sender is 0 because we are the reciever
      

>>>>>>> 7f174f345a21db1376c500ac66c5da9a1e320e80
      return amount;
    }
    return 0;
  }
<<<<<<< HEAD
  public ArrayList<Transaction> getTransaction(){
    return transactions;
  }
}
=======
  public boolean transfer(Account recipient,double amount){
    if(amount>0 && this.balance>=amount){
      this.balance-=amount;
      recipient.balance+=amount;

      Transaction transaction=new Transaction(this.accountID, recipient.accountID, amount);
      this.transactions.add(transaction);
            recipient.transactions.add(transaction);
            return true;
    }
    return false;
  }
  
  public ArrayList<Transaction> getTransactions() {
  	return transactions;
  }
}
>>>>>>> 7f174f345a21db1376c500ac66c5da9a1e320e80
