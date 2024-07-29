package entity;

import java.time.LocalDate;

public abstract class PeriodicTransaction extends Transaction {
    private String identification;
    private float amount;
    private LocalDate date;
    private String description;
    private boolean inflow;

    private LocalDate startDate;
    private LocalDate endDate;
    private int period;

    public PeriodicTransaction(String identification, float amount, LocalDate startDate, String description,
                               LocalDate endDate, int period) {
        this.identification = identification;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        this.description = description;
//        this.recurrencePeriodInDays = recurrencePeriodInDays;
        this.inflow = (amount>=0);
        this.date = startDate;
    }

    public boolean isInflow() {
        return this.inflow;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public int getPeriod() {
        return this.period;
    }

    public LocalDate getDate() {
        return this.date;
    }

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

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }
    //    public void setRecurrencePeriodInDays(int recurrencePeriodInDays) {
//        this.recurrencePeriodInDays = recurrencePeriodInDays;
//    }
}