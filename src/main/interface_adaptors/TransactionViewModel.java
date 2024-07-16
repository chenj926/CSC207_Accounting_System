package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TransactionViewModel extends ViewModel {
    public static final String TRANSACTION_PROPERTY = "transaction";
    protected TransactionViewModel currentViewModel;

    public TransactionViewModel(String title) {
        super(title);
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

    public void setCurrentViewModel(TransactionViewModel newViewModel) {
        TransactionViewModel oldViewModel = this.currentViewModel;
        this.currentViewModel = newViewModel;
        firePropertyChanged("currentViewModel", oldViewModel, newViewModel);
    }
}




