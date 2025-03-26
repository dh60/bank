import java.io.IOException;

public class AccountService {
    private Bank bank;

    public AccountService(Bank bank) {
        this.bank = bank;
    }
	
	public void deposit(Account account, double amount) {
        account.deposit(amount);
        try {
            bank.saveToFile("data.ser");
        } catch (IOException e) {
            System.out.println("Deposit failed to save: " + e.getMessage());
        }
    }
	
	public boolean withdraw(Account account, double amount) {
        double withdrawn = account.withdraw(amount);
        if (withdrawn > 0) {
            try {
                bank.saveToFile("data.ser");
            } catch (IOException e) {
                System.out.println("Withdraw failed to save: " + e.getMessage());
            }
            return true;
        }
        return false;
    }

}