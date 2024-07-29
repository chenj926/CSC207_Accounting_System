package entity;

import java.time.LocalDate;

// dateTimes.add(LocalDateTime.of(2024, 7, 13));
// dateTimes.add(LocalDateTime.now());


public class Transaction {
    // getters

    String identification;
    private float amount;
    private LocalDate date;
    private String description;

    private boolean inflow;

    LocalDate getDate();
    public String getIdentification() {
        return identification;
    }
    public String getDescription() {
        return this.description;
    }
    public float getAmount() {
        return amount;
    }

    // setters
    void setDate(LocalDate date);
    void setIdentification(String identification);
    void setDescription(String description);
    void setAmount(float amount);
}