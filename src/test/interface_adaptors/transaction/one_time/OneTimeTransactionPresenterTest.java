package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;

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
        // Create a OneTimeInflow entity
        OneTimeInflow inflow = new OneTimeInflow("1", 150.00f, LocalDate.of(2023, 8, 1), "Salary", "Income");
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(inflow, 2000.00f);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        OneTimeTransactionState state = viewModel.getState();
        assertEquals(2000.00f, state.getNewBalance(), 0.01);
        assertEquals("2023-08-01", state.getTransactionDate());
        assertEquals("Salary", state.getTransactionDescription());
        assertEquals("Income", state.getTransactionCategory());
        assertFalse(state.isUseCaseFailed());
        assertEquals("One time transaction recorded successfully!", state.getSuccessMessage());

        // Verify the view manager changes view
        assertEquals("Transaction", viewManager.getActiveViewName());
    }

    @Test
    void testPrepareSuccessViewWithOutflow() {
        // Create a OneTimeOutflow entity
        OneTimeOutflow outflow = new OneTimeOutflow("1", 100.00f, LocalDate.of(2023, 8, 1), "Groceries", "Food");
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(outflow, 1900.00f);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        OneTimeTransactionState state = viewModel.getState();
        assertEquals(1900.00f, state.getNewBalance(), 0.01);
        assertEquals("2023-08-01", state.getTransactionDate());
        assertEquals("Groceries", state.getTransactionDescription());
        assertEquals("Food", state.getTransactionCategory());
        assertFalse(state.isUseCaseFailed());
        assertEquals("One time transaction recorded successfully!", state.getSuccessMessage());

        // Verify the view manager changes view
        assertEquals("Transaction", viewManager.getActiveViewName());
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
}



