package use_case.FinancialReport;

import java.util.Date;

/**
 * Input data for generating financial reports.
 *
 * @author :Chi Fong
 */
public class FinancialReportInputData {
    private final String userName;

    private final Date startDate;
    private final Date endDate;

    /**
     * Constructs a FinancialReportInputData object.
     *
     * @param userName the account ID
     * @param startDate the start date of the report period
     * @param endDate the end date of the report period
     */
    public FinancialReportInputData(String userName, Date startDate, Date endDate) {
        this.userName = userName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the account ID.
     *
     * @return the account ID
     */
    public String getUserName() { return userName; }

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


