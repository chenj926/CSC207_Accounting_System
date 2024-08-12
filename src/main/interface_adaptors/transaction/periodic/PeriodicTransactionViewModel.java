package interface_adaptors.transaction.periodic;

import interface_adaptors.ViewModel;
import interface_adaptors.transaction.TransactionViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class PeriodicTransactionViewModel<S extends PeriodicTransactionState> extends TransactionViewModel {
    // labels
    protected final String TITLE_LABEL = "Periodic Transaction";
    protected final String STARTDATE = "Transaction Start Date";
    protected final String ENDDATE = "Transaction End Date";
    protected final String PERIOD = "Period";

    protected S transactionState;
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a UserAccountPeriodicTransactionViewModel object with the view name set to "Periodic Transaction".
     */
    public PeriodicTransactionViewModel(String viewName) {
        super(viewName);
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
        return this.ENDDATE;
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
    public S getState() {
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
    public void setState(S state) {
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
    public abstract void resetState();

}


