public class SavingsAccount extends Account{
    private double interestRate;
    
    public SavingsAccount(double interestRate){
        this.interestRate=interestRate;
    }
    
    public double getInterestRate(){
        return interestRate;
    }
    
    public void setInterest(double interestRate){
        this.interestRate=interestRate;
    }
}
