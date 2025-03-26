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

    public void register(String username, String password, String name, String email, String phone, String address) {
        User newUser = new User(username, password, name, email, phone, address, bank);  // Pass bank
        bank.getUsers().add(newUser);
        try {
            bank.saveToFile("data.ser");
        } catch (IOException e) {
            System.out.println("Register failed to save: " + e.getMessage());
        }
    }

    public Bank getBank() {
        return bank;
    }
}