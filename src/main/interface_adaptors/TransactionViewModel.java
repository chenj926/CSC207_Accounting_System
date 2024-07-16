package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TransactionViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String title;
    private TransactionViewModel currentViewModel;

    public final String ONE_TIME_BUTTON_LABEL = "One-Time Transaction";
    public final String PERIODIC_BUTTON_LABEL = "Periodic Transaction";

    public TransactionViewModel(String title) {
        this.title = title;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void setCurrentViewModel(TransactionViewModel newViewModel) {
        TransactionViewModel oldViewModel = this.currentViewModel;
        this.currentViewModel = newViewModel;
        firePropertyChange("currentViewModel", oldViewModel, newViewModel);
    }

    public TransactionViewModel getCurrentViewModel() {
        return currentViewModel;
    }

    // Method to handle button click for one-time transaction
    public void selectOneTimeTransaction() {
        setCurrentViewModel(new OneTimeTransactionViewModel());
    }

    // Method to handle button click for periodic transaction
    public void selectPeriodicTransaction() {
        setCurrentViewModel(new PeriodicTransactionViewModel());
    }
}




