package interface_adaptors.transaction.one_time.shared_account;

import interface_adaptors.transaction.one_time.AccountOneTimeTransactionState;

/**
 * The SharedAccountOneTimeTransactionState class manages the state for the shared account one-time transaction.
 * It keeps track of the user IDs responsible for the transaction and provides methods for state management.
 *
 * This class is a part of the view model state management and helps in encapsulating the transaction-related data.
 *
 */
public class SharedAccountOneTimeTransactionState extends AccountOneTimeTransactionState {

    /**
     * Constructs a SharedAccountOneTimeTransactionState with an empty set of responsible user IDs.
     */
    public SharedAccountOneTimeTransactionState() {
        super();
    }
}

