package interface_adaptors.transaction.periodic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicTransactionViewModelTest {

    private PeriodicTransactionViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new PeriodicTransactionViewModel();
    }

    @Test
    void testGetLabels() {
        assertEquals("Periodic Transaction", viewModel.getTitleLabel());
        assertEquals("Transaction Amount", viewModel.getAmount());
        assertEquals("Identification", viewModel.getId());
        assertEquals("Transaction Start Date", viewModel.getStartDate());
        assertEquals("Transaction End Date", viewModel.getEndDate());
        assertEquals("Description", viewModel.getDescription());
        assertEquals("Period", viewModel.getPeriod());
        assertEquals("Submit Transaction", viewModel.getSubmitButton());
        assertEquals("Cancel", viewModel.getCancelButton());
    }

    @Test
    void testGetAndSetState() {
        PeriodicTransactionState state = new PeriodicTransactionState();
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
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionAmount("100.0");
        viewModel.setState(state);

        viewModel.resetState();

        assertNotEquals("100.0", viewModel.getState().getTransactionAmount());
        assertNull(viewModel.getState().getTransactionAmount());
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

