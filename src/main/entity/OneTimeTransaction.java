package entity;

import java.time.LocalDate;

public abstract class OneTimeTransaction implements Transaction {
    private String identification;
    private double amount;
    private LocalDate date;
    private String description;

    private boolean inflow;

    public OneTimeTransaction(String identification, double amount, LocalDate date, String description) {
        this.identification = identification;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.inflow = (amount>=0);
    }

    // Setter
    @Override
    public String getIdentification() {
        return identification;
    }
    @Override
    public double getAmount() {
        return amount;
    }
    @Override
    public LocalDate getDate() {
        return date;
    }
    @Override
    public String getDescription() {
        return description;
    }
    public boolean isInflow() {
        return inflow;
    }

    // Getter
    @Override
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    @Override
    public void setAmount(double amount) {
        this.amount = amount;
        this.inflow = (amount>=0);
    }
    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}


/*
public abstract class OneTimeTransaction implements Transaction {
    protected float transactionAmount;
    private transactionDate;
    private String transactionDescription;
    private String transactionCategory;
    private String TransactionNotes;

    public OneTimeTransaction(float transactionAmount, String transactionDate,  String transactionDescription,
                              String transactionCategory, String TransactionNotes) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
        this.TransactionNotes = TransactionNotes;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionDate() {
        return TransactionDate
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public String getTransactionNotes() {
        return TransactionNotes;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionDate(String transactionDate) {
        this.TransactionDate = transactionDate
    }
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }
    public void setTransactionNotes(String transactionNotes) {
        this.TransactionNotes = transactionNotes;
    }

    // Abstract method to record the transaction
    public abstract void recordTransaction(float amount);

    public void displayTransactionDetails() {
        System.out.println("amount: " + transactionAmount);
        System.out.println("Date: " + transactionDate);
        System.out.println("Descriptioon: " + transactionDescription);
        System.out.println("Category: " + transactionCategory);
        System.out.println("Additional Notes: " + TransactionNotes);
    }
}

 */
