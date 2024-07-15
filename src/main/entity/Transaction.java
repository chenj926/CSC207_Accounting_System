package entity;

import java.time.LocalDate;

// dateTimes.add(LocalDateTime.of(2024, 7, 13));
// dateTimes.add(LocalDateTime.now());


public interface Transaction {
    // getters
    LocalDate getDate();
    String getIdentification();
    String getDescription();
    float getAmount();

    // setters
    void setDate(LocalDate date);
    void setIdentification(String identification);
    void setDescription(String description);
    void setAmount(float amount);
}

/*
public interface Transaction {
    void recordTransaction(String identification, float transactionAmount, LocalDate transactionDate, String transactionDescription, String recurrence, boolean periodic);
}

 */

