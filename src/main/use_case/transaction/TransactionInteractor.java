package use_case.transaction;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import use_case.transaction.one_time.OneTimeTransactionOutputBoundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public abstract class TransactionInteractor{
    protected final UserAccountDataAccessInterface userDataAccessObject;
    protected final UserAccount userAccount;

    /**
     * Constructs a TransactionInteractor object with data access object,
     * and user account.
     *
     * @param userAccountDataAccessInterface the data access interface for user data
     * @param userAccount the user account associated with the transaction
     */
    public TransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                 UserAccount userAccount) {
        this.userDataAccessObject = userAccountDataAccessInterface;
        this.userAccount = userAccount;
    }

    /**
     * Checks if the provided user input is valid (not null or empty).
     *
     * @param userInfo the user input to check
     * @return true if the user input is valid, false otherwise
     */
    protected boolean checkValid(String userInfo) {
        if (userInfo == null || userInfo.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Parses and formats the transaction amount to two decimal places.
     * <p>
     * This method tries to parse the input string to a float and formats it to two decimal places.
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
     * This method tries to parse the input date string to a LocalDate object using a strict date format.
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

