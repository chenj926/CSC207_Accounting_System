package interface-adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PeriodicTransactionViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Periodic Transaction";
    public static final String TRANSACTION_AMOUNT_LABEL = "Transaction Amount";
    public static final String TRANSACTION_DATE_LABEL = "Transaction Date";
    public static final String TRANSACTION_DESCRIPTION_LABEL = "Transaction Description";
    public static final String RECURRENCE_LABEL = "Recurrence";

    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String recurrence;
    private final PropertyChangeSupport propertyChangeSupport;

    public PeriodicTransactionViewModel(String viewName) {
        super(viewName);
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        float oldTransactionAmount = this.transactionAmount;
        this.transactionAmount = transactionAmount;
        propertyChangeSupport.firePropertyChange("transactionAmount", oldTransactionAmount, this.transactionAmount);
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        String oldTransactionDate = this.transactionDate;
        this.transactionDate = transactionDate;
        propertyChangeSupport.firePropertyChange("transactionDate", oldTransactionDate, this.transactionDate);
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        String oldTransactionDescription = this.transactionDescription;
        this.transactionDescription = transactionDescription;
        propertyChangeSupport.firePropertyChange("transactionDescription", oldTransactionDescription, this.transactionDescription);
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        String oldRecurrence = this.recurrence;
        this.recurrence = recurrence;
        propertyChangeSupport.firePropertyChange("recurrence", oldRecurrence, this.recurrence);
    }

    @Override
    public void firePropertyChanged() {
        // This method is not needed as the PropertyChangeSupport class
        // handles the firing of property changes
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}