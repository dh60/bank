import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Transaction{
 private double amount;
 private String type;
 private double balance;
 public Transaction(double amount, String type, double balance){
   this.amount=amount;
   this.type=type;
   this.balance=balance;
 }
 public double getBalance() {
        return this.balance;
 }
 public boolean ValidTransaction(){
   if(this.type.equals("deposit")&&this.amount>0){
      return true;
   }
   if(this.type.equals("withdraw")&&this.amount>0 && this.amount <= this.balance){
      return true;
   }


   return false;
 }
 public boolean processingTransaction(){
    if(!ValidTransaction()){
      System.out.println("Failed Tranctions");
      return false;
    }
    switch(this.type){
        case "deposit":
              this.balance+=this.amount;
              System.out.println("Deposit of "+this.amount+" successful,Balance "+this.balance);
              break;
        case "withdraw":
              this.balance-=this.amount;
              System.out.println("Deposit of "+this.amount+" successful,Balance "+this.balance);
              break;
        default:
            System.out.println("Invalid Transaxtion type.");
            return false;    
    }
    saveTransactionToFile();
    return true;
 }
public void saveTransactionToFile(){
 try(BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true))){
    String transActionDetails="Transaction Type: " + this.type + ", Amount: " + this.amount + ", Balance: " + this.balance;
    writer.write(transActionDetails);
    writer.newLine();
    System.out.println("Transaction details saved to transactions.txt.");
 }catch(IOException e){
    System.out.println("Error saving transaction to file: " + e.getMessage());
 }
}

}