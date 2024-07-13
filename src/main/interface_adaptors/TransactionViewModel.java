package main.interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TransactionViewModel extends ViewModel{

    public final String TITLE_LABEL = "Transaction View";
    public final String PERIODIC_TRANSACTION_BUTTON_LABEL = "Periodic Transaction";
    public final String ONE_TIME_TRANSACTION_BUTTON_LABEL = "One-Time Transaction";
    private OneTimeTransactionViewModel oneTimeTransaction;
    private PeriodicTransactionViewModel periodicTransaction;
    private TransactionState state = new TransactionState();


    public void setState(TransactionState state) {
        this.state = state;
    }

    public OneTimeTransactionViewModel getOneTimeTransaction() {
        return oneTimeTransaction;
    }

    public void setOneTimeTransaction(OneTimeTransactionViewModel oneTimeTransaction) {
        this.oneTimeTransaction = oneTimeTransaction;
    }

    public PeriodicTransactionViewModel getPeriodicTransaction() {
        return periodicTransaction;
    }

    public void setPeriodicTransaction(PeriodicTransactionViewModel periodicTransaction) {
        this.periodicTransaction = periodicTransaction;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }
}