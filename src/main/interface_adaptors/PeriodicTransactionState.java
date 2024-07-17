package interface_adaptors;

public class PeriodicTransactionState {
    private float transactionAmount;
    private String transactionStartDate;
    private String transactionEndDate;
    private int transactionPeriod;
    private String transactionDescription;
    private String successMessage;
    private String error;

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

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

