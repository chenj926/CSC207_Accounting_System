package interface_adaptors.financial_report.shared_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.financial_report.shared_account.SharedAccountFinancialReportInputBoundary;
import use_case.financial_report.shared_account.SharedAccountFinancialReportInputData;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountFinancialReportControllerTest {

    private SharedAccountFinancialReportController controller;
    private TestSharedAccountFinancialReportInteractor interactor;
    private SharedAccountFinancialReportViewModel viewModel;

    @BeforeEach
    void setUp() {
        interactor = new TestSharedAccountFinancialReportInteractor();
        viewModel = new SharedAccountFinancialReportViewModel();
        controller = new SharedAccountFinancialReportController(interactor, viewModel);
    }

    @Test
    void testExecute() {
        String identification = "sharedAccount123";

        controller.execute(identification);

        // Validate that the interactor received the correct data
        assertNotNull(interactor.inputData);
        assertEquals(identification, interactor.inputData.getIdentification());
    }

    // Inner class to simulate the SharedAccountFinancialReportInteractor behavior
    private static class TestSharedAccountFinancialReportInteractor implements SharedAccountFinancialReportInputBoundary {

        private SharedAccountFinancialReportInputData inputData;

        @Override
        public void execute(SharedAccountFinancialReportInputData inputData) {
            this.inputData = inputData;  // Capture the input data for testing
        }
    }
}

