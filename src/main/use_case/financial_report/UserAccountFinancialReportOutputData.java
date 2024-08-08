package use_case.financial_report;

/**
 * Output data for presenting financial reports.
 *
 * @author :Chi Fong
 */
public class UserAccountFinancialReportOutputData {
    private final String reportContent;

    /**
     * Constructs a FinancialReportOutputData object.
     *
     * @param reportContent the content of the financial report
     */
    public UserAccountFinancialReportOutputData(String reportContent) {
        this.reportContent = reportContent;
    }

    /**
     * Returns the content of the financial report.
     *
     * @return the report content
     */
    public String getReportContent() { return reportContent; }
}


