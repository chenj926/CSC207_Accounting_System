package interface_adaptors.transaction.periodic;

import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionState;
import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SharedAccountPeriodicTransactionControllerTest {

    @Test
    void testExecute() {
        SharedAccountPeriodicTransactionInputBoundary testInteractor = new SharedAccountPeriodicTransactionInputBoundary() {
            @Override
            public void execute(SharedAccountPeriodicTransactionInputData inputData) {
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

        SharedAccountPeriodicTransactionViewModel testViewModel = new SharedAccountPeriodicTransactionViewModel();

        // Create the controller with the test implementations
        SharedAccountPeriodicTransactionController controller = new SharedAccountPeriodicTransactionController(testInteractor, testViewModel);

        // Test data
        String id = "user789";
        String amount = "100.00";
        String startDate = "2023-07-25";
        String description = "2024-07-25";
        String period = "Monthly";
        String endDate = "Subscription";
        String category = "Entertainment";
        String sharedAccountId = "123";
        String date = "2023-07-25";

        // Execute the method
        controller.execute(amount, startDate, description, period, endDate, category, date, sharedAccountId, id);

        UserAccountPeriodicTransactionState expectedState = new UserAccountPeriodicTransactionState();
        assertEquals(expectedState.getTransactionAmount(), testViewModel.getState().getTransactionAmount());
        assertEquals(expectedState.getTransactionStartDate(), testViewModel.getState().getTransactionStartDate());
        assertEquals(expectedState.getTransactionDescription(), testViewModel.getState().getTransactionDescription());
        assertEquals(expectedState.getTransactionPeriod(), testViewModel.getState().getTransactionPeriod());
        assertEquals(expectedState.getTransactionEndDate(), testViewModel.getState().getTransactionEndDate());
        assertEquals(expectedState.getErrorMsg(), testViewModel.getState().getErrorMsg());
        assertEquals(expectedState.getSuccessMessage(), testViewModel.getState().getSuccessMessage());

    }
}


