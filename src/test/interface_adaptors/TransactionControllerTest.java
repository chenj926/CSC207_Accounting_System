package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionControllerTest {

    private TransactionController controller;
    private StubTransactionViewModel stubViewModel;

    @BeforeEach
    void setUp() {
        stubViewModel = new StubTransactionViewModel("Transaction View");
        controller = new TransactionController(stubViewModel);
    }

    @Test
    void testSelectOneTimeTransaction() {
        // Act
        controller.selectOneTimeTransaction();

        // Assert
        assertTrue(stubViewModel.oneTimeTransactionSelected);
    }

    @Test
    void testSelectPeriodicTransaction() {
        // Act
        controller.selectPeriodicTransaction();

        // Assert
        assertTrue(stubViewModel.periodicTransactionSelected);
    }

    // Stub implementation
    private static class StubTransactionViewModel extends TransactionViewModel {
        boolean oneTimeTransactionSelected = false;
        boolean periodicTransactionSelected = false;

        public StubTransactionViewModel(String title) {
            super(title);
        }

        @Override
        public void selectOneTimeTransaction() {
            oneTimeTransactionSelected = true;
        }

        @Override
        public void selectPeriodicTransaction() {
            periodicTransactionSelected = true;
        }
    }
}

