package interface_adaptors;

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
    void testGetState() {
        OneTimeTransactionState state = viewModel.getState();
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
    void testValidateOneTimeTransaction() {
        OneTimeTransactionState state = viewModel.getState();

        // Test invalid amount
        state.setTransactionAmount(0);
        assertFalse(viewModel.validateOneTimeTransaction());
        assertEquals("Transaction amount must be greater than 0", state.getError());

        // Test invalid date
        state.setTransactionAmount(100.0f);
        state.setTransactionDate("invalid-date");
        assertFalse(viewModel.validateOneTimeTransaction());
        assertEquals("Transaction date must be in the format YYYY-MM-DD", state.getError());

        // Test empty description
        state.setTransactionDate("2024-07-18");
        state.setTransactionDescription("");
        assertFalse(viewModel.validateOneTimeTransaction());
        assertEquals("Transaction description cannot be empty", state.getError());

        // Test valid transaction
        state.setTransactionDescription("Valid description");
        assertTrue(viewModel.validateOneTimeTransaction());
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


