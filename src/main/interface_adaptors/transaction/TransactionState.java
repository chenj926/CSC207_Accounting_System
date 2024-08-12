package interface_adaptors.transaction;


public abstract class TransactionState {
    protected String id;
    protected String transactionAmount;
    protected String transactionDescription;
    protected String transactionCategory;
    protected String successMessage;
    protected String errorMessage;

    public TransactionState(){
        this.id = "";
        this.transactionAmount = "0";
        this.transactionDescription = "";
        this.transactionCategory = "";
        this.errorMessage = null;
        this.successMessage = null;
    }

    // getters
    public String getId() {
        return this.id;
    }
    public String getTransactionAmount() {
        return this.transactionAmount;
    }
    public String getTransactionDescription() {
        return this.transactionDescription;
    }
    public String getTransactionCategory() {
        return this.transactionCategory;
    }
    public String getSuccessMessage() {
        return this.successMessage;
    }
    public String getErrorMessage() {
        return this.errorMessage;
    }

    // setters

    public void setId(String id) {
        this.id = id;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

