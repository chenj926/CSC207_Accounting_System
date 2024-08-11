package interface_adaptors.transaction.periodic;

import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionState;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountPeriodicTransactionViewModelTest {

    private SharedAccountPeriodicTransactionViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountPeriodicTransactionViewModel();
    }

    @Test
    void testGetLabels() {
        assertEquals("Periodic Transaction", viewModel.getTitleLabel());
        assertEquals("Transaction Amount", viewModel.getAmount());
        assertEquals("Transaction Start Date", viewModel.getStartDate());
        assertEquals("Transaction End Date", viewModel.getEndDate());
        assertEquals("Description", viewModel.getDescription());
        assertEquals("Period", viewModel.getPeriod());
        assertEquals("Submit Transaction", viewModel.getSubmitButton());
        assertEquals("Cancel", viewModel.getCancelButton());
        assertEquals("User ID", viewModel.getUSER_ID_FIELD_LABEL());
        assertEquals("Users", viewModel.getSELECT_USER());
    }

    @Test
    void testGetAndSetState() {
        SharedAccountPeriodicTransactionState state = new SharedAccountPeriodicTransactionState();
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    @Test
    void testFirePropertyChanged() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();

        assertTrue(listener.isPropertyChanged());
    }

    @Test
    void testResetState() {
        SharedAccountPeriodicTransactionState state = new SharedAccountPeriodicTransactionState();
        state.setTransactionAmount("100.0");
        viewModel.setState(state);

        viewModel.resetState();

        assertNotEquals("100.0", viewModel.getState().getTransactionAmount());
        assertEquals("0", viewModel.getState().getTransactionAmount());
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

