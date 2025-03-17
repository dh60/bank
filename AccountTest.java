public class AccountTest {
    //making test cases for the account to check if everything is working correctly
    public static void main(String[] args) {
        Account account =new Account();
        Account recipientAccount =new Account();
        account.deposit(150);
        System.out.println("amount deposited:"+account.getBalance());

        double a=account.withdraw(50);
        System.out.println("amount withdrawn:"+a);
        System.out.println("amount left after withdrawn:"+account.getBalance());

        boolean t=account.transfer(recipientAccount,100.00);
        System.out.println("did the transfer work?"+t+"\nthe balance in recipient account "+recipientAccount.getBalance());


        //did the transactions get stored?

        System.out.println("ALL TRANSACTION");
        for (Transaction temp : account.getTransactions()) { 
            System.out.println(temp);  
        }
        
        System.out.println(account.getTransactions());
        
    }
    
}
