package entity;

import java.time.LocalDate;

public abstract class PeriodicTransaction implements Transaction {
    private String identification;
    private float amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private int period;
    private String description;
//    private int recurrencePeriodInDays;

    private boolean inflow;

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
        return this.description;
    }
//    public int getRecurrencePeriodInDays() {
//        return recurrencePeriodInDays;
//    }
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
//    public void setRecurrencePeriodInDays(int recurrencePeriodInDays) {
//        this.recurrencePeriodInDays = recurrencePeriodInDays;
//    }
}