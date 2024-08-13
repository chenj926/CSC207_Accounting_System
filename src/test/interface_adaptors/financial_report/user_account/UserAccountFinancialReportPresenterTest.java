package interface_adaptors.financial_report.user_account;

import data_access.financial_report_api_accessor.TextToSpeech;
import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.financial_report.user_account.UserAccountFinancialReportOutputData;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountFinancialReportPresenterTest {

    private UserAccountFinancialReportPresenter presenter;
    private UserAccountFinancialReportViewModel viewModel;
    private ViewManagerModel viewManager;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountFinancialReportViewModel();
        viewManager = new ViewManagerModel();
        presenter = new UserAccountFinancialReportPresenter(viewModel, viewManager);
    }

    @Test
    void testPrepareSuccessView() {
        String reportContent = "This is a test financial report for user account.";
        UserAccountFinancialReportOutputData outputData = new UserAccountFinancialReportOutputData(reportContent);

        // Temporarily replace TextToSpeech with a testable version
        TestableTextToSpeech TTS = new TestableTextToSpeech();
        presenter.prepareSuccessView(outputData);

        UserAccountFinancialReportState state = viewModel.getState();
        assertEquals(reportContent, state.getReportContent());
        assertNull(state.getNoTransaction());
        assertEquals(viewModel.getViewName(), viewManager.getActiveViewName());

        // Verify that the report content was correctly "spoken"
        assertEquals(reportContent, TTS.getSpokenText());
    }

    @Test
    void testPrepareFailView() {
        String noTransactionMessage = "No transactions available.";

        presenter.prepareFailView(noTransactionMessage);

        UserAccountFinancialReportState state = viewModel.getState();
        assertEquals(noTransactionMessage, state.getReportContent());
        assertEquals(noTransactionMessage, state.getNoTransaction());
        assertEquals(viewModel.getViewName(), viewManager.getActiveViewName());
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

