package interface_adaptors;

public class OneTimeTransactionState {
    private String id;
    private String transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String transactionCategory;
    private float newBalance;
    private boolean useCaseFailed;
    private String errorMessage;
    private String successMessage;

    public OneTimeTransactionState() {
        this.id = "";
        this.transactionAmount = "0";
        this.transactionDate = null;
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
    public String getTransactionDate() {
        return this.transactionDate;
    }
    public String getTransactionDescription() {
        return this.transactionDescription;
    }
    public String getTransactionCategory() {
        return this.transactionCategory;
    }
    public float getNewBalance() {
        return this.newBalance;
    }
    public boolean isUseCaseFailed() {
        return this.useCaseFailed;
    }
    public String getErrorMessage() {
        return this.errorMessage;
    }
    public String getSuccessMessage() {
        return this.successMessage;
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
    public void setNewBalance(float newBalance) {
        this.newBalance = newBalance;
    }
    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    public void setErrorMessage(String error) {
        this.errorMessage = error;
    }
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
