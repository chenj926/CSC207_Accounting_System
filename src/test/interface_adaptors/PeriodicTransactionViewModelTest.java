package interface_adaptors;

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
    void testGetState() {
        PeriodicTransactionState state = viewModel.getState();
        assertNotNull(state);
    }

    @Test
    void testNotifyPropertyChange() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.notifyPropertyChange();

        assertTrue(listener.isNotified());
    }

    @Test
    void testFirePropertyChanged() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged("testProperty", null, "newValue");

        assertTrue(listener.isNotified());
        assertEquals("testProperty", listener.getEvent().getPropertyName());
        assertEquals("newValue", listener.getEvent().getNewValue());
    }

    @Test
    void testRemovePropertyChangeListener() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);
        viewModel.removePropertyChangeListener(listener);

        viewModel.notifyPropertyChange();

        assertFalse(listener.isNotified());
    }

    @Test
    void testValidatePeriodicTransaction() {
        PeriodicTransactionState state = viewModel.getState();

        // Test invalid amount
        state.setTransactionAmount(0);
        assertFalse(viewModel.validatePeriodicTransaction());
        assertEquals("Transaction amount must be greater than 0", state.getError());

        // Test invalid date
        state.setTransactionAmount(100.0f);
        state.setTransactionEndDate("invalid-date");
        assertFalse(viewModel.validatePeriodicTransaction());
        assertEquals("Transaction date must be in the format YYYY-MM-DD", state.getError());

        // Test empty description
        state.setTransactionEndDate("2024-07-18");
        state.setTransactionDescription("");
        assertFalse(viewModel.validatePeriodicTransaction());
        assertEquals("Transaction description cannot be empty", state.getError());

        // Test valid transaction
        state.setTransactionDescription("Valid description");
        assertTrue(viewModel.validatePeriodicTransaction());
        assertNull(state.getError());
    }

    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private boolean notified = false;
        private PropertyChangeEvent event;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            this.notified = true;
            this.event = evt;
        }

        public boolean isNotified() {
            return notified;
        }

        public PropertyChangeEvent getEvent() {
            return event;
        }
    }
}

