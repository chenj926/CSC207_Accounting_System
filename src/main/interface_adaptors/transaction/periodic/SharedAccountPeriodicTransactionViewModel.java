package interface_adaptors.transaction.periodic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The SharedAccountPeriodicTransactionViewModel class extends the PeriodicTransactionViewModel class
 * and manages the state and labels for the shared account periodic transaction view.
 * It provides getters for various labels used in the shared account periodic transaction view and
 * supports property change notifications.
 *
 * This class handles additional shared account details, such as responsible user IDs, and updates
 * the view state accordingly.
 *
 * @see PeriodicTransactionViewModel
 * @see SharedAccountPeriodicTransactionState
 * @see PropertyChangeSupport
 *
 *
 */
public class SharedAccountPeriodicTransactionViewModel extends PeriodicTransactionViewModel {
    private final String SELECT_USERS_BUTTON_LABEL = "Select Responsible Users";

    private SharedAccountPeriodicTransactionState transactionState = new SharedAccountPeriodicTransactionState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a SharedAccountPeriodicTransactionViewModel object with the view name set to "Shared Account Periodic Transaction".
     */
    public SharedAccountPeriodicTransactionViewModel() {
        super();
    }

    /**
     * Gets the responsible users label.
     *
     * @return the responsible users label
     */
    public String getSELECT_USERS_BUTTON_LABEL() {
        return this.SELECT_USERS_BUTTON_LABEL;
    }

    /**
     * Gets the current shared account periodic transaction state.
     *
     * @return the current shared account periodic transaction state
     */
    @Override
    public SharedAccountPeriodicTransactionState getState() {
        return this.transactionState;
    }

    /**
     * Sets the current shared account periodic transaction state.
     *
     * @param state the new shared account periodic transaction state
     */
    @Override
    public void setState(PeriodicTransactionState state) {
        if (state instanceof SharedAccountPeriodicTransactionState) {
            this.transactionState = (SharedAccountPeriodicTransactionState) state;
        }
    }

    /**
     * Notifies listeners that the shared account periodic transaction state has changed.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.transactionState);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from the listener list.
     *
     * @param listener the PropertyChangeListener to be removed
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
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

