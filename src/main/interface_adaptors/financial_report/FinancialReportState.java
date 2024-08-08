package interface_adaptors.financial_report;

public abstract class FinancialReportState {
    private String identification;
    private String reportContent;
    private String noTransaction;

    /**
     * Constructs a FinancialReportState object with default values.
     */
    public FinancialReportState(){
    this.identification = "";
    this.reportContent = "";
    this.noTransaction = "";
    }

    /**
     * Gets the transaction ID.
     *
     * @return the transaction ID
     */
    public String getIdentification() {
        return this.identification;
    }
    /**
     * Sets the transaction ID.
     *
     * @param identification the new transaction ID
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Gets the transaction report.
     *
     * @return the transaction report
     */
    public String getReportContent() {
        return this.reportContent;
    }
    /**
     * Sets the transaction report.
     *
     * @param reportContent the new transaction report
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    /**
     * Gets the empty transaction message.
     *
     * @return the transaction message
     */
    public String getNoTransaction() {
        return this.noTransaction;
    }

    /**
     * Sets the no transaction.
     *
     * @param noTransaction the empty transaction
     */
    public void setNoTransaction(String noTransaction) {
        this.noTransaction = noTransaction;
    }
}
