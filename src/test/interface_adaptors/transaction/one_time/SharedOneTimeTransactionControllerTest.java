package interface_adaptors.transaction.one_time;

import interface_adaptors.transaction.one_time.shared_account.SharedOneTimeTransactionController;
import interface_adaptors.transaction.one_time.shared_account.SharedOneTimeTransactionViewModel;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SharedOneTimeTransactionControllerTest {

    @Test
    void testExecute() {
        // Create a mock interactor to verify that execute is called with the correct data
        SharedAccountOneTimeTransactionInputBoundary testInteractor = new SharedAccountOneTimeTransactionInputBoundary() {
            @Override
            public void execute(SharedAccountOneTimeTransactionInputData inputData) {
                // Verify that the interactor was called with the correct data
                assertEquals("50.00", inputData.getTransactionAmount());
                assertEquals("2023-08-01", inputData.getTransactionDate());
                assertEquals("Dinner", inputData.getTransactionDescription());
                assertEquals("Food", inputData.getTransactionCategory());
                assertEquals("shared123", inputData.getSharedAccountId());
                assertEquals("user456", inputData.getId());
            }
        };

        // Create a mock view model, if you want to check reset state behavior
        SharedOneTimeTransactionViewModel testViewModel = new SharedOneTimeTransactionViewModel() {
            @Override
            public void resetState() {
                // Optionally verify if resetState is called, e.g., add a boolean flag and assert its value
            }
        };

        // Create the controller with the mock implementations
        SharedOneTimeTransactionController controller = new SharedOneTimeTransactionController(testInteractor, testViewModel);

        // Test data
        String amount = "50.00";
        String transactionDate = "2023-08-01";
        String transactionDescription = "Dinner";
        String transactionCategory = "Food";
        String sharedAccountId = "shared123";
        String userId = "user456";

        // Execute the method with the correct parameters
        controller.execute(amount, transactionDate, transactionDescription, transactionCategory, sharedAccountId, userId);
    }
}

