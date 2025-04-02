import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Transaction implements Serializable {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private int sender;
  private int recipient;
  private double amount;
  private String date;
  
  public Transaction(int sender, int recipient, double amount) {
    this.sender = sender;
    this.recipient = recipient;
    this.amount = amount;
    date = LocalDateTime.now().format(FORMATTER);
  }
  
  public String toString() {
    return "From: " + sender + "\tTo: " + recipient + "\tAmount: $" + amount + "\tDate: " + date;
  }
}