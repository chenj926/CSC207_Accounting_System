package main.entity;

public interface Transaction {
    void RecordTransaction(float amount);
}





public class OneTimeInflow extends OneTimeTransaction {
    public OneTimeInflow(float transactionAmount){
        super(transactionAmount)
    }

    @Override
    punlic void recordTransaction(float amount) {
        super.recordTransaction(amount);
        // System.out.println("one-time inflow: " +amount);
    }
}


public class OneTimeOutflow extends OneTimeTransaction {
    public OneTimeOutflow(float transactionAmount){
        super(transactionAmount);
    }
    @Override
    punlic void recordTransaction(float amount) {
        super.recordTransaction(amount);
        // System.out.println("one-time outflow: " +amount);
    }
}
