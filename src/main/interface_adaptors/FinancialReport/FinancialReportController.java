package interface_adaptors.FinancialReport;

import use_case.FinancialReport.FinancialReportInputBoundary;
import use_case.FinancialReport.FinancialReportInputData;
import java.util.Date;

/**
 * Controller for handling financial report generation requests.
 *
 * @author :Chi Fong
 */
public class FinancialReportController {
    private final FinancialReportInputBoundary financialReportInputBoundary;
    private final FinancialReportViewModel viewModel;


    /**
     * Constructs a FinancialReportController with the specified interactor.
     *
     * @param financialReportInputBoundary the use case interactor for generating financial reports
     */
    public FinancialReportController(FinancialReportInputBoundary financialReportInputBoundary, FinancialReportViewModel viewModel) {
        this.financialReportInputBoundary = financialReportInputBoundary;
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
        FinancialReportInputData financialReportInputData = new FinancialReportInputData(identification);
        financialReportInputBoundary.execute(financialReportInputData);
//        this.viewModel.resetState();
    }
}




