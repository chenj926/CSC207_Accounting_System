package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OneTimeTransactionViewModel extends ViewModel {
    private OneTimeTransactionState state = new OneTimeTransactionState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public OneTimeTransactionViewModel() {
        super("One Time Transaction");
    }

    // getters
    public OneTimeTransactionState getState() {
        return this.state;
    }

    // setters
    public void setState(OneTimeTransactionState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("transaction", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}

