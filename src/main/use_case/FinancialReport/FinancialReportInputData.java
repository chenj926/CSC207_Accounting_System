package use_case.FinancialReport;

import java.util.Date;

/**
 * Input data for generating financial reports.
 *
 * Author: Chi Fong Huang
 */
public class FinancialReportInputData {
    private final String accountId;
    private final Date startDate;
    private final Date endDate;

    public FinancialReportInputData(String accountId, Date startDate, Date endDate) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public String getAccountId() { return accountId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
}

