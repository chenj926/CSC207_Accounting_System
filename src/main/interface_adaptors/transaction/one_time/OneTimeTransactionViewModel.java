package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The OneTimeTransactionViewModel class extends the ViewModel class and manages the state and labels for the one-time transaction view.
 * It provides getters for various labels used in the one-time transaction view and supports property change notifications.
 *
 * @author Xile
 * @author Jessica
 * @author Eric
 */
public class OneTimeTransactionViewModel extends ViewModel {
    // labels
    private final String TITLE_LABEL = "One Time Transaction";
    private final String AMOUNT = "Transaction Amount";
    private final String ID = "Identification";
    private final String DATE = "Transaction Date";
    private final String DESCRIPTION = "Description";
    private final String CATEGORY_BUTTON = "Transaction Category";
    private final String SUBMIT_BUTTON = "Submit Transaction";
    private final String CANCEL_BUTTON = "Cancel";

    private OneTimeTransactionState state = new OneTimeTransactionState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a OneTimeTransactionViewModel object with the view name set to "One Time Transaction".
     */
    public OneTimeTransactionViewModel() {
        super("One Time Transaction");
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
     * Gets the current one-time transaction state.
     *
     * @return the current one-time transaction state
     */
    public OneTimeTransactionState getState() {
        return this.state;
    }

    /**
     * Sets the current one-time transaction state.
     *
     * @param state the new one-time transaction state
     */
    public void setState(OneTimeTransactionState state) {
        this.state = state;
    }

    /**
     * Notifies listeners that the one-time transaction state has changed.
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
     * Resets the one-time transaction state to default values.
     */
    public void resetState() {
        OneTimeTransactionState newState = new OneTimeTransactionState();
        setState(newState);
    }

}

