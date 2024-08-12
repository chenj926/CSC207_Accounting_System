package interface_adaptors.transaction.periodic.user_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;

/**
 * The UserAccountPeriodicTransactionViewModel class extends the ViewModel class and manages the state and labels for the periodic transaction view.
 * It provides getters for various labels used in the periodic transaction view and supports property change notifications.
 *
 * @author Rita
 * @author Xile
 * @author Jessica
 * @author Eric
 */
public class UserAccountPeriodicTransactionViewModel extends PeriodicTransactionViewModel<UserAccountPeriodicTransactionState> {

    /**
     * Constructs a UserAccountPeriodicTransactionViewModel object with the view name set to "Periodic Transaction".
     */
    public UserAccountPeriodicTransactionViewModel() {
        super("Periodic Transaction");
        this.transactionState = new UserAccountPeriodicTransactionState();
    }

    /**
     * Resets the periodic transaction state to default values.
     */
    public void resetState() {
        UserAccountPeriodicTransactionState newState = new UserAccountPeriodicTransactionState();
        setState(newState);
    }
}

