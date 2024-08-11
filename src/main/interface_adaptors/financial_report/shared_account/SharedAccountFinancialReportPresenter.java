package interface_adaptors.financial_report.shared_account;

import data_access.financial_report_api_accessor.TextToSpeech;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.FinancialReportPresenter;
import use_case.financial_report.shared_account.SharedAccountFinancialReportOutputBoundary;
import use_case.financial_report.shared_account.SharedAccountFinancialReportOutputData;

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
