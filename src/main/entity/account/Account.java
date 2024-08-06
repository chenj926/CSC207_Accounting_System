package entity.account;

import entity.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;

/**
 * The Account interface provides a blueprint for an account entity.
 * It includes methods to get and set account details, and to manage user accounts.
 *
 * @author Jessica
 * @author Eric
 */
public interface Account {
    /**
     * Gets the identification of the account.
            *
            * @return the identification of the account
     */
    String getIdentification();

    /**
     * Gets the username of the account.
     *
     * @return the username of the account
     */
    String getUsername();

    /**
     * Gets the password of the account.
     *
     * @return the password of the account
     */
    String getPassword();

    /**
     * Gets the total income of the account.
     *
     * @return the total income of the account
     */
    float getTotalIncome();

    /**
     * Gets the total outflow of the account.
     *
     * @return the total outflow of the account
     */
    float getTotalOutflow();

    /**
     * Gets the total balance of the account.
     *
     * @return the total balance of the account
     */
    float getTotalBalance();

    /**
     * Gets the list of transactions of the account.
     *
     * @return a list of transactions of the account
     */
    List<Transaction> getTransactions();

    /**
     * Gets the last login date of the account.
     *
     * @return the last login date
     */
    LocalDate getLastLoginDate();

    /**
     * Sets the identification of the account.
     *
     * @param identification the new identification of the account
     */
    void setIdentification(String identification);

    /**
     * Sets the username of the account.
     *
     * @param username the new username of the account
     */
    void setUsername(String username);

    /**
     * Sets the password of the account.
     *
     * @param password the new password of the account
     */
    void setPassword(String password);

    /**
     * Sets the total income of the account.
     *
     * @param totalIncome the new total income of the account
     */
    void setTotalIncome(float totalIncome);

    /**
     * Sets the total outflow of the account.
     *
     * @param totalOutflow the new total outflow of the account
     */
    void setTotalOutflow(float totalOutflow);

    /**
     * Sets the total balance of the account.
     *
     * @param totalBalance the new total balance of the account
     */
    void setTotalBalance(float totalBalance);

    /**
     * Sets the list of transactions of the account.
     *
     * @param transactions the new list of transactions of the account
     */
    void setTransactions(List<Transaction> transactions);

    /**
     * Sets the last login date.
     *
     * @param lastLoginDate the last login date of the account
     */
    void setLastLoginDate(LocalDate lastLoginDate);

    /**
     * Adds a transaction to the account.
     *
     * @param transaction the transaction to be added to the account
     */
    void addTransaction(Transaction transaction);

}

