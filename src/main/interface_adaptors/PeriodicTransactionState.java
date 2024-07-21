package interface_adaptors;

public class PeriodicTransactionState {
    private String transactionAmount;
    private String transactionStartDate;
    private String transactionEndDate;
    private String transactionPeriod;
    private String transactionDescription;
    private String successMessage;
    private String errorMsg;

    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionStartDate() {
        return this.transactionStartDate;
    }

    public void setTransactionStartDate(String transactionStartDate) {
        this.transactionStartDate = transactionStartDate;
    }

    public String getTransactionEndDate() {
        return this.transactionEndDate;
    }

    public void setTransactionEndDate(String transactionEndDate) {
        this.transactionEndDate = transactionEndDate;
    }

    public String getTransactionPeriod() {
        return this.transactionPeriod;
    }

    public void setTransactionPeriod(String transactionPeriod) {
        this.transactionPeriod = transactionPeriod;
    }

    public String getTransactionDescription() {
        return this.transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getSuccessMessage() {
        return this.successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setError(String error) {
        this.errorMsg = error;
    }
}

