package interface_adaptors.transaction.periodic.user_account;

import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountPeriodicTransactionControllerTest {

    @Test
    void testExecute() {
        UserAccountPeriodicTransactionInputBoundary testInteractor = new UserAccountPeriodicTransactionInputBoundary() {
            @Override
            public void execute(UserAccountPeriodicTransactionInputData inputData) {
                // Verify that the interactor was called with the correct data
                assertEquals("user789", inputData.getId());
                assertEquals("100.00", inputData.getTransactionAmount());
                assertEquals("2023-07-25", inputData.getTransactionStartDate());
                assertEquals("Subscription", inputData.getTransactionDescription());
                assertEquals("Monthly", inputData.getTransactionPeriod());
                assertEquals("2024-07-25", inputData.getTransactionEndDate());
                assertEquals("Entertainment", inputData.getTransactionCategory());
                assertEquals("2023-07-25", inputData.getTransactionDate());
            }
        };

        UserAccountPeriodicTransactionViewModel testViewModel = new UserAccountPeriodicTransactionViewModel();

        // Create the controller with the test implementations
        UserAccountPeriodicTransactionController controller = new UserAccountPeriodicTransactionController(testInteractor, testViewModel);

        // Test data
        String id = "user789";
        String amount = "100.00";
        String startDate = "2023-07-25";
        String description = "Subscription";
        String period = "Monthly";
        String endDate = "2024-07-25";
        String category = "Entertainment";
        String date = "2023-07-25";

        // Execute the method
        controller.execute(id, amount, startDate, description, period, endDate, category, date);

        UserAccountPeriodicTransactionState expectedState = new UserAccountPeriodicTransactionState();
        assertEquals(expectedState.getTransactionAmount(), testViewModel.getState().getTransactionAmount());
        assertEquals(expectedState.getTransactionStartDate(), testViewModel.getState().getTransactionStartDate());
        assertEquals(expectedState.getTransactionDescription(), testViewModel.getState().getTransactionDescription());
        assertEquals(expectedState.getTransactionPeriod(), testViewModel.getState().getTransactionPeriod());
        assertEquals(expectedState.getTransactionEndDate(), testViewModel.getState().getTransactionEndDate());
        assertEquals(expectedState.getErrorMessage(), testViewModel.getState().getErrorMessage());
        assertEquals(expectedState.getSuccessMessage(), testViewModel.getState().getSuccessMessage());

    }
}









