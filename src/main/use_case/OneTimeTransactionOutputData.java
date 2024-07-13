package use_case;

public class OneTimeTransactionOutputData {

    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String transactionCategory;
    private String transactionNotes;

    private boolean useCaseFailed;

    public OneTimeTransactionOutputData(float transactionAmount, String transactionDate, String transactionDescription,
                                        String transactionCategory, String transactionNotes) {

        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
        this.transactionNotes = transactionNotes;
        this.useCaseFailed = false;

    }
    public float getTransactionAmount() {
            return transactionAmount;
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

    public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }
}