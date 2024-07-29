package use_case.FinancialReport;

import java.util.Date;

/**
 * Interactor for generating financial reports.
 * Handles the business logic for creating financial reports.
 *
 * Author: Chi Fong Huang
 */
public class FinancialReportInteractor implements FinancialReportInputBoundary {
    private final FinancialReportOutputBoundary outputBoundary;

    public FinancialReportInteractor(FinancialReportOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void generateReport(FinancialReportInputData inputData) {
        // Logic to generate the financial report
        String reportContent = fetchFinancialReport(inputData.getAccountId(), inputData.getStartDate(), inputData.getEndDate());

        FinancialReportOutputData outputData = new FinancialReportOutputData(reportContent);
        outputBoundary.presentReport(outputData);
    }

    /**
     * Fetches the financial report content.
     *
     * @param accountId The ID of the account.
     * @param startDate The start date of the report period.
     * @param endDate   The end date of the report period.
     * @return The financial report content as a string.
     */
    private String fetchFinancialReport(String accountId, Date startDate, Date endDate) {
        // Implementation of fetching and generating the financial report
        // This is a placeholder for the actual implementation
        return "Financial Report for Account ID: " + accountId;
    }
}


