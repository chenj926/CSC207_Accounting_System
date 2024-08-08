package interface_adaptors.transaction.periodic;

import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.PeriodicTransactionInputBoundary;
import use_case.transaction.periodic.PeriodicTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeriodicTransactionControllerTest {

    @Test
    void testExecute() {
        PeriodicTransactionInputBoundary testInteractor = new PeriodicTransactionInputBoundary() {
            @Override
            public void execute(PeriodicTransactionInputData inputData) {
                // Verify that the interactor was called with the correct data
                assertEquals("123", inputData.getId());
                assertEquals("100.00", inputData.getTransactionAmount());
                assertEquals("2023-07-25", inputData.getTransactionStartDate());
                assertEquals("Subscription", inputData.getTransactionDescription());
                assertEquals("Monthly", inputData.getTransactionPeriod());
                assertEquals("2024-07-25", inputData.getTransactionEndDate());
                assertEquals("Entertainment", inputData.getTransactionCategory());
                assertEquals("2023-07-25", inputData.getTransactionDate());
            }
        };

        PeriodicTransactionViewModel testViewModel = new PeriodicTransactionViewModel();

        // Create the controller with the test implementations
        PeriodicTransactionController controller = new PeriodicTransactionController(testInteractor, testViewModel);

        // Test data
        String id = "123";
        String amount = "100.00";
        String startDate = "2023-07-25";
        String description = "Subscription";
        String period = "Monthly";
        String endDate = "2024-07-25";
        String category = "Entertainment";
        String date = "2023-07-25";

        // Execute the method
        controller.execute(id, amount, startDate, description, period, endDate, category, date);

        PeriodicTransactionState expectedState = new PeriodicTransactionState();
        assertEquals(expectedState.getTransactionAmount(), testViewModel.getState().getTransactionAmount());
        assertEquals(expectedState.getTransactionStartDate(), testViewModel.getState().getTransactionStartDate());
        assertEquals(expectedState.getTransactionDescription(), testViewModel.getState().getTransactionDescription());
        assertEquals(expectedState.getTransactionPeriod(), testViewModel.getState().getTransactionPeriod());
        assertEquals(expectedState.getTransactionEndDate(), testViewModel.getState().getTransactionEndDate());
        assertEquals(expectedState.getErrorMsg(), testViewModel.getState().getErrorMsg());
        assertEquals(expectedState.getSuccessMessage(), testViewModel.getState().getSuccessMessage());

    }
}









