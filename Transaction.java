import java.time.Instant;
import java.io.Serializable;

public class Transaction implements Serializable {
  private int sender;
  private int recipient;
  private double amount;
  private String date;

  public Transaction(int sender, int recipient, double amount) {
    this.sender = sender;
    this.recipient = recipient;
    this.amount = amount;
    this.date = Instant.now().toString();  
  }


  public String toString(int accountId) {
    if (accountId == sender) {
      return date + ", " + sender + ", Debit, Sent to " + recipient + ", $" + amount;
    } else if (accountId == recipient) {
      return date + ", " + recipient + ", Credit, Received from " + sender + ", $" + amount;
    } else {
      return date + ", " + sender + " to " + recipient + ", $" + amount;
    }
  }
  
  
  @Override
  public String toString() {
    return "From: " + sender + " To: " + recipient + " Amount: $" + amount;
  }
}