package data_access.transaction;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.util.*;

/**
 * In-memory data access object (DAO) for user account and one-time transaction operations.
 * <p>
 * This class implements the {@link UserAccountDataAccessInterface} interface and provides an in-memory
 * implementation for managing user accounts and one-time transactions. It is intended for testing purposes
 * and does not persist data beyond the lifetime of the application.
 * </p>
 *
 * @author Eric
 */
public class InMemoryOneTimeDataAccessObject implements UserAccountDataAccessInterface{
    private static Map<String, UserAccount> users;

    /**
     * Constructs a new {@link InMemoryOneTimeDataAccessObject} with an empty user account map.
     */
    public InMemoryOneTimeDataAccessObject() {
        this.users = new HashMap<>();
    }

    /**
     * Checks if a user account exists with the given identifier.
     *
     * @param identifier the unique identifier for the user account
     * @return {@code true} if a user account exists with the specified identifier; {@code false} otherwise
     */
    @Override
    public boolean existById(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * Retrieves a user account by its unique identifier.
     *
     * @param identification the unique identifier for the user account
     * @return {@code null} as this method is not implemented yet
     */
    @Override
    public UserAccount getById(String identification) {
        return null;
    }

    /**
     * Updates a user account.
     *
     * @param userAccount the {@link UserAccount} to be updated
     */
    @Override
    public void update(UserAccount userAccount) {
        System.out.println("update");
    }

    /**
     * Deletes a user account by its unique identifier.
     *
     * @param id the unique identifier for the user account to be deleted
     */
    @Override
    public void deleteById(String id) {
        System.out.println("id");
    }

    /**
     * Saves a user account.
     *
     * @param userAccount the {@link UserAccount} to be saved
     */
    @Override
    public void save(UserAccount userAccount) {
        System.out.println("id");
    }

    /**
     * Saves transaction data. This method is not yet implemented.
     *
     * @param oneTimeOutputData   the output data for one-time transactions
     * @param periodicOutputData  the output data for periodic transactions
     * @param isPeriodic          {@code true} if the transaction is periodic; {@code false} if it is one-time
     */
    @Override
    public void saveTransaction(OneTimeTransactionOutputData oneTimeOutputData,
                                PeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {
        System.out.println("not implemented yet");
    }

    @Override
    public List<Transaction> readTransactions(String identification) {
        List<Transaction> transactions= new ArrayList<>();
        return transactions;
    }


}
