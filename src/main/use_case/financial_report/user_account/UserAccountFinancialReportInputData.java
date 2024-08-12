package use_case.financial_report.user_account;

import use_case.financial_report.FinancialReportInputData;

/**
 * Input data for generating financial reports.
 *
 * @author :Chi Fong
 * @author :Dana
 */
public class UserAccountFinancialReportInputData extends FinancialReportInputData {

    /**
     * Constructs a FinancialReportInputData object.
     *
     * @param identification the account ID
     */

    public UserAccountFinancialReportInputData(String identification) {
        super(identification);
    }

//    /**
//     * Returns the account ID.
//     *
//     * @return the account ID
//     */
//    public String getIdentification() { return identification; }
//
//    /**
//     * Returns the start date of the report period.
//     *
//     * @return the start date
//     */
//    public Date getStartDate() { return startDate; }
//
//    /**
//     * Returns the end date of the report period.
//     *
//     * @return the end date
//     */
//    public Date getEndDate() { return endDate; }
}


