package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class TransactionViewModelTest {

    private TransactionViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new TransactionViewModel("Transaction View");
    }

    @Test
    void testGetTransactionViewLabel() {
        assertEquals("Transaction", viewModel.getTransactionViewLabel());
    }

    @Test
    void testGetOneTimeButtonLabel() {
        assertEquals("One Time Transaction", viewModel.getOneTimeButtonLabel());
    }

    @Test
    void testGetPeriodicButtonLabel() {
        assertEquals("Periodic Transaction", viewModel.getPeriodicButtonLabel());
    }

    @Test
    void testGetAmountLabel() {
        assertEquals("Transaction Amount", viewModel.getAmountLabel());
    }

    @Test
    void testGetDateLabel() {
        assertEquals("Transaction Date", viewModel.getDateLabel());
    }

    @Test
    void testGetDescriptionLabel() {
        assertEquals("Description", viewModel.getDescriptionLabel());
    }

    @Test
    void testGetRecurrencePeriodLabel() {
        assertEquals("Recurrence Period", viewModel.getRecurrencePeriodLabel());
    }

    @Test
    void testGetRecordButtonLabel() {
        assertEquals("Record Transaction", viewModel.getRecordButtonLabel());
    }

    @Test
    void testGetCancelButtonLabel() {
        assertEquals("Cancel", viewModel.getCancelButtonLabel());
    }

    @Test
    void testSelectOneTimeTransaction() {
        viewModel.selectOneTimeTransaction();
        assertTrue(viewModel.getTransactionState().isOneTimeSelected());
        assertFalse(viewModel.getTransactionState().isPeriodicSelected());
        assertNotNull(viewModel.getCurrentViewModel());
        assertTrue(viewModel.getCurrentViewModel() instanceof OneTimeTransactionViewModel);
    }

    @Test
    void testSelectPeriodicTransaction() {
        viewModel.selectPeriodicTransaction();
        assertFalse(viewModel.getTransactionState().isOneTimeSelected());
        assertTrue(viewModel.getTransactionState().isPeriodicSelected());
        assertNotNull(viewModel.getCurrentViewModel());
        assertTrue(viewModel.getCurrentViewModel() instanceof PeriodicTransactionViewModel);
    }

    @Test
    void testSetCurrentViewModel() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        TransactionViewModel newViewModel = new OneTimeTransactionViewModel();
        viewModel.setCurrentViewModel(newViewModel);

        assertEquals(newViewModel, viewModel.getCurrentViewModel());
        assertTrue(listener.isNotified());
        assertEquals("currentViewModel", listener.getEvent().getPropertyName());
        assertEquals(newViewModel, listener.getEvent().getNewValue());
    }

    @Test
    void testResetTransactionView() {
        viewModel.selectOneTimeTransaction();
        viewModel.resetTransactionView();

        assertFalse(viewModel.getTransactionState().isOneTimeSelected());
        assertFalse(viewModel.getTransactionState().isPeriodicSelected());
        assertNull(viewModel.getCurrentViewModel());
    }

    @Test
    void testCancelTransaction() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.selectOneTimeTransaction();
        viewModel.cancelTransaction();

        assertNull(viewModel.getCurrentViewModel());
        assertTrue(listener.isNotified());
        assertEquals("currentViewModel", listener.getEvent().getPropertyName());
        assertEquals(viewModel, listener.getEvent().getNewValue());
    }

    @Test
    void testAddRemovePropertyChangeListener() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged("testProperty", null, "newValue");
        assertTrue(listener.isNotified());

        listener.reset();
        viewModel.removePropertyChangeListener(listener);
        viewModel.firePropertyChanged("testProperty", null, "newValue");
        assertFalse(listener.isNotified());
    }

    @Test
    void testFirePropertyChanged() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged("testProperty", "oldValue", "newValue");

        assertTrue(listener.isNotified());
        assertEquals("testProperty", listener.getEvent().getPropertyName());
        assertEquals("oldValue", listener.getEvent().getOldValue());
        assertEquals("newValue", listener.getEvent().getNewValue());
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

        public void reset() {
            this.notified = false;
            this.event = null;
        }
    }
}




