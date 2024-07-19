package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.PeriodicTransactionOutputData;
import entity.PeriodicInflow;
import entity.PeriodicOutflow;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeriodicTransactionPresenterTest {

    private PeriodicTransactionPresenter presenter;
    private StubPeriodicTransactionViewModel stubViewModel;
    private StubTransactionViewModel stubTransactionViewModel;

    @BeforeEach
    void setUp() {
        stubViewModel = new StubPeriodicTransactionViewModel("Periodic Transaction View");
        stubTransactionViewModel = new StubTransactionViewModel("Transaction View");
        presenter = new PeriodicTransactionPresenter(stubViewModel, stubTransactionViewModel);
    }

    @Test
    void testPrepareSuccessViewWithInflow() {
        // Arrange
        PeriodicInflow periodicInflow = new PeriodicInflow("12345", 100.0f, LocalDate.of(2024, 7, 18), "Monthly Salary", LocalDate.of(2025, 7, 18), 30);
        PeriodicTransactionOutputData data = new PeriodicTransactionOutputData(periodicInflow, 1000.0f);

        // Act
        presenter.prepareSuccessView(data);

        // Assert
        PeriodicTransactionState state = stubViewModel.getState();
        assertEquals(100.0f, state.getTransactionAmount());
        assertEquals("2024-07-18", state.getTransactionStartDate());
        assertEquals("Monthly Salary", state.getTransactionDescription());
        assertEquals(30, state.getTransactionPeriod());
        assertEquals("2025-07-18", state.getTransactionEndDate());
        assertEquals("Periodic transaction recorded successfully!", state.getSuccessMessage());
        assertEquals(true, stubViewModel.propertyChangedNotified);
    }

    @Test
    void testPrepareSuccessViewWithOutflow() {
        // Arrange
        PeriodicOutflow periodicOutflow = new PeriodicOutflow("12345", 50.0f, LocalDate.of(2024, 7, 18), "Monthly Rent", LocalDate.of(2025, 7, 18), 30);
        PeriodicTransactionOutputData data = new PeriodicTransactionOutputData(periodicOutflow, 950.0f);

        // Act
        presenter.prepareSuccessView(data);

        // Assert
        PeriodicTransactionState state = stubViewModel.getState();
        assertEquals(50.0f, state.getTransactionAmount());
        assertEquals("2024-07-18", state.getTransactionStartDate());
        assertEquals("Monthly Rent", state.getTransactionDescription());
        assertEquals(30, state.getTransactionPeriod());
        assertEquals("2025-07-18", state.getTransactionEndDate());
        assertEquals("Periodic transaction recorded successfully!", state.getSuccessMessage());
        assertEquals(true, stubViewModel.propertyChangedNotified);
    }

    @Test
    void testPrepareFailView() {
        // Arrange
        String error = "Transaction failed due to insufficient funds";

        // Act
        presenter.prepareFailView(error);

        // Assert
        PeriodicTransactionState state = stubViewModel.getState();
        assertEquals(error, state.getError());
        assertEquals(null, state.getSuccessMessage());
        assertEquals(true, stubViewModel.propertyChangedNotified);
    }

    @Test
    void testHandleCancel() {
        // Act
        presenter.handleCancel();

        // Assert
        assertEquals(true, stubTransactionViewModel.periodicTransactionSelected);
    }

    // Stub implementations

    private static class StubPeriodicTransactionViewModel extends PeriodicTransactionViewModel {
        private final PeriodicTransactionState state = new PeriodicTransactionState();
        boolean propertyChangedNotified = false;

        public StubPeriodicTransactionViewModel(String title) {
            super();
        }

        @Override
        public PeriodicTransactionState getState() {
            return state;
        }

        @Override
        public void notifyPropertyChange() {
            propertyChangedNotified = true;
        }
    }

    private static class StubTransactionViewModel extends TransactionViewModel {
        boolean periodicTransactionSelected = false;

        public StubTransactionViewModel(String title) {
            super(title);
        }

        @Override
        public void selectPeriodicTransaction() {
            periodicTransactionSelected = true;
        }
    }
}




