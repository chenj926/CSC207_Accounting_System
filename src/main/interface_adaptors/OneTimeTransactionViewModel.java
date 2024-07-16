package interface_adaptors;

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

    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private float newBalance;
    private String transactionCategory;
    private boolean useCaseFailed;
    private String error;

    public OneTimeTransactionViewModel() {
        super("One-Time Transaction");
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public float getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(float newBalance) {
        this.newBalance = newBalance;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
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







