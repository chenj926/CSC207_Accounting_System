package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

/**
 * The SharedAccountOneTimeTransactionViewModel class extends the ViewModel class
 * and manages the state and labels for the shared account one-time transaction view.
 * It provides getters for various labels used in the shared account one-time transaction view
 * and supports property change notifications.
 *
 * This view model specifically adds functionality for managing user selections,
 * allowing the specification of which users are responsible for the transaction.
 *
 * @see OneTimeTransactionViewModel
 */
public class SharedAccountOneTimeTransactionViewModel extends ViewModel {
    // labels
    private final String TITLE_LABEL = "Shared Account One Time Transaction";
    private final String AMOUNT = "Transaction Amount";
    private final String ID = "Identification";
    private final String DATE = "Transaction Date";
    private final String DESCRIPTION = "Description";
    private final String CATEGORY_BUTTON = "Transaction Category";
    private final String SELECT_USERS_BUTTON_LABEL = "Enter Responsible Users";
    private final String SUBMIT_BUTTON = "Submit Transaction";
    private final String CANCEL_BUTTON = "Cancel";

    private SharedAccountOneTimeTransactionState state = new SharedAccountOneTimeTransactionState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a SharedAccountOneTimeTransactionViewModel object with the view name set to "Shared Account One Time Transaction".
     */
    public SharedAccountOneTimeTransactionViewModel() {
        super("Shared Account One Time Transaction");
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }

    /**
     * Gets the amount label.
     *
     * @return the amount label
     */
    public String getAmount() {
        return this.AMOUNT;
    }

    /**
     * Gets the identification label.
     *
     * @return the identification label
     */
    public String getId() {
        return this.ID;
    }

    /**
     * Gets the date label.
     *
     * @return the date label
     */
    public String getDate() {
        return this.DATE;
    }

    /**
     * Gets the description label.
     *
     * @return the description label
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Gets the category label.
     *
     * @return the category label
     */
    public String getCategoryButton() {
        return this.CATEGORY_BUTTON;
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
     * Gets the submit button label.
     *
     * @return the submit button label
     */
    public String getSubmitButton() {
        return this.SUBMIT_BUTTON;
    }

    /**
     * Gets the cancel button label.
     *
     * @return the cancel button label
     */
    public String getCancelButton() {
        return this.CANCEL_BUTTON;
    }

    /**
     * Gets the current shared account one-time transaction state.
     *
     * @return the current shared account one-time transaction state
     */
    public SharedAccountOneTimeTransactionState getState() {
        return this.state;
    }

    /**
     * Sets the current shared account one-time transaction state.
     *
     * @param state the new shared account one-time transaction state
     */
    public void setState(SharedAccountOneTimeTransactionState state) {
        this.state = state;
        firePropertyChanged();
    }

    /**
     * Gets the selected responsible user IDs.
     *
     * @return the set of responsible user IDs
     */
//    public Set<String> getSelectedUserIds() {
//        return this.state.getResponsibleUserIds();
//    }

    /**
     * Sets the responsible user IDs for the transaction.
     *
     * @param userIds the set of responsible user IDs
     */
//    public void setResponsibleUserIds(Set<String> userIds) {
//        this.state.setResponsibleUserIds(userIds);
//        firePropertyChanged();
//    }

    /**
     * Notifies listeners that the shared account one-time transaction state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from the listener list.
     *
     * @param listener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Resets the shared account one-time transaction state to default values.
     */
    public void resetState() {
        SharedAccountOneTimeTransactionState newState = new SharedAccountOneTimeTransactionState();
        setState(newState);
    }
}

