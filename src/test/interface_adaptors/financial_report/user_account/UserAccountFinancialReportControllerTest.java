package interface_adaptors.financial_report.user_account;

import interface_adaptors.financial_report.user_account.UserAccountFinancialReportController;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import org.junit.jupiter.api.Test;
import use_case.financial_report.user_account.UserAccountFinancialReportInputBoundary;
import use_case.financial_report.user_account.UserAccountFinancialReportInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountFinancialReportControllerTest {

    @Test
    void testGenerateReport() {
        // Create a mock interactor to verify that execute is called with the correct data
        UserAccountFinancialReportInputBoundary testInteractor = new UserAccountFinancialReportInputBoundary() {
            @Override
            public void execute(UserAccountFinancialReportInputData inputData) {
                // Verify that the interactor was called with the correct data
                assertEquals("shared123", inputData.getIdentification());
            }
        };

        // Create a mock view model, if you want to check reset state behavior
        UserAccountFinancialReportViewModel testViewModel = new UserAccountFinancialReportViewModel() {
            @Override
            public void resetState() {
                // Optionally verify if resetState is called, e.g., add a boolean flag and assert its value
            }
        };

        // Create the controller with the mock implementations
        UserAccountFinancialReportController controller = new UserAccountFinancialReportController(testInteractor, testViewModel);

        // Test data
        String UserAccountId = "shared123";

        // Execute the method with the correct parameters
        controller.execute(UserAccountId);
    }
}

