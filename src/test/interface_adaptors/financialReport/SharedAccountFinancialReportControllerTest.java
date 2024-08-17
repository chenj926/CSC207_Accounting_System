package interface_adaptors.financialReport;

import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportController;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;
import org.junit.jupiter.api.Test;
import use_case.financial_report.shared_account.SharedAccountFinancialReportInputBoundary;
import use_case.financial_report.shared_account.SharedAccountFinancialReportInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SharedAccountFinancialReportControllerTest {

    @Test
    void testGenerateReport() {
        // Create a mock interactor to verify that execute is called with the correct data
        SharedAccountFinancialReportInputBoundary testInteractor = new SharedAccountFinancialReportInputBoundary() {
            @Override
            public void execute(SharedAccountFinancialReportInputData inputData) {
                // Verify that the interactor was called with the correct data
                assertEquals("shared123", inputData.getIdentification());
            }
        };

        // Create a mock view model, if you want to check reset state behavior
        SharedAccountFinancialReportViewModel testViewModel = new SharedAccountFinancialReportViewModel() {
            @Override
            public void resetState() {
                // Optionally verify if resetState is called, e.g., add a boolean flag and assert its value
            }
        };

        // Create the controller with the mock implementations
        SharedAccountFinancialReportController controller = new SharedAccountFinancialReportController(testInteractor, testViewModel);

        // Test data
        String sharedAccountId = "shared123";

        // Execute the method with the correct parameters
        controller.execute(sharedAccountId);
    }
}
