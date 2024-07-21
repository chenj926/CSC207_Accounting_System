package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TransactionViewModel extends ViewModel {
    // labels
    private final String TITLE_LABEL = "Account";

    private final String BALANCE_LABEL = "Total Balance";
    private final String INCOME_LABEL = "Total Income";
    private final String OUTFLOW_LABEL = "Total Outflow";

    private final String ONE_TIME_BUTTON_LABEL = "One Time Transaction";
    private final String PERIODIC_BUTTON_LABEL = "Periodic Transaction";
    private final String CANCEL_BUTTON_LABEL = "Log out";

//    private final TransactionState transactionState = new TransactionState();
//    protected TransactionViewModel currentViewModel;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TransactionViewModel() {
        super("Transaction");
    }

    // label getters
    public String getTITLE_LABEL() {
            return this.TITLE_LABEL;
        }
    public String getBALANCE_LABEL() {
        return this.BALANCE_LABEL;
    }
    public String getINCOME_LABEL() {
        return this.INCOME_LABEL;
    }
    public String getOUTFLOW_LABEL() {
        return this.OUTFLOW_LABEL;
    }
    public String getONE_TIME_BUTTON_LABEL() {
        return this.ONE_TIME_BUTTON_LABEL;
    }
    public String getPERIODIC_BUTTON_LABEL() {
        return this.PERIODIC_BUTTON_LABEL;
    }
    public String getCANCEL_BUTTON_LABEL() {
        return this.CANCEL_BUTTON_LABEL;
    }

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






