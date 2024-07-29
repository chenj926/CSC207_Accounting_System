package interface_adaptors.transaction;

/**
 * The TransactionState class represents the state of a transaction,
 * indicating whether a one-time or periodic transaction is selected.
 *
 * @author Xile
 */
public class TransactionState {
    private boolean isOneTimeSelected;
    private boolean isPeriodicSelected;

    /**
     * Checks if a one-time transaction is selected.
     *
     * @return true if a one-time transaction is selected, false otherwise
     */
    public boolean isOneTimeSelected() {
        return isOneTimeSelected;
    }

    /**
     * Sets the state of the one-time transaction selection.
     *
     * @param oneTimeSelected the new state of the one-time transaction selection
     */
    public void setOneTimeSelected(boolean oneTimeSelected) {
        isOneTimeSelected = oneTimeSelected;
    }

    /**
     * Checks if a periodic transaction is selected.
     *
     * @return true if a periodic transaction is selected, false otherwise
     */
    public boolean isPeriodicSelected() {
        return isPeriodicSelected;
    }

    /**
     * Sets the state of the periodic transaction selection.
     *
     * @param periodicSelected the new state of the periodic transaction selection
     */
    public void setPeriodicSelected(boolean periodicSelected) {
        isPeriodicSelected = periodicSelected;
    }
}
