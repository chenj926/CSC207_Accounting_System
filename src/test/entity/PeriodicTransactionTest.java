package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicTransactionTest {

    @Test
    void testPeriodicTransaction() {
        PeriodicTransaction transaction = new PeriodicInflow("testID", 100.0f, LocalDate.of(2024, 7, 16), "Test description", LocalDate.of(2024, 12, 31), 30);

        assertEquals("testID", transaction.getIdentification(), "Identification doesn't match");
        assertEquals(100.0f, transaction.getAmount(), "Amount doesn't match");
        assertEquals(LocalDate.of(2024, 7, 16), transaction.getStartDate(), "Start date doesn't match");
        assertEquals(LocalDate.of(2024, 12, 31), transaction.getEndDate(), "End date doesn't match");
        assertEquals(30, transaction.getPeriod(), "Period doesn't match");
        assertEquals("Test description", transaction.getDescription(), "Description doesn't match");
        assertTrue(transaction.isInflow(), "Inflow should be true");

        transaction.setIdentification("newID");
        transaction.setAmount(-200.0f);
        transaction.setStartDateDate(LocalDate.of(2024, 8, 16));
        transaction.setEndDate(LocalDate.of(2024, 11, 30));
        transaction.setPeriod(15);
        transaction.setDescription("New description");

        assertEquals("newID", transaction.getIdentification(), "Identification doesn't match after set");
        assertEquals(-200.0f, transaction.getAmount(), "Amount doesn't match after set");
        assertEquals(LocalDate.of(2024, 8, 16), transaction.getStartDate(), "Start date doesn't match after set");
        assertEquals(LocalDate.of(2024, 11, 30), transaction.getEndDate(), "End date doesn't match after set");
        assertEquals(15, transaction.getPeriod(), "Period doesn't match after set");
        assertEquals("New description", transaction.getDescription(), "Description doesn't match after set");
        assertFalse(transaction.isInflow(), "Inflow should be false after setting negative amount");
    }
}
