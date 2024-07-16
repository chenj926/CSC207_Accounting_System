package interface_adaptors;

import java.beans.PropertyChangeSupport;

public class PeriodicTransactionViewModel extends TransactionViewModel {
    public final String TITLE_LABEL = "Periodic Transaction";
    public final String AMOUNT_LABEL = "Transaction Amount";
    public final String START_DATE_LABEL = "Start Date";
    public final String END_DATE_LABEL = "End Date";
    public final String PERIOD_LABEL = "Transaction Period";
    public final String DESCRIPTION_LABEL = "Transaction Description";
    public final String RECORD_BUTTON_LABEL = "Record Transaction";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private float transactionAmount;
    private String transactionStartDate;
    private String transactionEndDate;
    private int transactionPeriod;
    private String transactionDescription;
    private String error;

    public PeriodicTransactionViewModel() {
        super("Periodic Transaction");
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionStartDate() {
        return transactionStartDate;
    }

    public void setTransactionStartDate(String transactionStartDate) {
        this.transactionStartDate = transactionStartDate;
    }

    public String getTransactionEndDate() {
        return transactionEndDate;
    }

    public void setTransactionEndDate(String transactionEndDate) {
        this.transactionEndDate = transactionEndDate;
    }

    public int getTransactionPeriod() {
        return transactionPeriod;
    }

    public void setTransactionPeriod(int transactionPeriod) {
        this.transactionPeriod = transactionPeriod;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("transaction", null, this);
    }
}


