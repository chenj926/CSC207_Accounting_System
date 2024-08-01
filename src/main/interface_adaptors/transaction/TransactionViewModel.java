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
    private final String TITLE_LABEL = "Account";

    private final String BALANCE_LABEL = "Total Balance";
    private final String INCOME_LABEL = "Total Income";
    private final String OUTFLOW_LABEL = "Total Outflow";

    private final String ONE_TIME_BUTTON_LABEL = "One Time Transaction";
    private final String PERIODIC_BUTTON_LABEL = "Periodic Transaction";
    private final String CANCEL_BUTTON_LABEL = "Log out";
    private final String TRANSACTION_REPORT_BUTTON_LABEL = "Transaction Report";

//    private final TransactionState transactionState = new TransactionState();
//    protected TransactionViewModel currentViewModel;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a TransactionViewModel object with the view name set to "Transaction".
     */
    public TransactionViewModel() {
        super("Transaction");
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTITLE_LABEL() {
            return this.TITLE_LABEL;
        }

    /**
     * Gets the balance label.
     *
     * @return the balance label
     */
    public String getBALANCE_LABEL() {
        return this.BALANCE_LABEL;
    }

    /**
     * Gets the income label.
     *
     * @return the income label
     */
    public String getINCOME_LABEL() {
        return this.INCOME_LABEL;
    }

    /**
     * Gets the outflow label.
     *
     * @return the outflow label
     */
    public String getOUTFLOW_LABEL() {
        return this.OUTFLOW_LABEL;
    }

    /**
     * Gets the label for the one-time transaction button.
     *
     * @return the label for the one-time transaction button
     */
    public String getONE_TIME_BUTTON_LABEL() {
        return this.ONE_TIME_BUTTON_LABEL;
    }

    /**
     * Gets the label for the periodic transaction button.
     *
     * @return the label for the periodic transaction button
     */
    public String getPERIODIC_BUTTON_LABEL() {
        return this.PERIODIC_BUTTON_LABEL;
    }

    /**
     * Gets the label for the cancel button.
     *
     * @return the label for the cancel button
     */
    public String getCANCEL_BUTTON_LABEL() {
        return this.CANCEL_BUTTON_LABEL;
    }

    /**
     * Gets the label for the history button.
     *
     * @return the label for the history button
     */
    public String getTRANSACTION_REPORT_BUTTON_LABEL() {return this.TRANSACTION_REPORT_BUTTON_LABEL;}

//    // Method to handle button click for one-time transaction
//    public void selectOneTimeTransaction() {
//        transactionState.setOneTimeSelected(true);
//        transactionState.setPeriodicSelected(false);
//        setCurrentViewModel(new OneTimeTransactionViewModel());
//    }
//
//    // Method to handle button click for periodic transaction
//    public void selectPeriodicTransaction() {
//        transactionState.setOneTimeSelected(false);
//        transactionState.setPeriodicSelected(true);
//        setCurrentViewModel(new PeriodicTransactionViewModel());
//    }
//
//    public void setCurrentViewModel(TransactionViewModel newViewModel) {
//        TransactionViewModel oldViewModel = this.currentViewModel;
//        this.currentViewModel = newViewModel;
//        firePropertyChanged("currentViewModel", oldViewModel, newViewModel);
//    }
//
//    public void resetTransactionView() {
//        transactionState.setOneTimeSelected(false);
//        transactionState.setPeriodicSelected(false);
//        setCurrentViewModel(null);
//    }
//
//    public void cancelTransaction() {
//        setCurrentViewModel(null); // This can be adjusted based on how you want to clear the view
//        firePropertyChanged("currentViewModel", null, this);
//    }
}






