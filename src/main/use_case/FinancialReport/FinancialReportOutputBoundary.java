package use_case.FinancialReport;

/**
 * Output boundary interface for presenting financial reports.
 *
 * @author :Chi Fong
 */
public interface FinancialReportOutputBoundary {
    /**
     * Presents the financial report.
     *
     * @param outputData the output data containing the report content
     */
    void presentReport(FinancialReportOutputData outputData);
}

