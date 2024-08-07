package interface_adaptors.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class TransactionViewModelTest {

    private TransactionViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new TransactionViewModel();
    }

    @Test
    void testGetLabels() {
        assertEquals("Account", viewModel.getTITLE_LABEL());
        assertEquals("Total Balance", viewModel.getBALANCE_LABEL());
        assertEquals("Total Income", viewModel.getINCOME_LABEL());
        assertEquals("Total Outflow", viewModel.getOUTFLOW_LABEL());
        assertEquals("One Time Transaction", viewModel.getONE_TIME_BUTTON_LABEL());
        assertEquals("Periodic Transaction", viewModel.getPERIODIC_BUTTON_LABEL());
        assertEquals("Log out", viewModel.getCANCEL_BUTTON_LABEL());
    }

    @Test
    void testTransactionState() {
        TransactionState state = new TransactionState();

        // Test setters
        state.setOneTimeSelected(true);
        state.setPeriodicSelected(false);

        // Test getters
        assertAll("state",
                () -> assertTrue(state.isOneTimeSelected()),
                () -> assertFalse(state.isPeriodicSelected())
        );

        // Update state and test again
        state.setOneTimeSelected(false);
        state.setPeriodicSelected(true);

        assertAll("updated state",
                () -> assertFalse(state.isOneTimeSelected()),
                () -> assertTrue(state.isPeriodicSelected())
        );
    }


    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private boolean propertyChanged = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            propertyChanged = true;
        }

        public boolean isPropertyChanged() {
            return propertyChanged;
        }
    }
}

