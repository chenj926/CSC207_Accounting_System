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
    private final FinancialReportInputBoundary interactor;

    /**
     * Constructs a FinancialReportController with the specified interactor.
     *
     * @param interactor the interactor for generating financial reports
     */
    public FinancialReportController(FinancialReportInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Generates a financial report for the specified account and date range.
     *
     * @param accountId the account ID
     * @param startDate the start date of the report period
     * @param endDate the end date of the report period
     */
    public void generateReport(String accountId, Date startDate, Date endDate) {
        FinancialReportInputData inputData = new FinancialReportInputData(accountId, startDate, endDate);
        interactor.generateReport(inputData);
    }
}




