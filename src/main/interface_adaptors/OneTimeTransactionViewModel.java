package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OneTimeTransactionViewModel extends TransactionViewModel {
    private String successMessage;
    private String errorMessage;

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

    public boolean validateOneTimeTransaction() {
        if (transactionState.getTransactionAmount() <= 0) {
            transactionState.setError("Transaction amount must be greater than 0");
            return false;
        }
        if (!transactionState.getTransactionDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            transactionState.setError("Transaction date must be in the format YYYY-MM-DD");
            return false;
        }
        if (transactionState.getTransactionDescription().isEmpty()) {
            transactionState.setError("Transaction description cannot be empty");
            return false;
        }
        transactionState.setError(null);
        return true;
    }
}

