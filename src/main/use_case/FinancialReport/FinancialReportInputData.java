package use_case.FinancialReport;

import java.util.Date;

/**
 * Input data for generating financial reports.
 *
 * @author :Chi Fong
 */
public class FinancialReportInputData {
    private final String accountId;
    private final Date startDate;
    private final Date endDate;

    /**
     * Constructs a FinancialReportInputData object.
     *
     * @param accountId the account ID
     * @param startDate the start date of the report period
     * @param endDate the end date of the report period
     */
    public FinancialReportInputData(String accountId, Date startDate, Date endDate) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the account ID.
     *
     * @return the account ID
     */
    public String getAccountId() { return accountId; }

    /**
     * Returns the start date of the report period.
     *
     * @return the start date
     */
    public Date getStartDate() { return startDate; }

    /**
     * Returns the end date of the report period.
     *
     * @return the end date
     */
    public Date getEndDate() { return endDate; }
}


