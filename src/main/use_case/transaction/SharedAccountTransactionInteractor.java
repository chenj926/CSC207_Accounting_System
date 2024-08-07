package use_case.transaction;

import data_access.account.ShareAccountDataAccessInterface;
import entity.account.SharedAccount;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * The SharedAccountTransactionInteractor class provides a base implementation for shared account transactions,
 * including input validation, amount parsing, and date parsing.
 * <p>
 * This class ensures consistency in how shared account transactions are processed by providing utility methods
 * that are commonly used across different transaction types.
 * </p>
 */
public abstract class SharedAccountTransactionInteractor {
    protected final ShareAccountDataAccessInterface sharedAccountDataAccessInterface;
    protected final SharedAccount sharedAccount;

    /**
     * Constructs a SharedAccountTransactionInteractor object with data access object
     * and shared account information.
     *
     * @param sharedAccountDataAccessInterface the data access interface for shared account data
     * @param sharedAccount the shared account associated with the transaction
     */
    public SharedAccountTransactionInteractor(ShareAccountDataAccessInterface sharedAccountDataAccessInterface,
                                              SharedAccount sharedAccount) {
        this.sharedAccountDataAccessInterface = sharedAccountDataAccessInterface;
        this.sharedAccount = sharedAccount;
    }

    /**
     * Checks if the provided input is valid (not null or empty).
     *
     * @param input the input to check
     * @return true if the input is valid, false otherwise
     */
    protected boolean checkValid(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Parses and formats the transaction amount to two decimal places.
     * <p>
     * This method attempts to parse the input string to a float and formats it to two decimal places.
     * If the parsing fails, it returns Float.MIN_VALUE as an indication of failure.
     * </p>
     *
     * @param stringAmount the transaction amount as a string
     * @return the parsed and formatted amount as a float, or Float.MIN_VALUE if parsing fails
     */
    protected float parseAmount(String stringAmount) {
        try {
            float amount = Float.parseFloat(stringAmount);
            return Float.parseFloat(String.format("%.2f", amount));
        } catch (NumberFormatException e) {
            return Float.MIN_VALUE; // Return a sentinel value indicating failure
        }
    }

    /**
     * Parses and validates the transaction date.
     * <p>
     * This method attempts to parse the input date string to a LocalDate object using a strict date format.
     * If the parsing fails, it returns null as an indication of failure.
     * </p>
     *
     * @param date the transaction date as a string
     * @return the parsed date as a LocalDate object, or null if parsing fails
     */
    protected LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null; // Return null indicating failure
        }
    }
}

