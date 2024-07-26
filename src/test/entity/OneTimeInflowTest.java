package entity;

import entity.transaction.one_time.OneTimeInflow;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OneTimeInflowTest {

    @Test
    void testOneTimeInflow() {
        OneTimeInflow inflow = new OneTimeInflow("testID", 100.0f, LocalDate.of(2024, 7, 16), "Test description", "Test category");

        assertEquals("testID", inflow.getIdentification(), "Identification doesn't match");
        assertEquals(100.0f, inflow.getAmount(), "Amount doesn't match");
        assertEquals(LocalDate.of(2024, 7, 16), inflow.getDate(), "Date doesn't match");
        assertEquals("Test description", inflow.getDescription(), "Description doesn't match");
        assertEquals("Test category", inflow.getCategory(), "Category doesn't match");
        assertTrue(inflow.isInflow(), "Inflow should be true");

        inflow.setIdentification("newID");
        inflow.setAmount(200.0f);
        inflow.setDate(LocalDate.of(2024, 8, 16));
        inflow.setDescription("New description");

        assertEquals("newID", inflow.getIdentification(), "Identification doesn't match after set");
        assertEquals(200.0f, inflow.getAmount(), "Amount doesn't match after set");
        assertEquals(LocalDate.of(2024, 8, 16), inflow.getDate(), "Date doesn't match after set");
        assertEquals("New description", inflow.getDescription(), "Description doesn't match after set");
        assertTrue(inflow.isInflow(), "Inflow should be true after setting positive amount");
    }
}
