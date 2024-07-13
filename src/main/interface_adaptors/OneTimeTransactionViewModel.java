package interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OneTimeTransactionViewModel extends TransactionViewModel {

    public final String TITLE_LABEL = "One-Time Transaction";
    public final String AMOUNT_LABEL = "Transaction Amount";
    public final String DATE_LABEL = "Transaction Date";
    public final String DESCRIPTION_LABEL = "Transaction Description";
    public final String CATEGORY_LABEL = "Transaction Category";
    public final String NOTES_LABEL = "Transaction Notes";
    public final String RECORD_BUTTON_LABEL = "Record Transaction";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private OneTimeTransactionState state = new OneTimeTransactionState();

    public OneTimeTransactionViewModel() {
        super("One-Time Transaction");
    }

    public void setState(OneTimeTransactionState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public OneTimeTransactionState getState() {
        return state;
    }
}
>>>>>>> origin/interface-adaptors
