package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PeriodicTransactionViewModel extends TransactionViewModel {
    private PeriodicTransactionState transactionState;

    public PeriodicTransactionViewModel() {
        super("Periodic Transaction");
        this.transactionState = new PeriodicTransactionState();
    }

    private PeriodicTransactionState state = new PeriodicTransactionState();

    public PeriodicTransactionViewModel() {
        super("Periodic Transaction");
    }

    public void setState(PeriodicTransactionState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("transaction", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public OneTimeTransactionState getState() {
        return state;
    }
}
