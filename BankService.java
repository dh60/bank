import java.io.IOException;

public class BankService {
    private Bank bank;

    public BankService(Bank bank) {
        this.bank = bank;
    }
	
	public User login(String username, String password) {
        for (User user : bank.getUsers()) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user;
            }
        }
        return null;
    }

}