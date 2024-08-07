package interface_adaptors.transaction.one_time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class OneTimeTransactionViewModelTest {

    private OneTimeTransactionViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new OneTimeTransactionViewModel();
    }

    @Test
    void testGetLabels() {
        assertEquals("One Time Transaction", viewModel.getTitleLabel());
        assertEquals("Transaction Amount", viewModel.getAmount());
        assertEquals("Identification", viewModel.getId());
        assertEquals("Transaction Date", viewModel.getDate());
        assertEquals("Description", viewModel.getDescription());
        assertEquals("Transaction Category", viewModel.getCategory());
        assertEquals("Submit Transaction", viewModel.getSubmitButton());
        assertEquals("Cancel", viewModel.getCancelButton());
    }

    @Test
    void testGetAndSetState() {
        OneTimeTransactionState state = new OneTimeTransactionState();
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
        OneTimeTransactionState state = new OneTimeTransactionState();
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

