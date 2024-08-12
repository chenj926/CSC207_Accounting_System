package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewModel;
import interface_adaptors.transaction.TransactionViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AccountOneTimeTransactionViewModel<S extends AccountOneTimeTransactionState> extends TransactionViewModel {
    protected final String TITLE_LABEL = "One Time Transaction";
    protected final String AMOUNT = "Transaction Amount";
    protected final String DATE = "Transaction Date";
    protected final String DESCRIPTION = "Description";
    protected final String CATEGORY_BUTTON = "Transaction Category";

    protected final String SUBMIT_BUTTON = "Submit Transaction";
    protected final String CANCEL_BUTTON = "Cancel";

    protected S state;
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a AccountOneTimeTransactionViewModel object with the view name set to "One Time Transaction".
     */
    public AccountOneTimeTransactionViewModel(String viewName) {
        super(viewName);
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
    public S getState() {
        return this.state;
    }

    /**
     * Sets the current one-time transaction state.
     *
     * @param state the new one-time transaction state
     */
    public void setState(S state) {
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
    public abstract void resetState();

}
