package interface_adaptors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneTimeTransactionStateTest {

    @Test
    void testTransactionAmount() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setTransactionAmount(100.0f);
        assertEquals(100.0f, state.getTransactionAmount());
    }

    @Test
    void testTransactionDate() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setTransactionDate("2024-07-18");
        assertEquals("2024-07-18", state.getTransactionDate());
    }

    @Test
    void testTransactionDescription() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setTransactionDescription("Grocery Shopping");
        assertEquals("Grocery Shopping", state.getTransactionDescription());
    }

    @Test
    void testTransactionCategory() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setTransactionCategory("Food");
        assertEquals("Food", state.getTransactionCategory());
    }

    @Test
    void testNewBalance() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setNewBalance(1000.0f);
        assertEquals(1000.0f, state.getNewBalance());
    }

    @Test
    void testUseCaseFailed() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setUseCaseFailed(true);
        assertEquals(true, state.isUseCaseFailed());
    }

    @Test
    void testError() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setError("Transaction failed");
        assertEquals("Transaction failed", state.getError());
    }

    @Test
    void testSuccessMessage() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setSuccessMessage("One-time transaction recorded successfully!");
        assertEquals("One-time transaction recorded successfully!", state.getSuccessMessage());
    }

    @Test
    void testSetAndGetTransactionAmount() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setTransactionAmount(150.0f);
        assertEquals(150.0f, state.getTransactionAmount());
    }

    @Test
    void testSetAndGetTransactionDate() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setTransactionDate("2023-08-15");
        assertEquals("2023-08-15", state.getTransactionDate());
    }

    @Test
    void testSetAndGetTransactionDescription() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setTransactionDescription("Utility Bill");
        assertEquals("Utility Bill", state.getTransactionDescription());
    }

    @Test
    void testSetAndGetTransactionCategory() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setTransactionCategory("Utilities");
        assertEquals("Utilities", state.getTransactionCategory());
    }

    @Test
    void testSetAndGetNewBalance() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setNewBalance(2000.0f);
        assertEquals(2000.0f, state.getNewBalance());
    }

    @Test
    void testSetAndGetUseCaseFailed() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setUseCaseFailed(false);
        assertEquals(false, state.isUseCaseFailed());
    }

    @Test
    void testSetAndGetError() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setError("Insufficient funds");
        assertEquals("Insufficient funds", state.getError());
    }

    @Test
    void testSetAndGetSuccessMessage() {
        OneTimeTransactionState state = new OneTimeTransactionState();
        state.setSuccessMessage("Transaction successful!");
        assertEquals("Transaction successful!", state.getSuccessMessage());
    }
}



