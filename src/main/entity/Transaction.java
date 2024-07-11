package main.entity;

public interface Transaction {
    void RecordTransaction(float amount);
}



public abstract class OneTimeTransaction implements Transaction {
    protected float transactionAmount;

    // Abstract method to record the transaction
    public abstract void recordTransaction(float amount);
}
}