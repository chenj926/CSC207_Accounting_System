package use_case.transaction.one_time;

import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.TransactionOutputData;

import java.util.Set;

/**
 * The SharedAccountOneTimeTransactionOutputData class represents the output data of a one-time transaction operation
 * that involves a shared account.
 * It includes details such as the new balance of the shared account and the set of users responsible for the transaction.
 */
public class SharedAccountOneTimeTransactionOutputData extends OneTimeTransactionOutputData {
    private final float newSharedAccountBalance;
    private final Set<String> responsibleUserIds; // List of responsible user IDs

    /**
     * Constructs a SharedAccountOneTimeTransactionOutputData object with the specified details.
     *
     * @param oneTimeTransaction      the one-time transaction entity
     * @param newSharedAccountBalance the new balance of the shared account
     * @param responsibleUserIds      the set of user IDs responsible for the transaction
     */
    public SharedAccountOneTimeTransactionOutputData(
            OneTimeTransaction oneTimeTransaction,
            float newSharedAccountBalance,
            Set<String> responsibleUserIds) {
        super(oneTimeTransaction);
        this.newSharedAccountBalance = newSharedAccountBalance;
        this.responsibleUserIds = responsibleUserIds;
    }

    /**
     * Gets the new balance of the shared account.
     *
     * @return the new balance of the shared account
     */
    public float getNewSharedAccountBalance() {
        return newSharedAccountBalance;
    }

    /**
     * Gets the set of user IDs responsible for the transaction.
     *
     * @return a set of user IDs responsible for the transaction
     */
    public Set<String> getResponsibleUserIds() {
        return responsibleUserIds;
    }
}

