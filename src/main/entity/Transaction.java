package entity;

import java.time.LocalDate;

// dateTimes.add(LocalDateTime.of(2024, 7, 13));
// dateTimes.add(LocalDateTime.now());

public interface Transaction {
    void recordTransaction(String identification, float transactionAmount, LocalDate transactionDate, String transactionDescription, String recurrence, Boolean periodic);
}