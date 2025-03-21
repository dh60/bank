public class AccountTest {
    //making test cases for the account to check if everything is working correctly
    public static void main(String[] args) {
        System.out.println("Account Basic Testing");
        Account account =new Account();
        Account recipientAccount =new Account();

        //Deposit testing
        account.deposit(150);
        System.out.println("amount deposited:"+account.getBalance());

        //withdraw testing
        double withdraw=account.withdraw(50);
        System.out.println("amount withdrawn:"+withdraw);
        System.out.println("amount left after withdrawn:"+account.getBalance());

        //transfer testing
        boolean transfer=account.transfer(recipientAccount,100.00);
        System.out.println("did the transfer work?true or false"+transfer+"\nthe balance in recipient account "+recipientAccount.getBalance());


        //did the transactions get stored?

        System.out.println("ALL TRANSACTION OF SENDER");
        for (Transaction temp : account.getTransactions()) { 
            System.out.println(temp);  
        }
        System.out.println("ALL TRANSACTION OF RECIPIENT");
        for (Transaction temp : account.getTransactions()) { 
            System.out.println(temp);  
        }
                
        //savings accounts and interest calculation\
        System.out.println("ALL TRANSACTION OF RECIPIENT");
        SavingsAccount savings=new SavingsAccount(2);
        savings.deposit(1000);
        System.out.println(savings.getInterest());
        savings.calculateInterest();
        System.out.println(savings.getBalance());

        System.out.println("INTEREST TESTING");
        System.out.println("changing the interest to 3%");
        savings.setInterest(3);
        savings.calculateInterest();
        System.out.println("After second interest calculation, Balance: "+savings.getBalance());

    }
    
}
