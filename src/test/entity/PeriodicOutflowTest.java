package entity;

import entity.transaction.periodic.PeriodicOutflow;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicOutflowTest {

    @Test
    void testPeriodicOutflow() {
        PeriodicOutflow outflow = new PeriodicOutflow("testID", -100.0f, LocalDate.of(2024, 7, 16), "Test description", LocalDate.of(2024, 12, 31), "MOUTH", "Auto");

        assertEquals("testID", outflow.getIdentification(), "Identification doesn't match");
        assertEquals(-100.0f, outflow.getAmount(), "Amount doesn't match");
        assertEquals(LocalDate.of(2024, 7, 16), outflow.getStartDate(), "Start date doesn't match");
        assertEquals(LocalDate.of(2024, 12, 31), outflow.getEndDate(), "End date doesn't match");
        assertEquals(30, outflow.getPeriod(), "Period doesn't match");
        assertEquals("Test description", outflow.getDescription(), "Description doesn't match");
        assertFalse(outflow.isInflow(), "Inflow should be false");

        outflow.setIdentification("newID");
        outflow.setAmount(-200.0f);
        outflow.setStartDate(LocalDate.of(2024, 8, 16));
        outflow.setEndDate(LocalDate.of(2024, 11, 30));
        outflow.setPeriod("MOUTH");
        outflow.setDescription("New description");

        assertEquals("newID", outflow.getIdentification(), "Identification doesn't match after set");
        assertEquals(-200.0f, outflow.getAmount(), "Amount doesn't match after set");
        assertEquals(LocalDate.of(2024, 8, 16), outflow.getStartDate(), "Start date doesn't match after set");
        assertEquals(LocalDate.of(2024, 11, 30), outflow.getEndDate(), "End date doesn't match after set");
        assertEquals(15, outflow.getPeriod(), "Period doesn't match after set");
        assertEquals("New description", outflow.getDescription(), "Description doesn't match after set");
        assertFalse(outflow.isInflow(), "Inflow should be false after setting negative amount");
    }
}

