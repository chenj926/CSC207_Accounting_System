package use_case.financial_report;

import java.util.Date;

/**
 * Input data for generating financial reports.
 *
 * @author :Chi Fong
 */
public class UserAccountFinancialReportInputData {
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
    public UserAccountFinancialReportInputData(String userName, String id, Date startDate, Date endDate) {
        this.userName = userName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.identification = id;
    }

    public UserAccountFinancialReportInputData(String id) {
        this.identification = id;

        // unused in this constructor
        this.userName = "";
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Returns the account ID.
     *
     * @return the account ID
     */
    public String getUserName() { return userName; }

    /**
     * Returns the account ID.
     *
     * @return the account ID
     */
    public String getIdentification() { return identification; }

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


