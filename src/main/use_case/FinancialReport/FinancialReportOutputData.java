package use_case.FinancialReport;

/**
 * Output data for presenting financial reports.
 *
 * Author: Chi Fong Huang
 */
public class FinancialReportOutputData {
    private final String reportContent;

    public FinancialReportOutputData(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getReportContent() { return reportContent; }
}

