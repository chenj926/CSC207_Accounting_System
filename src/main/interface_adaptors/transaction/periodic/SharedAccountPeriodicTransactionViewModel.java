package interface_adaptors.transaction.periodic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The SharedAccountPeriodicTransactionViewModel class extends the UserAccountPeriodicTransactionViewModel class
 * and manages the state and labels for the shared account periodic transaction view.
 * It provides getters for various labels used in the shared account periodic transaction view and
 * supports property change notifications.
 *
 * This class handles additional shared account details, such as responsible user IDs, and updates
 * the view state accordingly.
 *
 * @see UserAccountPeriodicTransactionViewModel
 * @see SharedAccountPeriodicTransactionState
 * @see PropertyChangeSupport
 *
 *
 */
public class SharedAccountPeriodicTransactionViewModel extends PeriodicTransactionViewModel<SharedAccountPeriodicTransactionState> {
    private final String USER_ID_FIELD_LABEL = "User ID";
    private final String SELECT_USER = "Users";

    /**
     * Constructs a SharedAccountPeriodicTransactionViewModel object with the view name set to "Shared Account Periodic Transaction".
     */
    public SharedAccountPeriodicTransactionViewModel() {
        super("Periodic Transaction");
        this.transactionState = new SharedAccountPeriodicTransactionState();
    }

    /**
     * Gets the responsible users label.
     *
     * @return the responsible users label
     */
    public String getUSER_ID_FIELD_LABEL() {
        return this.USER_ID_FIELD_LABEL;
    }
    public String getSELECT_USER() {
        return this.SELECT_USER;
    }


    /**
     * Resets the shared account periodic transaction state to default values.
     */
    @Override
    public void resetState() {
        SharedAccountPeriodicTransactionState newState = new SharedAccountPeriodicTransactionState();
        setState(newState);
    }

}

