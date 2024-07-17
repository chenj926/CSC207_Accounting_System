package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PeriodicTransactionViewModel extends TransactionViewModel {
    private PeriodicTransactionState transactionState;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PeriodicTransactionViewModel() {
        super("Periodic Transaction");
        this.transactionState = new PeriodicTransactionState();
    }

    public PeriodicTransactionState getState() {
        return transactionState;
    }

    public void notifyPropertyChange() {
        support.firePropertyChange("transaction", null, this);
    }

    @Override
    protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        this.support.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}

