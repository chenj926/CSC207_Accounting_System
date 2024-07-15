package entity;

import java.time.LocalDate;

public abstract class PeriodicTransaction implements Transaction {
    private String identification;
    private float amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private String description;
    private int recurrencePeriodInDays;

    private boolean inflow;

    public PeriodicTransaction(String identification, float amount, LocalDate startDate, String description,
                               int recurrencePeriodInDays, LocalDate endDate, String duration) {
        this.identification = identification;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.description = description;
        this.recurrencePeriodInDays = recurrencePeriodInDays;
        this.inflow = (amount>=0);
    }

    // Getter
    @Override
    public String getIdentification() {
        return identification;
    }
    @Override
    public float getAmount() {
        return amount;
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
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getDuration() { return duration; }

    // Setter
    @Override
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    @Override
    public void setAmount(float amount) {
        this.amount = amount;
        this.inflow = (amount>=0);
    }

    public void setStartDateDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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