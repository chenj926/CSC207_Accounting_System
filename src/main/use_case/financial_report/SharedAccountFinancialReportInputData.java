package use_case.financial_report;

import use_case.login.SharedAccountLoginInputData;

/**
 * Input data for generating financial reports.
 *
 * @author :Dana
 */
public class SharedAccountFinancialReportInputData extends FinancialReportInputData {

    /**
     * Constructs a FinancialReportInputData object.
     *
     * @param identification the account ID
     */

    public SharedAccountFinancialReportInputData(String identification) {
        super(identification);
    }
}
