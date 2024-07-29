package entity.transaction;

import java.time.LocalDate;

/**
 * The Transaction interface provides a blueprint for a financial transaction entity.
 * It includes methods to get and set transaction details such as date, identification,
 * description, and amount.
 *
 * @author Jessica
 * @author Eric
 */
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