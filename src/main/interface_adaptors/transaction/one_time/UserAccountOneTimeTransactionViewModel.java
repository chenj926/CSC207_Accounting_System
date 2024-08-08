package interface_adaptors.transaction.one_time;

import entity.account.UserAccount;
import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The UserAccountOneTimeTransactionViewModel class extends the ViewModel class and manages the state and labels for the one-time transaction view.
 * It provides getters for various labels used in the one-time transaction view and supports property change notifications.
 *
 * @author Xile
 * @author Jessica
 * @author Eric
 */
public class UserAccountOneTimeTransactionViewModel extends
        AccountOneTimeTransactionViewModel<
                UserAccountOneTimeTransactionState> {

    /**
     * Constructs a UserAccountOneTimeTransactionViewModel object with the view name set to "One Time Transaction".
     */
    public UserAccountOneTimeTransactionViewModel() {
        super("One Time Transaction");
        this.state = new UserAccountOneTimeTransactionState();
    }


    /**
     * Resets the one-time transaction state to default values.
     */
    @Override
    public void resetState() {
        setState(new UserAccountOneTimeTransactionState());
    }

}

