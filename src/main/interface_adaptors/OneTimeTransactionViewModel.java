package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OneTimeTransactionViewModel extends ViewModel {
    // labels
    private final String TITLE_LABEL = "One Time Transaction";
    private final String AMOUNT = "Transaction Amount";
    private final String ID = "Identification";
    private final String DATE = "Transaction Date";
    private final String DESCRIPTION = "Description";
    private final String CATEGORY = "Transaction Category";
    private final String SUBMIT_BUTTON = "Submit Transaction";
    private final String CANCEL_BUTTON = "Cancel";

    private OneTimeTransactionState state = new OneTimeTransactionState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public OneTimeTransactionViewModel() {
        super("One Time Transaction");
    }

    // getters
    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }
    public String getAmount() {
        return this.AMOUNT;
    }
    public String getId() {
        return this.ID;
    }
    public String getDate() {
        return this.DATE;
    }
    public String getDescription() {
        return this.DESCRIPTION;
    }
    public String getCategory() {
        return this.CATEGORY;
    }
    public String getSubmitButton() {
        return this.SUBMIT_BUTTON;
    }
    public String getCancelButton() {
        return this.CANCEL_BUTTON;
    }
    public OneTimeTransactionState getState() {
        return this.state;
    }

    // setters
    public void setState(OneTimeTransactionState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void resetState() {
        OneTimeTransactionState newState = new OneTimeTransactionState();
        setState(newState);
    }

}

