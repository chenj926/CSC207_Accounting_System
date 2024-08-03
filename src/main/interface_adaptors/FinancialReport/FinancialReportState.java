package interface_adaptors.FinancialReport;

public class FinancialReportState {
    private String userName;
    private String identification;
    private String startDate;
    private String endDate;
    private String reportContent;
    private String noTransaction;

    /**
     * Gets the identification
     *
     * @return the identification
     */
    public String getIdentification() { return this.identification; }

    /**
     * Gets the userName
     *
     * @return the userName
     */
    public String getUserName() { return this.userName; }

    /**
     * Gets the startDate
     *
     * @return the startDate
     */
    public String getStartDate() { return this.startDate; }

    /**
     * Gets the endDate
     *
     * @return the endDate
     */
    public String getEndDate() { return this.endDate; }

    public String getReportContent() {
        return this.reportContent;
    }
    public String getNoTransaction() {
        return this.noTransaction;
    }

    /**
     * set the identification
     *
     * @param identification in financial report
     */
    public void setIdentification(String identification) { this.identification = identification; }

    /**
     * set the userName
     *
     * @param userName in financial report
     */
    public void setUserName(String userName) { this.userName = userName; }

    /**
     * set the startDate
     *
     * @param startDate in financial report
     */
    public void setStartDate(String startDate) { this.startDate = startDate; }

    /**
     * set the endDate
     *
     * @param endDate in financial report
     */
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }
    public void setNoTransaction(String noTransaction) {
        this.noTransaction = noTransaction;
    }
}
