import java.time.Instant;

public class Transaction {
  private int sender;
  private int recipient;
  private double amount;
  private String date;
  
  public Transaction(int sender, int recipient, double amount) {
    this.sender = sender;
    this.recipient = recipient;
    this.amount = amount;
    date = Instant.now().toString();
  }
  
  public String toString() {
    return "From: " + sender + "\tTo: " + recipient + "\tAmount: $" + amount + "\tDate: " + date;
  }
}