package interface_adaptors.transaction.one_time.shared_account;

import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;

/**
 * The SharedOneTimeTransactionViewModel class extends the ViewModel class
 * and manages the state and labels for the shared account one-time transaction view.
 * It provides getters for various labels used in the shared account one-time transaction view
 * and supports property change notifications.
 *
 * This view model specifically adds functionality for managing user selections,
 * allowing the specification of which users are responsible for the transaction.
 *
 * @see OneTimeTransactionViewModel
 */
public class SharedOneTimeTransactionViewModel extends
        OneTimeTransactionViewModel<SharedOneTimeTransactionState> { ;
    private final String USER_ID_FIELD_LABEL = "User ID";

    /**
     * Constructs a SharedOneTimeTransactionViewModel object with the view name set to "Shared Account One Time Transaction".
     */
    public SharedOneTimeTransactionViewModel() {
        super("Shared Account One Time Transaction");
        this.state = new SharedOneTimeTransactionState();
    }

    /**
     * Gets the identification label.
     *
     * @return the identification label
     */
    public String getUserIdFieldLabel() {
        return this.USER_ID_FIELD_LABEL;
    }

    /**
     * Gets the responsible users label.
     *
     * @return the responsible users label
     */
    public String getSELECT_USERS_BUTTON_LABEL() {
        return this.USER_ID_FIELD_LABEL;
    }

    /**
     * Resets the shared account one-time transaction state to default values.
     */
    @Override
    public void resetState() {
        setState(new SharedOneTimeTransactionState());
    }
}

