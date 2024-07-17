package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OneTimeTransactionTest {

    @Test
    void testOneTimeTransaction() {
        OneTimeTransaction transaction = new OneTimeOutflow("testID", -100.0f, LocalDate.of(2024, 7, 16), "Test description", "Test category");

        assertEquals("testID", transaction.getIdentification(), "Identification doesn't match");
        assertEquals(-100.0f, transaction.getAmount(), "Amount doesn't match");
        assertEquals(LocalDate.of(2024, 7, 16), transaction.getDate(), "Date doesn't match");
        assertEquals("Test description", transaction.getDescription(), "Description doesn't match");
        assertEquals("Test category", transaction.getCategory(), "Category doesn't match");
        assertFalse(transaction.isInflow(), "Inflow should be false");

        transaction.setIdentification("newID");
        transaction.setAmount(200.0f);
        transaction.setDate(LocalDate.of(2024, 8, 16));
        transaction.setDescription("New description");

        assertEquals("newID", transaction.getIdentification(), "Identification doesn't match after set");
        assertEquals(200.0f, transaction.getAmount(), "Amount doesn't match after set");
        assertEquals(LocalDate.of(2024, 8, 16), transaction.getDate(), "Date doesn't match after set");
        assertEquals("New description", transaction.getDescription(), "Description doesn't match after set");
        assertTrue(transaction.isInflow(), "Inflow should be true after setting positive amount");
    }
}
