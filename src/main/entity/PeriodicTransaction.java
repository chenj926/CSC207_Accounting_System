package entity;

import java.time.LocalDate;

public abstract class PeriodicTransaction implements Transaction {
    private String identification;
    private float amount;
    private LocalDate date;
    private String description;
    private int recurrencePeriodInDays;

    private boolean inflow;

    public PeriodicTransaction(String identification, float amount, LocalDate date, String description, int recurrencePeriodInDays) {
        this.identification = identification;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.recurrencePeriodInDays = recurrencePeriodInDays;
        this.inflow = (amount>=0);
    }

    // Setter
    @Override
    public String getIdentification() {
        return identification;
    }
    @Override
    public float getAmount() {
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
    public int getRecurrencePeriodInDays() {
        return recurrencePeriodInDays;
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
    public void setAmount(float amount) {
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
    public void setRecurrencePeriodInDays(int recurrencePeriodInDays) {
        this.recurrencePeriodInDays = recurrencePeriodInDays;
    }
}

/*
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

 */