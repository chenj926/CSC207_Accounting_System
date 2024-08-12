package interface_adaptors.FinancialReport;

import data_access.financial_report_api_accessor.TextToSpeech;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportPresenter;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportState;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.financial_report.user_account.UserAccountFinancialReportOutputData;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountFinancialReportPresenterTest {

    private UserAccountFinancialReportViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountFinancialReportPresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountFinancialReportViewModel();
        viewManager = new ViewManagerModel();
        presenter = new UserAccountFinancialReportPresenter(viewModel, viewManager) {
            @Override
            public void prepareSuccessView(UserAccountFinancialReportOutputData outputData) {
                super.prepareSuccessView(outputData);
            }
        };
    }

    @Test
    void testPrepareSuccessView() {
        // Create the output data with a mock report content
        String reportContent = "This is a sample financial report.";
        UserAccountFinancialReportOutputData outputData = new UserAccountFinancialReportOutputData(reportContent);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        UserAccountFinancialReportState state = viewModel.getState();
        assertEquals(reportContent, state.getReportContent());
        assertNull(state.getNoTransaction());

        // Verify the view manager changes view
        assertEquals(viewModel.getViewName(), viewManager.getActiveViewName());
    }

    @Test
    void testPrepareSuccessViewNoTransaction() {
        // Create the output data with an empty report content (indicating no transactions)
        String reportContent = "";
        UserAccountFinancialReportOutputData outputData = new UserAccountFinancialReportOutputData(reportContent);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        UserAccountFinancialReportState state = viewModel.getState();
        assertEquals(reportContent, state.getReportContent());
        assertNull(state.getNoTransaction());

        // Verify the view manager changes view
        assertEquals(viewModel.getViewName(), viewManager.getActiveViewName());
    }

    @Test
    void testPrepareFailView() {
        // Test data
        String errorMessage = "No transactions available.";

        // Execute method
        presenter.prepareFailView(errorMessage);

        // Verify the view model state
        UserAccountFinancialReportState state = viewModel.getState();
        assertEquals(errorMessage, state.getNoTransaction());
        assertEquals(errorMessage, state.getReportContent());
    }

    @Test
    void testFinancialReportStateSetters() {
        UserAccountFinancialReportState state = new UserAccountFinancialReportState();

        // Test setters
        state.setReportContent("This is a sample financial report.");
        state.setNoTransaction("No transactions found");

        // Verify the values set by the setters
        assertEquals("This is a sample financial report.", state.getReportContent());
        assertEquals("No transactions found", state.getNoTransaction());
    }

    @Test
    void testGetReportContent() {
        // Set the report content in the presenter
        String reportContent = "Final Report Content";
        presenter.prepareSuccessView(new UserAccountFinancialReportOutputData(reportContent));

        // Verify that the getReportContent method returns the correct content
        assertEquals(reportContent, presenter.getReportContent());
    }
}
