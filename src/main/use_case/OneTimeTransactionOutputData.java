package use_case;

public class OneTimeTransactionOutputData {

    private float newBalance;
    private String transactionDate;
    private String transactionDescription;
    private String transactionCategory;

    private boolean useCaseFailed;

    public OneTimeTransactionOutputData(float newBalance, String transactionDate, String transactionDescription,
                                        String transactionCategory) {

        this.newBalance = newBalance;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;

        this.useCaseFailed = false;

    }
    public float getNewBalance() {
            return newBalance;
        }

    public String getTransactionDate() {
        return transactionDate;
        }

    public String getTransactionDescription() {
            return transactionDescription;
        }

    public String getTransactionCategory() {
            return transactionCategory;
        }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {}

    public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }
}