package interface_adaptors.transaction.one_time;

import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionViewModel;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountOneTimeTransactionControllerTest {

    @Test
    void testExecute() {
        // Create a mock interactor to verify that execute is called with the correct data
        UserAccountOneTimeTransactionInputBoundary testInteractor = new UserAccountOneTimeTransactionInputBoundary() {
            @Override
            public void execute(UserAccountOneTimeTransactionInputData inputData) {
                // Verify that the interactor was called with the correct data
                assertEquals("123", inputData.getId());
                assertEquals("50.00", inputData.getTransactionAmount());
                assertEquals("2023-08-01", inputData.getTransactionDate());
                assertEquals("Dinner", inputData.getTransactionDescription());
                assertEquals("Food", inputData.getTransactionCategory());
            }
        };

        // Create a mock view model, if you want to check reset state behavior
        UserAccountOneTimeTransactionViewModel testViewModel = new UserAccountOneTimeTransactionViewModel() {
            @Override
            public void resetState() {
                // Optionally verify if resetState is called, e.g., add a boolean flag and assert its value
            }
        };

        // Create the controller with the mock implementations
        UserAccountOneTimeTransactionController controller = new UserAccountOneTimeTransactionController(testInteractor, testViewModel);

        // Test data
        String id = "123";
        String amount = "50.00";
        String transactionDate = "2023-08-01";
        String transactionDescription = "Dinner";
        String transactionCategory = "Food";

        // Execute the method with the correct parameters, including id
        controller.execute(id, amount, transactionDate, transactionDescription, transactionCategory);
    }
}









