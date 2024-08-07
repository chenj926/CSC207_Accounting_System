package interface_adaptors.transaction.one_time;

import java.util.HashSet;
import java.util.Set;

/**
 * The SharedAccountOneTimeTransactionState class manages the state for the shared account one-time transaction.
 * It keeps track of the user IDs responsible for the transaction and provides methods for state management.
 *
 * This class is a part of the view model state management and helps in encapsulating the transaction-related data.
 *
 */
public class SharedAccountOneTimeTransactionState extends OneTimeTransactionState {
    private Set<String> responsibleUserIds;

    /**
     * Constructs a SharedAccountOneTimeTransactionState with an empty set of responsible user IDs.
     */
    public SharedAccountOneTimeTransactionState() {
        this.responsibleUserIds = new HashSet<>();
    }

    /**
     * Gets the set of responsible user IDs for the transaction.
     *
     * @return the set of responsible user IDs
     */
    public Set<String> getResponsibleUserIds() {
        return responsibleUserIds;
    }

    /**
     * Sets the responsible user IDs for the transaction.
     *
     * @param responsibleUserIds the set of responsible user IDs
     */
    public void setResponsibleUserIds(Set<String> responsibleUserIds) {
        this.responsibleUserIds = responsibleUserIds;
    }
}

