package interface_adaptors.financial_report;

import data_access.financial_report_api_accessor.TextToSpeech;
import interface_adaptors.ViewManagerModel;
import use_case.financial_report.UserAccountFinancialReportOutputBoundary;
import use_case.financial_report.UserAccountFinancialReportOutputData;

/**
 * Implements the output boundary to present the financial report.
 *
 * @author Chi Fong, Eric Chen
 */
public class UserAccountFinancialReportPresenter implements UserAccountFinancialReportOutputBoundary {
    private String reportContent;
    private final FinancialReportViewModel viewModel;
    private final ViewManagerModel viewManager;
//    private ViewManagerModel viewManager;

    public UserAccountFinancialReportPresenter(FinancialReportViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Presents the financial report by storing and printing its content.
     *
     * @param outputData the output data containing the report content
     */
    @Override
    public void prepareSuccessView(UserAccountFinancialReportOutputData outputData) {
        FinancialReportState state = this.viewModel.getState();
        this.reportContent = outputData.getReportContent();

        state.setReportContent(reportContent);
        state.setNoTransaction(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setReportContent(state.getReportContent());
        this.viewModel.firePropertyChanged();

        this.viewManager.setActiveViewName(viewModel.getViewName());

        TextToSpeech TTS = new TextToSpeech();
        TTS.speak(reportContent);

    }

    // 如果user还没有transaction，就report说暂无transaction
    @Override
    public void prepareFailView(String noTransaction) {
        FinancialReportState state = this.viewModel.getState();

        state.setReportContent(noTransaction);
        state.setNoTransaction(noTransaction);
        this.viewModel.setState(state);
        this.viewModel.setReportContent(state.getReportContent());
        this.viewModel.firePropertyChanged();

        this.viewManager.setActiveViewName(viewModel.getViewName());
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




