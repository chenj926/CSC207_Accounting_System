package use_case;

public class OneTimeTransactionOutputData {

    private float newBalance;
    private String transactionDate;
    private String transactionDescription;
    private String transactionCategory;
    private String transactionNotes;

    private boolean useCaseFailed;

    public OneTimeTransactionOutputData(float newBalance, String transactionDate, String transactionDescription,
                                        String transactionCategory, String transactionNotes) {

        this.newBalance = newBalance;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
        this.transactionNotes = transactionNotes;
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

    public String getTransactionNotes() {
            return transactionNotes;
        }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {}

    public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }
}