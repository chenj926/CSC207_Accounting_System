package interface_adaptors.transaction.one_time;

import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionState;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountUserOneTimeTransactionViewModelTest {

    private UserOneTimeTransactionViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new UserOneTimeTransactionViewModel();
    }

    @Test
    void testGetLabels() {
        assertEquals("One Time Transaction", viewModel.getTitleLabel());
        assertEquals("Transaction Amount", viewModel.getAmount());
        assertEquals("Transaction Date", viewModel.getDate());
        assertEquals("Description", viewModel.getDescription());
        assertEquals("Transaction Category", viewModel.getCategoryButton());
        assertEquals("Submit Transaction", viewModel.getSubmitButton());
        assertEquals("Cancel", viewModel.getCancelButton());
    }

    @Test
    void testGetAndSetState() {
        UserOneTimeTransactionState state = new UserOneTimeTransactionState();
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
        UserOneTimeTransactionState state = new UserOneTimeTransactionState();
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


