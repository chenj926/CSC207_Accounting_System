package interface_adaptors.FinancialReport;

import data_access.financial_report_api_accessor.TextToSpeech;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportPresenter;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportState;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.financial_report.shared_account.SharedAccountFinancialReportOutputData;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountFinancialReportPresenterTest {

    private SharedAccountFinancialReportViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountFinancialReportPresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountFinancialReportViewModel();
        viewManager = new ViewManagerModel();
        presenter = new SharedAccountFinancialReportPresenter(viewModel, viewManager) {
            @Override
            public void prepareSuccessView(SharedAccountFinancialReportOutputData outputData) {
                super.prepareSuccessView(outputData);
            }
        };
    }

    @Test
    void testPrepareSuccessView() {
        // Create the output data with a mock report content
        String reportContent = "This is a sample financial report.";
        SharedAccountFinancialReportOutputData outputData = new SharedAccountFinancialReportOutputData(reportContent);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        SharedAccountFinancialReportState state = viewModel.getState();
        assertEquals(reportContent, state.getReportContent());
        assertNull(state.getNoTransaction());

        // Verify the view manager changes view
        assertEquals(viewModel.getViewName(), viewManager.getActiveViewName());
    }

    @Test
    void testPrepareSuccessViewNoTransaction() {
        // Create the output data with an empty report content (indicating no transactions)
        String reportContent = "";
        SharedAccountFinancialReportOutputData outputData = new SharedAccountFinancialReportOutputData(reportContent);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        SharedAccountFinancialReportState state = viewModel.getState();
        assertEquals(reportContent, state.getReportContent());
        assertNull(state.getNoTransaction());

        // Verify the view manager changes view
        assertEquals(viewModel.getViewName(), viewManager.getActiveViewName());
    }

    @Test
    void testPrepareFailView() {
        // Test data
        String errorMessage = null;

        // Execute method
        presenter.prepareFailView(errorMessage);

        // Verify the view model state
        SharedAccountFinancialReportState state = viewModel.getState();
        assertEquals(errorMessage, state.getNoTransaction());
        assertNull(state.getReportContent());
    }

    @Test
    void testFinancialReportStateSetters() {
        SharedAccountFinancialReportState state = new SharedAccountFinancialReportState();

        // Test setters
        state.setReportContent("This is a sample financial report.");
        state.setNoTransaction("No transactions found");

        // Verify the values set by the setters
        assertEquals("This is a sample financial report.", state.getReportContent());
        assertEquals("No transactions found", state.getNoTransaction());
    }
}
