package use_case;

public class OneTimeTransactionOutputData {

    private float transactionAmount;
    private String TransactionDate;
    private String TransactionDescription;
    private String TransactionCategory;
    private String TransactionNotes;

    private boolean useCaseFailed;

    public OneTimeTransactionOutputData(float transactionAmount, String transactionDate, String transactionDescription,
                                        String transactionCategory, String transactionNotes) {

        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
        this.TransactionNotes = TransactionNotes;
        this.useCaseFailed = false;

        public float getTransactionAmount() {
            return transactionAmount;
        }

        public String getTransactionDate() {
            return TransactionDate
        }

        public String getTransactionDescription() {
            return transactionDescription;
        }

        public String getTransactionCategory() {
            return transactionCategory;
        }

        public String getTransactionNotes() {
            return TransactionNotes;
        }

        public void setTransactionDate(String transactionDate) {
            this.TransactionDate = transactionDate
        }
    }
}