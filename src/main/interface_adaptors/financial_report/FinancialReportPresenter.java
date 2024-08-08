package interface_adaptors.financial_report;

import interface_adaptors.ViewManagerModel;
import use_case.financial_report.FinancialReportOutputData;
import use_case.financial_report.UserAccountFinancialReportOutputData;


public abstract class FinancialReportPresenter<
        V extends FinancialReportViewModel<S>,
        S extends FinancialReportState,
        O extends FinancialReportOutputData> {
    protected V viewModel;
    protected final ViewManagerModel viewManager;

    protected String reportContent;

    public FinancialReportPresenter(V viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }


    /**
     * Presents the financial report by storing and printing its content.
     *
     * @param outputData the output data containing the report content
     */
    public void prepareSuccessView(O outputData) {
        this.reportContent = outputData.getReportContent();
        S state = (S) viewModel.getState();
    }

    public void prepareFailView(String error){
    S state = (S) viewModel.getState();
    state.setReportContent(error);
    state.setNoTransaction(error);
    viewModel.setState(state);
    viewModel.setReportContent(state.getReportContent());
    viewModel.firePropertyChange();

    viewManager.setActiveViewName(viewModel.getViewName());
    }
}

