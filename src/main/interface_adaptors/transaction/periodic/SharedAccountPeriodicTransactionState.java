package interface_adaptors.transaction.periodic;

import java.util.Set;

/**
 * The SharedAccountPeriodicTransactionState class extends the UserAccountPeriodicTransactionState class
 * to include additional information related to shared account transactions, such as the IDs
 * of users responsible for the transaction.
 *
 * This class is used to track and manage the state of shared account periodic transactions,
 * including transaction details, success or error messages, and information about the users
 * involved in the transaction.
 *
 * @see UserAccountPeriodicTransactionState
 * @see Set
 *
 *
 */
public class SharedAccountPeriodicTransactionState extends PeriodicTransactionState {
    public SharedAccountPeriodicTransactionState() {
        super();
    }
}
