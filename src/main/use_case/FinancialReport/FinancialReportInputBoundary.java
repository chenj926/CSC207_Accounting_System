package use_case.FinancialReport;

/**
 * Input boundary for generating financial reports.
 *
 * @author : Chi Fong
 */
public interface FinancialReportInputBoundary {
    /**
     * Generates a financial report.
     *
     * @param inputData the input data for the report
     */
    void execute(FinancialReportInputData inputData);
}


