package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;
import entity.account.UserAccount;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OneTimeTransactionPresenterTest {

    private OneTimeTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private OneTimeTransactionPresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new OneTimeTransactionViewModel();
        viewManager = new ViewManagerModel();
        presenter = new OneTimeTransactionPresenter(viewModel, viewManager);
    }

    @Test
    void testPrepareSuccessViewWithInflow() {
        // Mock a UserAccount
        UserAccount mockUserAccount = createMockUserAccount();

        // Create a OneTimeInflow entity with the mocked UserAccount
        OneTimeInflow inflow = new OneTimeInflow("mockUserId", 150.00f, LocalDate.of(2023, 8, 1), "Salary", "Income");
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(inflow);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        OneTimeTransactionState state = viewModel.getState();
        assertEquals("1", state.getId());
        assertEquals("2023-08-01", state.getTransactionDate());
        assertEquals("Salary", state.getTransactionDescription());
        assertEquals("Income", state.getTransactionCategory());
        assertEquals("One time transaction recorded successfully!", state.getSuccessMessage());

        // Verify the view manager changes view
        assertEquals("Homepage Two", viewManager.getActiveViewName());
    }

    @Test
    void testPrepareSuccessViewWithOutflow() {
        // Mock a UserAccount
        UserAccount mockUserAccount = createMockUserAccount();

        // Create a OneTimeOutflow entity with the mocked UserAccount
        OneTimeOutflow outflow = new OneTimeOutflow("mockUserId", 100.00f, LocalDate.of(2023, 8, 1), "Groceries", "Food");
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(outflow);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        OneTimeTransactionState state = viewModel.getState();
        assertEquals("1", state.getId());
        assertEquals("2023-08-01", state.getTransactionDate());
        assertEquals("Groceries", state.getTransactionDescription());
        assertEquals("Food", state.getTransactionCategory());
        assertEquals("One time transaction recorded successfully!", state.getSuccessMessage());

        // Verify the view manager changes view
        assertEquals("Homepage Two", viewManager.getActiveViewName());
    }

    @Test
    void testPrepareFailView() {
        // Test data
        String errorMessage = "An error occurred";

        // Execute method
        presenter.prepareFailView(errorMessage);

        // Verify the view model state
        OneTimeTransactionState state = viewModel.getState();
        assertEquals(errorMessage, state.getErrorMessage());
        assertNull(state.getSuccessMessage());
    }

    @Test
    void testOneTimeTransactionStateSetters() {
        OneTimeTransactionState state = new OneTimeTransactionState();

        // Test setters
        state.setId("1");
        state.setTransactionAmount("150.00");
        state.setTransactionDate("2023-08-01");
        state.setTransactionDescription("Salary");
        state.setTransactionCategory("Income");
        state.setNewBalance(2000.00f);
        state.setUseCaseFailed(false);
        state.setErrorMessage("No error");
        state.setSuccessMessage("Transaction successful");
    }

    /**
     * Helper method to create a mock UserAccount.
     */
    private UserAccount createMockUserAccount() {
        UserAccount mockUser = new UserAccount("mockUserId", "mockUsername", "mockPassword");
        // Mock or set additional fields if needed
        return mockUser;
    }
}








