package interface_adaptors.financial_report;

import data_access.financial_report_api_accessor.TextToSpeech;
import interface_adaptors.ViewManagerModel;
import use_case.financial_report.SharedAccountFinancialReportOutputBoundary;
import use_case.financial_report.SharedAccountFinancialReportOutputData;
import use_case.financial_report.UserAccountFinancialReportOutputBoundary;
import use_case.financial_report.UserAccountFinancialReportOutputData;

public class SharedAccountFinancialReportPresenter extends FinancialReportPresenter<
        SharedAccountFinancialReportViewModel,
        SharedAccountFinancialReportState,
        SharedAccountFinancialReportOutputData> implements SharedAccountFinancialReportOutputBoundary {

    public SharedAccountFinancialReportPresenter(SharedAccountFinancialReportViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    @Override
    public void prepareSuccessView(SharedAccountFinancialReportOutputData outputData) {
        SharedAccountFinancialReportState state = this.viewModel.getState();
        this.reportContent = outputData.getReportContent();

        state.setReportContent(reportContent);
        state.setNoTransaction(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setReportContent(state.getReportContent());
        this.viewModel.firePropertyChange();

        this.viewManager.setActiveViewName(viewModel.getViewName());

        TextToSpeech TTS = new TextToSpeech();
        TTS.speak(reportContent);

    }


}
