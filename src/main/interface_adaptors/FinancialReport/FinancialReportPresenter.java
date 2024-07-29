package interface_adaptors.FinancialReport;

import use_case.FinancialReport.FinancialReportOutputBoundary;
import use_case.FinancialReport.FinancialReportOutputData;

public class FinancialReportPresenter implements FinancialReportOutputBoundary {
    @Override
    public void presentReport(FinancialReportOutputData outputData) {
        // Present the report
        System.out.println(outputData.getReportContent());
    }
}


