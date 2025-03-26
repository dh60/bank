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
	
	public boolean transfer(Account fromAccount, Account toAccount, double amount) {
        boolean success = fromAccount.transfer(toAccount, amount);
        if (success) {
            try {
                bank.saveToFile("data.ser");
                System.out.println("Transfer successful: " + amount + " from " + fromAccount.getID() + " to " + toAccount.getID());
            } catch (IOException e) {
                System.out.println("Transfer failed to save: " + e.getMessage());
            }
        }
        return success;
    }
	
	public boolean transferById(Account fromAccount, int toAccountId, double amount) {
        Account toAccount = findAccountById(toAccountId);
        if (toAccount != null) {
            boolean success = transfer(fromAccount, toAccount, amount);
            if (success) {
                System.out.println("TransferById: " + amount + " from " + fromAccount.getID() + " to " + toAccountId);
            } else {
                System.out.println("TransferById failed: Insufficient funds or error");
            }
            return success;
        }
        System.out.println("TransferById failed: Account ID " + toAccountId + " not found");
        return false;
    }
	
	private Account findAccountById(int accountId) {
        for (User user : bank.getUsers()) {
            for (Account account : user.getAccounts()) {
                if (account.getID() == accountId) {
                    return account;
                }
            }
        }
        return null;
    }
	
}