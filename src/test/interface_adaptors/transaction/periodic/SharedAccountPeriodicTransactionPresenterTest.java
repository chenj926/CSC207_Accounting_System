package interface_adaptors.transaction.periodic;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionPresenter;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionState;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountPeriodicTransactionPresenterTest {

    private SharedAccountPeriodicTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountPeriodicTransactionPresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountPeriodicTransactionViewModel();
        viewManager = new ViewManagerModel();
        presenter = new SharedAccountPeriodicTransactionPresenter(viewModel, viewManager);
    }

    @Test
    void testPrepareSuccessViewWithInflow() {
        // Create a PeriodicInflow entity
        PeriodicInflow inflow = new PeriodicInflow("1", 100.00f, LocalDate.of(2023, 7, 15), "Subscription", LocalDate.of(2024, 7, 25), "Weekly", "Income");
        SharedAccountPeriodicTransactionOutputData outputData = new SharedAccountPeriodicTransactionOutputData(inflow);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        assertEquals("100.0", viewModel.getState().getTransactionAmount());
        assertEquals("2023-07-15", viewModel.getState().getTransactionStartDate());
        assertEquals("Subscription", viewModel.getState().getTransactionDescription());
        assertEquals("Weekly", viewModel.getState().getTransactionPeriod());
        assertEquals("2024-07-25", viewModel.getState().getTransactionEndDate());
        assertEquals("Shared Account Periodic Transaction Recorded successfully!", viewModel.getState().getSuccessMessage());
        assertEquals("Income", viewModel.getState().getTransactionCategory());

        // Verify the view manager changes view
        assertEquals("Shared Account Transaction", viewManager.getActiveViewName());
    }

    @Test
    void testPrepareSuccessViewWithOutflow() {
        // Create a PeriodicOutflow entity
        PeriodicOutflow outflow = new PeriodicOutflow("1", 50.00f, LocalDate.of(2023, 7, 15), "Subscription", LocalDate.of(2024, 7, 25), "Weekly", "Expense");
        SharedAccountPeriodicTransactionOutputData outputData = new SharedAccountPeriodicTransactionOutputData(outflow);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        assertEquals("50.0", viewModel.getState().getTransactionAmount());
        assertEquals("2023-07-15", viewModel.getState().getTransactionStartDate());
        assertEquals("Subscription", viewModel.getState().getTransactionDescription());
        assertEquals("Weekly", viewModel.getState().getTransactionPeriod());
        assertEquals("2024-07-25", viewModel.getState().getTransactionEndDate());
        assertEquals("Shared Account Periodic Transaction Recorded successfully!", viewModel.getState().getSuccessMessage());
        assertEquals("Expense", viewModel.getState().getTransactionCategory());

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
        SharedAccountPeriodicTransactionState state = viewModel.getState();
        assertEquals(errorMessage, state.getErrorMsg());
        assertNull(state.getSuccessMessage());
    }
}
