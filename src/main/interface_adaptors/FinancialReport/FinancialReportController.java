package interface_adaptors.FinancialReport;

import use_case.FinancialReport.FinancialReportInputBoundary;
import use_case.FinancialReport.FinancialReportInputData;
import java.util.Date;

/**
 * Controller for handling financial report generation requests.
 *
 * This class serves as an intermediary between the user interface and the financial report use case.
 * It takes the user's input, creates the necessary data structure, and invokes the use case interactor
 * to generate the financial report.
 *
 * Author: Chi Fong Huang
 */
public class FinancialReportController {
    private final FinancialReportInputBoundary interactor;

    /**
     * Constructs a FinancialReportController with the specified use case interactor.
     *
     * @param interactor the interactor that handles the logic for generating financial reports
     */
    public FinancialReportController(FinancialReportInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Initiates the generation of a financial report for the specified account and date range.
     *
     * This method creates a FinancialReportInputData object with the given account ID, start date,
     * and end date, and then passes it to the interactor to generate the financial report.
     *
     * @param accountId the ID of the account for which the financial report is to be generated
     * @param startDate the start date of the period for which the report is to be generated
     * @param endDate the end date of the period for which the report is to be generated
     */
    public void generateReport(String accountId, Date startDate, Date endDate) {
        FinancialReportInputData inputData = new FinancialReportInputData(accountId, startDate, endDate);
        interactor.generateReport(inputData);
    }
}



