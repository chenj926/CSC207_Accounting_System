package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OneTimeTransactionViewModel extends TransactionViewModel {
    private OneTimeTransactionState transactionState;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public OneTimeTransactionViewModel() {
        super("One-Time Transaction");
        this.transactionState = new OneTimeTransactionState();
    }

    public OneTimeTransactionState getState() {
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

