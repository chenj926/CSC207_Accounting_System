package interface_adaptors.transaction.periodic;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The PeriodicTransactionViewModel class extends the ViewModel class and manages the state and labels for the periodic transaction view.
 * It provides getters for various labels used in the periodic transaction view and supports property change notifications.
 *
 * @author Rita
 * @author Xile
 * @author Jessica
 * @author Eric
 */
public class PeriodicTransactionViewModel extends ViewModel {
    // labels
    private final String TITLE_LABEL = "Periodic Transaction";
    private final String AMOUNT = "Transaction Amount";
    private final String ID = "Identification";
    private final String STARTDATE = "Transaction Start Date";
    private final String ENDDATE = "Transaction End Date";
    private final String DESCRIPTION = "Description";
    private final String PERIOD = "Period";
    private final String CATEGORY_BUTTON = "Transaction Category";

    private final String SUBMIT_BUTTON = "Submit Transaction";
    private final String CANCEL_BUTTON = "Cancel";

    private PeriodicTransactionState transactionState = new PeriodicTransactionState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a PeriodicTransactionViewModel object with the view name set to "Periodic Transaction".
     */
    public PeriodicTransactionViewModel() {
        super("Periodic Transaction");
    }

    // getters
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
     * Gets the start date label.
     *
     * @return the start date label
     */
    public String getStartDate() {
        return this.STARTDATE;
    }

    /**
     * Gets the end date label.
     *
     * @return the end date label
     */
    public String getEndDate() {
        return this.ENDDATE; }

    /**
     * Gets the description label.
     *
     * @return the description label
     */
    public String getDescription() {
        return this.DESCRIPTION;
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
     * Gets the period label.
     *
     * @return the period label
     */
    public String getPeriod() {
        return this.PERIOD;
    }

    /**
     * Gets the current periodic transaction state.
     *
     * @return the current periodic transaction state
     */
    public PeriodicTransactionState getState() {
        return this.transactionState;
    }

    public String getCategoryButton() {
        return this.CATEGORY_BUTTON;
    }

    // setters
    /**
     * Sets the current periodic transaction state.
     *
     * @param state the new periodic transaction state
     */
    public void setState(PeriodicTransactionState state) {
        this.transactionState = state;
    }

    /**
     * Notifies listeners that the periodic transaction state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.transactionState);
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
     * Resets the periodic transaction state to default values.
     */
    public void resetState() {
        PeriodicTransactionState newState = new PeriodicTransactionState();
        setState(newState);
    }
}

