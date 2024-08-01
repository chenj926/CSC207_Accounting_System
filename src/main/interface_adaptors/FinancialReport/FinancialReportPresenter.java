package interface_adaptors.FinancialReport;

import use_case.FinancialReport.FinancialReportOutputBoundary;
import use_case.FinancialReport.FinancialReportOutputData;

/**
 * Implements the output boundary to present the financial report.
 *
 * @author :Chi Fong
 */
public class FinancialReportPresenter implements FinancialReportOutputBoundary {
    private String reportContent;

    /**
     * Presents the financial report by storing and printing its content.
     *
     * @param outputData the output data containing the report content
     */
    @Override
    public void presentReport(FinancialReportOutputData outputData) {
        this.reportContent = outputData.getReportContent();
        System.out.println(reportContent);
    }

    /**
     * Returns the report content.
     *
     * @return the report content
     */
    public String getReportContent() {
        return reportContent;
    }
}




