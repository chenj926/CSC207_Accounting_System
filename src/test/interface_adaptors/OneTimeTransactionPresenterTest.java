package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.OneTimeTransactionOutputData;
import entity.OneTimeOutflow;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneTimeTransactionPresenterTest {

    private OneTimeTransactionPresenter presenter;
    private StubOneTimeTransactionViewModel stubViewModel;
    private StubTransactionViewModel stubTransactionViewModel;

    @BeforeEach
    void setUp() {
        stubViewModel = new StubOneTimeTransactionViewModel("One Time Transaction View");
        stubTransactionViewModel = new StubTransactionViewModel("Transaction View");
        presenter = new OneTimeTransactionPresenter(stubViewModel, stubTransactionViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        OneTimeOutflow oneTimeOutflow = new OneTimeOutflow("Grocery Shopping", 50.0f, LocalDate.of(2024, 7, 18), "Description","Grocery Shopping");
        OneTimeTransactionOutputData data = new OneTimeTransactionOutputData(oneTimeOutflow, 1000.0f);

        // Act
        presenter.prepareSuccessView(data);

        // Assert
        OneTimeTransactionState state = stubViewModel.getState();
        assertEquals(1000.0f, state.getNewBalance());
        assertEquals("2024-07-18", state.getTransactionDate());
        assertEquals("Description", state.getTransactionDescription());
        assertEquals("Grocery Shopping", state.getTransactionCategory());  // Corrected category
        assertEquals(false, state.isUseCaseFailed());
        assertEquals("One-time transaction recorded successfully!", state.getSuccessMessage());
        assertEquals(true, stubViewModel.propertyChangedNotified);
    }

    @Test
    void testPrepareFailView() {
        // Arrange
        String error = "Transaction failed due to insufficient funds";

        // Act
        presenter.prepareFailView(error);

        // Assert
        OneTimeTransactionState state = stubViewModel.getState();
        assertEquals(error, state.getError());
        assertEquals(null, state.getSuccessMessage());
        assertEquals(true, stubViewModel.propertyChangedNotified);
    }

    @Test
    void testHandleCancel() {
        // Act
        presenter.handleCancel();

        // Assert
        assertEquals(true, stubTransactionViewModel.oneTimeTransactionSelected);
    }

    // Stub implementations

    private static class StubOneTimeTransactionViewModel extends OneTimeTransactionViewModel {
        private final OneTimeTransactionState state = new OneTimeTransactionState();
        boolean propertyChangedNotified = false;

        public StubOneTimeTransactionViewModel(String title) {
            super();
        }

        @Override
        public OneTimeTransactionState getState() {
            return state;
        }

        @Override
        public void notifyPropertyChange() {
            propertyChangedNotified = true;
        }
    }

    private static class StubTransactionViewModel extends TransactionViewModel {
        boolean oneTimeTransactionSelected = false;

        public StubTransactionViewModel(String title) {
            super(title);
        }

        @Override
        public void selectOneTimeTransaction() {
            oneTimeTransactionSelected = true;
        }
    }
}








