package interface_adaptors.transaction.one_time.shared_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountOneTimeTransactionViewModelTest {

    private SharedAccountOneTimeTransactionViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountOneTimeTransactionViewModel();
    }

    @Test
    void testGetLabels() {
        assertEquals("Shared Account One Time Transaction", viewModel.getTitleLabel());
        assertEquals("Transaction Amount", viewModel.getAmount());
        assertEquals("Transaction Date", viewModel.getDate());
        assertEquals("Description", viewModel.getDescription());
        assertEquals("Transaction Category", viewModel.getCategoryButton());
        assertEquals("Submit Transaction", viewModel.getSubmitButton());
        assertEquals("Cancel", viewModel.getCancelButton());
        assertEquals("User ID", viewModel.getUserIdFieldLabel());
        assertEquals("User ID", viewModel.getSELECT_USERS_BUTTON_LABEL());
    }

    @Test
    void testGetAndSetState() {
        SharedAccountOneTimeTransactionState state = new SharedAccountOneTimeTransactionState();
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
        SharedAccountOneTimeTransactionState state = new SharedAccountOneTimeTransactionState();
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

