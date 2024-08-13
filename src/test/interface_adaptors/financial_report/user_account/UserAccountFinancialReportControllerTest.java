package interface_adaptors.financial_report.user_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.financial_report.user_account.UserAccountFinancialReportInputBoundary;
import use_case.financial_report.user_account.UserAccountFinancialReportInputData;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountFinancialReportControllerTest {

    private UserAccountFinancialReportController controller;
    private TestUserAccountFinancialReportInteractor interactor;
    private UserAccountFinancialReportViewModel viewModel;

    @BeforeEach
    void setUp() {
        interactor = new TestUserAccountFinancialReportInteractor();
        viewModel = new UserAccountFinancialReportViewModel();
        controller = new UserAccountFinancialReportController(interactor, viewModel);
    }

    @Test
    void testExecute() {
        String identification = "userAccount123";

        controller.execute(identification);

        // Validate that the interactor received the correct data
        assertNotNull(interactor.inputData);
        assertEquals(identification, interactor.inputData.getIdentification());
    }

    // Inner class to simulate the UserAccountFinancialReportInteractor behavior
    private static class TestUserAccountFinancialReportInteractor implements UserAccountFinancialReportInputBoundary {

        private UserAccountFinancialReportInputData inputData;

        @Override
        public void execute(UserAccountFinancialReportInputData inputData) {
            this.inputData = inputData;  // Capture the input data for testing
        }
    }
}

