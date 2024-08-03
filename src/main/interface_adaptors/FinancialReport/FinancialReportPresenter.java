package interface_adaptors.FinancialReport;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;
import use_case.FinancialReport.FinancialReportOutputBoundary;
import use_case.FinancialReport.FinancialReportOutputData;

/**
 * Implements the output boundary to present the financial report.
 *
 * @author :Chi Fong
 */
public class FinancialReportPresenter implements FinancialReportOutputBoundary {
    private String reportContent;
    private final FinancialReportViewModel viewModel;
    private final ViewManagerModel viewManager;

    public FinancialReportPresenter(FinancialReportViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Presents the financial report by storing and printing its content.
     *
     * @param outputData the output data containing the report content
     */
    @Override
    public void prepareSuccessView(FinancialReportOutputData outputData) {
        this.reportContent = outputData.getReportContent();
        System.out.println(reportContent);
    }

    @Override
    public void prepareFailView(String error) {

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




