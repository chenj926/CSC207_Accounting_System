package interface_adaptors.transaction;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeSupport;

/**
 * The TransactionViewModel class extends the ViewModel class and manages the state and labels for transaction-related views.
 * It provides getters for various labels used in the transaction view and supports property change notifications.
 *
 * @author Xile
 * @author Jessica
 * @author Eric
 */
public class TransactionViewModel extends ViewModel {
    // labels
    protected final String AMOUNT = "Transaction Amount";
    protected final String DESCRIPTION = "Description";
    protected final String CATEGORY_BUTTON = "Transaction Category";
    protected final String SUBMIT_BUTTON = "Submit Transaction";
    protected final String CANCEL_BUTTON = "Cancel";

//    private final TransactionState transactionState = new TransactionState();
//    protected TransactionViewModel currentViewModel;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a TransactionViewModel object with the view name set to "Transaction".
     */
    public TransactionViewModel(String viewName) {
        super(viewName);
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
}






