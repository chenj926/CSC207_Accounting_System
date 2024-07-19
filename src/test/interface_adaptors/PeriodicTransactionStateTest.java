package interface_adaptors;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeriodicTransactionStateTest {

    @Test
    void testTransactionAmount() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionAmount(100.0f);
        assertEquals(100.0f, state.getTransactionAmount());
    }

    @Test
    void testTransactionStartDate() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionStartDate("2024-07-18");
        assertEquals("2024-07-18", state.getTransactionStartDate());
    }

    @Test
    void testTransactionEndDate() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionEndDate("2025-07-18");
        assertEquals("2025-07-18", state.getTransactionEndDate());
    }

    @Test
    void testTransactionDescription() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionDescription("Monthly Subscription");
        assertEquals("Monthly Subscription", state.getTransactionDescription());
    }

    @Test
    void testTransactionPeriod() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionPeriod(30);
        assertEquals(30, state.getTransactionPeriod());
    }

    @Test
    void testError() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setError("Transaction failed");
        assertEquals("Transaction failed", state.getError());
    }

    @Test
    void testSuccessMessage() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setSuccessMessage("Periodic transaction recorded successfully!");
        assertEquals("Periodic transaction recorded successfully!", state.getSuccessMessage());
    }

    @Test
    void testSetAndGetTransactionAmount() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionAmount(150.0f);
        assertEquals(150.0f, state.getTransactionAmount());
    }

    @Test
    void testSetAndGetTransactionStartDate() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionStartDate("2023-08-15");
        assertEquals("2023-08-15", state.getTransactionStartDate());
    }

    @Test
    void testSetAndGetTransactionEndDate() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionEndDate("2024-08-15");
        assertEquals("2024-08-15", state.getTransactionEndDate());
    }

    @Test
    void testSetAndGetTransactionDescription() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionDescription("Utility Bill");
        assertEquals("Utility Bill", state.getTransactionDescription());
    }

    @Test
    void testSetAndGetTransactionPeriod() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setTransactionPeriod(15);
        assertEquals(15, state.getTransactionPeriod());
    }

    @Test
    void testSetAndGetError() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setError("Insufficient funds");
        assertEquals("Insufficient funds", state.getError());
    }

    @Test
    void testSetAndGetSuccessMessage() {
        PeriodicTransactionState state = new PeriodicTransactionState();
        state.setSuccessMessage("Transaction successful!");
        assertEquals("Transaction successful!", state.getSuccessMessage());
    }
}

