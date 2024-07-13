package main.entity;

public class OneTimeInflow extends OneTimeTransaction {
    public OneTimeInflow(float transactionAmount){
        super(transactionAmount)
    }

    @Override
    punlic void recordTransaction(float amount) {
        super.recordTransaction(amount);
        System.out.println("one-time inflow: " +amount);
    }
}