package entity;

public abstract class PeriodicTransaction implements Transaction {
    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String recurrence;

    public PeriodicTransaction(float transactionAmount, String transactionDate, String transactionDescription, String recurrence) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.recurrence = recurrence;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public abstract void recordTransaction();

    public void displayTransactionDetails() {
        System.out.println("Amount: " + transactionAmount);
        System.out.println("Date: " + transactionDate);
        System.out.println("Description: " + transactionDescription);
        System.out.println("Recurrence: " + recurrence);
    }
}