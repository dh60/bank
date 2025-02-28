import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class ReadDriver {
  public static void main(String[] args) {
    Account readAccount = null;
    
    // Read the serialized object
    try {
      FileInputStream fileIn = new FileInputStream("testAccount.ser");
      ObjectInputStream objIn = new ObjectInputStream(fileIn);
      readAccount = (Account) objIn.readObject();
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
    catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
    
    System.out.println("Account ID: " + readAccount.getID());
    System.out.println("Balance:    $" + readAccount.getBalance());
  }
}