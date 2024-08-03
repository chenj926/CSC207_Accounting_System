package interface_adaptors.FinancialReport;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionState;
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
        FinancialReportState state = this.viewModel.getState();
        this.reportContent = outputData.getReportContent();

        // debug
        System.out.println("presenter"+reportContent);

        state.setReportContent(reportContent);
        System.out.println("presenter\nstate\n"+state.getReportContent());
        state.setNoTransaction(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setReportContent(state.getReportContent());
        this.viewModel.firePropertyChanged();

        this.viewManager.setActiveViewName(viewModel.getViewName());

    }

    // 如果user还没有transaction，就report说暂无transaction
    @Override
    public void prepareFailView(String noTransaction) {
        FinancialReportState state = this.viewModel.getState();
        state.setNoTransaction(noTransaction);
        this.viewModel.setState(state);
        this.viewModel.firePropertyChanged();
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




