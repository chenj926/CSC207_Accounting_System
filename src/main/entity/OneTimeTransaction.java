package entity;

import java.time.LocalDate;

public abstract class OneTimeTransaction implements Transaction {
    private String identification;
    private float amount;
    private LocalDate date;
    private String description;
    // private String category;

    private boolean inflow;

    public OneTimeTransaction(String identification, float amount, LocalDate date, String description, String category) {
        this.identification = identification;
        this.amount = amount;
        this.date = date;
        this.description = description;
        // this.category = category;
        this.inflow = (amount>=0);
    }

    // Getter
    @Override
    public String getIdentification() {
        return this.identification;
    }
    @Override
    public float getAmount() {
        return this.amount;
    }

    public LocalDate getDate() {
        return this.date;
    }
    @Override
    public String getDescription() {
        return this.description;
    }
    public boolean isInflow() {
        return this.inflow;
    }
    // public String getCategory() {return this.category;}
    public String getTransactionType() {
        return this.transactionType;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
