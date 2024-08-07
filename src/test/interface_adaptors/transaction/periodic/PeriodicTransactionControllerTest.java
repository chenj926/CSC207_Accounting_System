package interface_adaptors.transaction.periodic;

import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.PeriodicTransactionInputBoundary;
import use_case.transaction.periodic.PeriodicTransactionInputData;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicTransactionControllerTest {

    @Test
    void testExecute() {
        PeriodicTransactionInputBoundary testInteractor = new PeriodicTransactionInputBoundary() {

            @Override
            public void execute(PeriodicTransactionInputData inputData) {

                // Verify that the interactor was called with the correct data
                assertEquals("100.00", inputData.getTransactionAmount());
                assertEquals("2023-07-25", inputData.getTransactionStartDate());
                assertEquals("Subscription", inputData.getTransactionDescription());
                assertEquals("Monthly", inputData.getTransactionPeriod());
                assertEquals("2024-07-25", inputData.getTransactionEndDate());
            }
        };

        PeriodicTransactionViewModel testViewModel = new PeriodicTransactionViewModel();

        // Create the controller with the simple implementations
        PeriodicTransactionController controller = new PeriodicTransactionController(testInteractor, testViewModel);

        // Test data
        String amount = "100.00";
        String startDate = "2023-07-25";
        String description = "Subscription";
        String period = "Monthly";
        String endDate = "2024-07-25";

        // Execute the method
        controller.execute(amount, startDate, description, period, endDate);

    }
}








