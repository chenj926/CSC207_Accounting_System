package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PeriodicTransactionViewModel extends ViewModel {
    // labels
    private final String TITLE_LABEL = "Periodic Transaction";
    private final String AMOUNT = "Transaction Amount";
    private final String ID = "Identification";
    private final String STARTDATE = "Transaction Start Date";
    private final String ENDDATE = "Transaction End Date";
    private final String DESCRIPTION = "Description";
    private final String PERIOD = "Period";

    private final String SUBMIT_BUTTON = "Submit Transaction";
    private final String CANCEL_BUTTON = "Cancel";

    private PeriodicTransactionState transactionState = new PeriodicTransactionState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PeriodicTransactionViewModel() {
        super("Periodic Transaction");
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
    public String getStartDate() {
        return this.STARTDATE;
    }
    public String getEndDate() {
        return this.ENDDATE; }
    public String getDescription() {
        return this.DESCRIPTION;
    }
    public String getSubmitButton() {
        return this.SUBMIT_BUTTON;
    }
    public String getCancelButton() {
        return this.CANCEL_BUTTON;
    }
    public String getPeriod() {
        return this.PERIOD;
    }
    public PeriodicTransactionState getState() {
        return this.transactionState;
    }

    // setters
    public void setState(PeriodicTransactionState state) {
        this.transactionState = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.transactionState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void resetState() {
        PeriodicTransactionState newState = new PeriodicTransactionState();
        setState(newState);
    }
}

