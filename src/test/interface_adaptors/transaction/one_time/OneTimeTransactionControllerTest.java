package interface_adaptors.transaction.one_time;

import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.OneTimeTransactionInputBoundary;
import use_case.transaction.one_time.OneTimeTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneTimeTransactionControllerTest {

    @Test
    void testExecute() {
        OneTimeTransactionInputBoundary testInteractor = new OneTimeTransactionInputBoundary() {
            @Override
            public void execute(OneTimeTransactionInputData inputData) {
                // Verify that the interactor was called with the correct data
                assertEquals("50.00", inputData.getTransactionAmount());
                assertEquals("2023-08-01", inputData.getTransactionDate());
                assertEquals("Dinner", inputData.getTransactionDescription());
                assertEquals("Food", inputData.getTransactionCategory());
            }
        };

        OneTimeTransactionViewModel testViewModel = new OneTimeTransactionViewModel() {
            @Override
            public void resetState() {
                // Optionally verify if resetState is called
                // e.g., add a boolean flag and assert its value
            }
        };

        // Create the controller with the mock implementations
        OneTimeTransactionController controller = new OneTimeTransactionController(testInteractor, testViewModel);

        // Test data
        String amount = "50.00";
        String transactionDate = "2023-08-01";
        String transactionDescription = "Dinner";
        String transactionCategory = "Food";

        // Execute the method
        controller.execute(amount, transactionDate, transactionDescription, transactionCategory);
    }
}








