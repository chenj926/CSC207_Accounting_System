package use_case.financial_report;

/**
 * Output boundary interface for presenting financial reports.
 *
 * @author :Chi Fong
 */
public interface FinancialReportOutputBoundary {
    /**
     * Presents the financial report.
     *
     * @param reportOutputData the output data containing the report content
     */
    void prepareSuccessView(FinancialReportOutputData reportOutputData);

    /**
     * Prepares the fail view with the given error message.
     *
     * @param error the error message to be presented in case of a failed logout attempt
     */
    void prepareFailView(String error);
}

