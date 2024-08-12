package interface_adaptors.transaction.one_time.user_account;

import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;

/**
 * The UserOneTimeTransactionViewModel class extends the ViewModel class and manages the state and labels for the one-time transaction view.
 * It provides getters for various labels used in the one-time transaction view and supports property change notifications.
 *
 * @author Xile
 * @author Jessica
 * @author Eric
 */
public class UserOneTimeTransactionViewModel extends OneTimeTransactionViewModel<
        UserOneTimeTransactionState> {

    /**
     * Constructs a UserOneTimeTransactionViewModel object with the view name set to "One Time Transaction".
     */
    public UserOneTimeTransactionViewModel() {
        super("One Time Transaction");
        this.state = new UserOneTimeTransactionState();
    }


    /**
     * Resets the one-time transaction state to default values.
     */
    @Override
    public void resetState() {
        setState(new UserOneTimeTransactionState());
    }

}

