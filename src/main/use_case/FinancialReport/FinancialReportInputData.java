package use_case.FinancialReport;

import java.util.Date;

/**
 * Input data for generating financial reports.
 *
 * @author :Chi Fong
 */
public class FinancialReportInputData {
    private final String userName;
    private final String identification;

    private final Date startDate;
    private final Date endDate;

    /**
     * Constructs a FinancialReportInputData object.
     *
     * @param userName the account ID
     * @param startDate the start date of the report period
     * @param endDate the end date of the report period
     */
    public FinancialReportInputData(String userName, String id, Date startDate, Date endDate) {
        this.userName = userName;
        this.identification = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the account ID.
     *
     * @return the account ID
     */
    public String getUserName() { return this.userName; }

    public String getIdentification() {
        return this.identification;
    }

    /**
     * Returns the start date of the report period.
     *
     * @return the start date
     */
    public Date getStartDate() { return this.startDate; }

    /**
     * Returns the end date of the report period.
     *
     * @return the end date
     */
    public Date getEndDate() { return this.endDate; }
}


