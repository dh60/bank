public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int id) {  
        super(id);  
        interestRate = 4.0; 
    }

    public double getInterestRate() {
        return interestRate;
    }
}