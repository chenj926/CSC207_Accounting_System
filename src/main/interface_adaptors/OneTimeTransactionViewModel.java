package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OneTimeTransactionViewModel extends TransactionViewModel {
    private OneTimeTransactionState transactionState;

    public OneTimeTransactionViewModel() {
        super("One-Time Transaction");
        this.transactionState = new OneTimeTransactionState();
    }

    private OneTimeTransactionState state = new OneTimeTransactionState();

    public OneTimeTransactionViewModel() {
        super("One-Time Transaction");
    }

    public void setState(OneTimeTransactionState state) {
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
