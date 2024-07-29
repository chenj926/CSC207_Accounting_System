package interface_adaptors.FinancialReport;

import use_case.FinancialReport.FinancialReportOutputBoundary;
import use_case.FinancialReport.FinancialReportOutputData;
/**
 * Presenter for displaying the generated financial report.
 *
 * Author: Chi Fong Huang
 */
public class FinancialReportPresenter implements FinancialReportOutputBoundary {
    @Override
    public void presentReport(FinancialReportOutputData outputData) {
        // Present the report (e.g., display it in the UI)
        System.out.println(outputData.getReportContent());
    }
}



