package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OneTimeOutflowTest {

    @Test
    void testOneTimeOutflow() {
        OneTimeOutflow outflow = new OneTimeOutflow("testID", -100.0f, LocalDate.of(2024, 7, 16), "Test description", "Test category");

        assertEquals("testID", outflow.getIdentification(), "Identification doesn't match");
        assertEquals(-100.0f, outflow.getAmount(), "Amount doesn't match");
        assertEquals(LocalDate.of(2024, 7, 16), outflow.getDate(), "Date doesn't match");
        assertEquals("Test description", outflow.getDescription(), "Description doesn't match");
        assertEquals("Test category", outflow.getCategory(), "Category doesn't match");
        assertFalse(outflow.isInflow(), "Inflow should be false");

        outflow.setIdentification("newID");
        outflow.setAmount(-200.0f);
        outflow.setDate(LocalDate.of(2024, 8, 16));
        outflow.setDescription("New description");

        assertEquals("newID", outflow.getIdentification(), "Identification doesn't match after set");
        assertEquals(-200.0f, outflow.getAmount(), "Amount doesn't match after set");
        assertEquals(LocalDate.of(2024, 8, 16), outflow.getDate(), "Date doesn't match after set");
        assertEquals("New description", outflow.getDescription(), "Description doesn't match after set");
        assertFalse(outflow.isInflow(), "Inflow should be false after setting negative amount");
    }
}

