public class SavingsAccount extends Account{
    private double interestRate;
    public SavingsAccount(double interestRate){
        this.interestRate=interestRate;
    }
    public void calculateInterest(){
        double interest=getBalance()*(interestRate/100);
        deposit(interest);
    }
    public double getInterest(){
        return interestRate;
    }
    public void setInterest(double interestRate){
        this.interestRate=interestRate;
    }
}
