package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TransactionViewModel extends ViewModel {
    // labels
    private final String TRANSACTION_VIEW_LABEL = "Transaction";

    private final String ONE_TIME_BUTTON_LABEL = "One Time Transaction";
    private final String PERIODIC_BUTTON_LABEL = "Periodic Transaction";


    private final String AMOUNT_LABEL = "Transaction Amount";
    private final String DATE_LABEL = "Transaction Date";
    private final String DESCRIPTION_LABEL = "Description";
    private final String RECURRENCE_PERIOD_LABEL = "Recurrence Period";

    private final String RECORD_BUTTON_LABEL = "Record Transaction";
    private final String CANCEL_BUTTON_LABEL = "Cancel";

    // label getters
    public String getTransactionViewLabel() {
        return TRANSACTION_VIEW_LABEL;
    }
    public String getOneTimeButtonLabel() {
        return ONE_TIME_BUTTON_LABEL;
    }
    public String getPeriodicButtonLabel() {
        return PERIODIC_BUTTON_LABEL;
    }
    public String getAmountLabel() {
        return AMOUNT_LABEL;
    }
    public String getDateLabel() {
        return DATE_LABEL;
    }
    public String getDescriptionLabel() {
        return DESCRIPTION_LABEL;
    }
    public String getRecurrencePeriodLabel() {
        return RECURRENCE_PERIOD_LABEL;
    }
    public String getRecordButtonLabel() {
        return RECORD_BUTTON_LABEL;
    }
    public String getCancelButtonLabel() {
        return CANCEL_BUTTON_LABEL;
    }


    private final TransactionState transactionState = new TransactionState();
    protected TransactionViewModel currentViewModel;

    public TransactionViewModel(String title) {
        super(title);
    }

    public TransactionViewModel getCurrentViewModel() {
        return currentViewModel;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }


    // Method to handle button click for one-time transaction
    public void selectOneTimeTransaction() {
        transactionState.setOneTimeSelected(true);
        transactionState.setPeriodicSelected(false);
        setCurrentViewModel(new OneTimeTransactionViewModel());
    }

    // Method to handle button click for periodic transaction
    public void selectPeriodicTransaction() {
        transactionState.setOneTimeSelected(false);
        transactionState.setPeriodicSelected(true);
        setCurrentViewModel(new PeriodicTransactionViewModel());
    }

    public void setCurrentViewModel(TransactionViewModel newViewModel) {
        TransactionViewModel oldViewModel = this.currentViewModel;
        this.currentViewModel = newViewModel;
        firePropertyChanged("currentViewModel", oldViewModel, newViewModel);
    }

    public void resetTransactionView() {
        transactionState.setOneTimeSelected(false);
        transactionState.setPeriodicSelected(false);
        setCurrentViewModel(null);
    }

    public void cancelTransaction() {
        setCurrentViewModel(null); // This can be adjusted based on how you want to clear the view
        firePropertyChanged("currentViewModel", null, this);
    }
}






