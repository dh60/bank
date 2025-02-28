public class TransactionDriver{
    public static void main(String[] args){
        
        Transaction deposit= new Transaction(500.0,"deposit",1000);
        Transaction withdraw= new Transaction(200.0,"withdraw",1500);

        deposit.processingTransaction();

        withdraw.processingTransaction();

        Transaction Invalidwithdraw= new Transaction(2000,"withdraw",1000);
        Invalidwithdraw.processingTransaction();
        
    }
} 