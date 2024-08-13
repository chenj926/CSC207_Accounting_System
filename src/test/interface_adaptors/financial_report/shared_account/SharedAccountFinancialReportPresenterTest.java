package interface_adaptors.financial_report.shared_account;

import data_access.financial_report_api_accessor.TextToSpeech;
import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.financial_report.shared_account.SharedAccountFinancialReportOutputData;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountFinancialReportPresenterTest {

    private SharedAccountFinancialReportPresenter presenter;
    private SharedAccountFinancialReportViewModel viewModel;
    private ViewManagerModel viewManager;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountFinancialReportViewModel();
        viewManager = new ViewManagerModel();
        presenter = new SharedAccountFinancialReportPresenter(viewModel, viewManager);
    }

    @Test
    void testPrepareSuccessView() {
        String reportContent = "This is a test financial report for shared account.";
        SharedAccountFinancialReportOutputData outputData = new SharedAccountFinancialReportOutputData(reportContent);

        // Temporarily replace TextToSpeech with a testable version
        TestableTextToSpeech TTS = new TestableTextToSpeech();
        presenter.prepareSuccessView(outputData);

        SharedAccountFinancialReportState state = viewModel.getState();
        assertEquals(reportContent, state.getReportContent());
        assertNull(state.getNoTransaction());
        assertEquals(viewModel.getViewName(), viewManager.getActiveViewName());

        // Verify that the report content was correctly "spoken"
        assertEquals(reportContent, TTS.getSpokenText());
    }

    // A simple class to simulate TextToSpeech for testing purposes
    private static class TestableTextToSpeech extends TextToSpeech {
        private String spokenText;

        @Override
        public void speak(String text) {
            this.spokenText = text;
        }

        public String getSpokenText() {
            return spokenText;
        }
    }
}


