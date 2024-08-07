package interface_adaptors.transaction.periodic;

import java.util.Set;

/**
 * The SharedAccountPeriodicTransactionState class extends the PeriodicTransactionState class
 * to include additional information related to shared account transactions, such as the IDs
 * of users responsible for the transaction.
 *
 * This class is used to track and manage the state of shared account periodic transactions,
 * including transaction details, success or error messages, and information about the users
 * involved in the transaction.
 *
 * @see PeriodicTransactionState
 * @see Set
 *
 *
 */
public class SharedAccountPeriodicTransactionState extends PeriodicTransactionState {
    private Set<String> responsibleUserIds;

    /**
     * Gets the set of responsible user IDs for the transaction.
     *
     * @return the set of responsible user IDs
     */
    public Set<String> getResponsibleUserIds() {
        return this.responsibleUserIds;
    }

    /**
     * Sets the set of responsible user IDs for the transaction.
     *
     * @param responsibleUserIds the new set of responsible user IDs
     */
    public void setResponsibleUserIds(Set<String> responsibleUserIds) {
        this.responsibleUserIds = responsibleUserIds;
    }
}
