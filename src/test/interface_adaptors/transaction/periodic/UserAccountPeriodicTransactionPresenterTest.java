package interface_adaptors.transaction.periodic;

import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionPresenter;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import interface_adaptors.ViewManagerModel;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountPeriodicTransactionPresenterTest {

    private UserAccountPeriodicTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountPeriodicTransactionPresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountPeriodicTransactionViewModel();
        viewManager = new ViewManagerModel();
        presenter = new UserAccountPeriodicTransactionPresenter(viewModel, viewManager);
    }

    @Test
    void testPrepareSuccessViewWithInflow() {
        // Create a PeriodicInflow entity
        PeriodicInflow inflow = new PeriodicInflow("1", 100.00f, LocalDate.of(2023, 7, 15), "Subscription", LocalDate.of(2024, 7, 25), "weekly","food");
        UserAccountPeriodicTransactionOutputData outputData = new UserAccountPeriodicTransactionOutputData(inflow);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        assertEquals("100.0", viewModel.getState().getTransactionAmount());
        assertEquals("2023-07-15", viewModel.getState().getTransactionStartDate());
        assertEquals("Subscription", viewModel.getState().getTransactionDescription());
        assertEquals("weekly", viewModel.getState().getTransactionPeriod());
        assertEquals("2024-07-25", viewModel.getState().getTransactionEndDate());
        assertEquals("Period Transaction Recorded successfully!", viewModel.getState().getSuccessMessage());
        assertEquals("",viewModel.getState().getTransactionCategory());

        // Verify the view manager changes view
        assertEquals("Homepage Two", viewManager.getActiveViewName()); // Check if it changes to the correct view
    }

    @Test
    void testPrepareSuccessViewWithOutflow() {
        // Create a PeriodicOutflow entity
        PeriodicOutflow outflow = new PeriodicOutflow("1", 50.00f, LocalDate.of(2023, 7, 15), "Subscription", LocalDate.of(2024, 7, 25), "weekly","food");
        UserAccountPeriodicTransactionOutputData outputData = new UserAccountPeriodicTransactionOutputData(outflow);

        // Execute method
        presenter.prepareSuccessView(outputData);

        // Verify the view model state
        assertEquals("50.0", viewModel.getState().getTransactionAmount());
        assertEquals("2023-07-15", viewModel.getState().getTransactionStartDate());
        assertEquals("Subscription", viewModel.getState().getTransactionDescription());
        assertEquals("weekly", viewModel.getState().getTransactionPeriod());
        assertEquals("2024-07-25", viewModel.getState().getTransactionEndDate());
        assertEquals("Period Transaction Recorded successfully!", viewModel.getState().getSuccessMessage());

        // Verify the view manager changes view
        assertEquals("Homepage Two", viewManager.getActiveViewName()); // Ensure it changes to the expected view
    }

    @Test
    void testPrepareFailView() {
        // Test data
        String errorMessage = "An error occurred";

        // Execute method
        presenter.prepareFailView(errorMessage);

        // Verify the view model state
        assertEquals(errorMessage, viewModel.getState().getErrorMsg());
        assertNull(viewModel.getState().getSuccessMessage());
    }
}












