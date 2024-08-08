package interface_adaptors.financial_report;

import use_case.financial_report.UserAccountFinancialReportInputBoundary;
import use_case.financial_report.UserAccountFinancialReportInputData;

/**
 * Controller for handling financial report generation requests.
 *
 * @author :Chi Fong Dana Eric
 */
public class FinancialReportController {
    private final UserAccountFinancialReportInputBoundary userAccountFinancialReportInputBoundary;
    private final FinancialReportViewModel viewModel;


    /**
     * Constructs a FinancialReportController with the specified interactor.
     *
     * @param userAccountFinancialReportInputBoundary the use case interactor for generating financial reports
     */
    public FinancialReportController(UserAccountFinancialReportInputBoundary userAccountFinancialReportInputBoundary, FinancialReportViewModel viewModel) {
        this.userAccountFinancialReportInputBoundary = userAccountFinancialReportInputBoundary;
        this.viewModel = viewModel;
    }

    /**
     * Generates a financial report for the specified account and date range.
     *
//     * @param userName the account ID
//     * @param startDate the start date of the report period
//     * @param endDate the end date of the report period
     */
    public void execute(String identification) {
        UserAccountFinancialReportInputData userAccountFinancialReportInputData = new UserAccountFinancialReportInputData(identification);
        userAccountFinancialReportInputBoundary.execute(userAccountFinancialReportInputData);
//        this.viewModel.resetState();
    }
}




