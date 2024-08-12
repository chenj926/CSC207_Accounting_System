package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionState;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountOneTimeTransactionPresenterTest {

    private SharedAccountOneTimeTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountOneTimeTransactionPresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountOneTimeTransactionViewModel();
        viewManager = new ViewManagerModel();
        presenter = new SharedAccountOneTimeTransactionPresenter(viewModel, viewManager);
    }

    @Test
    void testPrepareSuccessViewWithInflow() {
        // Create a OneTimeInflow entity
        OneTimeInflow inflow = new OneTimeInflow("mockUserId", 150.00f, LocalDate.of(2023, 8, 1), "Salary", "Income");
        SharedAccountOneTimeTransactionOutputData outputData = new SharedAccountOneTimeTransactionOutputData(inflow);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        SharedAccountOneTimeTransactionState state = viewModel.getState();
        assertEquals("mockUserId", state.getId());
        assertEquals("2023-08-01", state.getTransactionDate());
        assertEquals("Salary", state.getTransactionDescription());
        assertEquals("Income", state.getTransactionCategory());
        assertEquals("Shared account one-time transaction recorded successfully!", state.getSuccessMessage());

        // Verify the view manager changes view
        assertEquals("Shared Account Transaction", viewManager.getActiveViewName());
    }

    @Test
    void testPrepareSuccessViewWithOutflow() {
        // Create a OneTimeOutflow entity
        OneTimeOutflow outflow = new OneTimeOutflow("mockUserId", 100.00f, LocalDate.of(2023, 8, 1), "Groceries", "Food");
        SharedAccountOneTimeTransactionOutputData outputData = new SharedAccountOneTimeTransactionOutputData(outflow);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        SharedAccountOneTimeTransactionState state = viewModel.getState();
        assertEquals("mockUserId", state.getId());
        assertEquals("2023-08-01", state.getTransactionDate());
        assertEquals("Groceries", state.getTransactionDescription());
        assertEquals("Food", state.getTransactionCategory());
        assertEquals("Shared account one-time transaction recorded successfully!", state.getSuccessMessage());

        // Verify the view manager changes view
        assertEquals("Shared Account Transaction", viewManager.getActiveViewName());
    }

    @Test
    void testPrepareFailView() {
        // Test data
        String errorMessage = "An error occurred";

        // Execute method
        presenter.prepareFailView(errorMessage);

        // Verify the view model state
        SharedAccountOneTimeTransactionState state = viewModel.getState();
        assertEquals(errorMessage, state.getErrorMessage());
        assertNull(state.getSuccessMessage());
    }

    @Test
    void testOneTimeTransactionStateSetters() {
        SharedAccountOneTimeTransactionState state = new SharedAccountOneTimeTransactionState();

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
}

