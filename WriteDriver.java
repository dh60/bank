import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class WriteDriver {
  public static void main(String[] args) {
    Account test = new Account();
    
    // Make sure Account functions properly before serializing
    System.out.println(test.getBalance());
    test.deposit(300);
    System.out.println(test.getBalance());
    
    // Serialize the object
    try {
      FileOutputStream fileOut = new FileOutputStream("testAccount.ser");
      ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
      objOut.writeObject(test);
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}