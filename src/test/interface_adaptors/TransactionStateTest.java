package interface_adaptors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionStateTest {

    @Test
    void testIsOneTimeSelected() {
        TransactionState state = new TransactionState();
        state.setOneTimeSelected(true);
        assertTrue(state.isOneTimeSelected());

        state.setOneTimeSelected(false);
        assertFalse(state.isOneTimeSelected());
    }

    @Test
    void testIsPeriodicSelected() {
        TransactionState state = new TransactionState();
        state.setPeriodicSelected(true);
        assertTrue(state.isPeriodicSelected());

        state.setPeriodicSelected(false);
        assertFalse(state.isPeriodicSelected());
    }

    @Test
    void testSetOneTimeSelected() {
        TransactionState state = new TransactionState();
        state.setOneTimeSelected(true);
        assertTrue(state.isOneTimeSelected());

        state.setOneTimeSelected(false);
        assertFalse(state.isOneTimeSelected());
    }

    @Test
    void testSetPeriodicSelected() {
        TransactionState state = new TransactionState();
        state.setPeriodicSelected(true);
        assertTrue(state.isPeriodicSelected());

        state.setPeriodicSelected(false);
        assertFalse(state.isPeriodicSelected());
    }
}

