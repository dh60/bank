import java.time.Instant;

public class Transaction {
  private int sender;
  private int receiver;
  private double amount;
  private String date;
  
  public Transaction(int sender, int receiver, double amount) {
    this.sender = sender;
    this.receiver = receiver;
    this.amount = amount;
    date = Instant.now().toString();
  }
}