package interface_adaptors.FinancialReport;

import use_case.FinancialReport.FinancialReportOutputBoundary;
import use_case.FinancialReport.FinancialReportOutputData;

/**
 * The FinancialReportPresenter class implements the output boundary to present the report.
 *
 * Author: Chi Fong Huang
 */
public class FinancialReportPresenter implements FinancialReportOutputBoundary {
    private String reportContent;

    @Override
    public void presentReport(FinancialReportOutputData outputData) {
        this.reportContent = outputData.getReportContent();
        System.out.println(reportContent);
    }
    // test purpose
    public String getReportContent() {
        return reportContent;
    }
}



