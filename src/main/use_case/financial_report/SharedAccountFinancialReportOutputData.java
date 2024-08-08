package use_case.financial_report;

/**
 * Output data for presenting financial reports.
 *
 * @author :Dana
 */
public class SharedAccountFinancialReportOutputData extends FinancialReportOutputData {

    /**
     * Constructs a FinancialReportOutputData object.
     *
     * @param reportContent the content of the financial report
     */
    public SharedAccountFinancialReportOutputData(String reportContent) {

        super(reportContent);
    }
}
