package entity;

import entity.transaction.periodic.PeriodicInflow;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicInflowTest {

    @Test
    void testPeriodicInflow() {
        PeriodicInflow inflow = new PeriodicInflow("testID", 100.0f, LocalDate.of(2024, 7, 16), "Test description", LocalDate.of(2024, 12, 31), 30, "Auto");

        assertEquals("testID", inflow.getIdentification(), "Identification doesn't match");
        assertEquals(100.0f, inflow.getAmount(), "Amount doesn't match");
        assertEquals(LocalDate.of(2024, 7, 16), inflow.getStartDate(), "Start date doesn't match");
        assertEquals(LocalDate.of(2024, 12, 31), inflow.getEndDate(), "End date doesn't match");
        assertEquals(30, inflow.getPeriod(), "Period doesn't match");
        assertEquals("Test description", inflow.getDescription(), "Description doesn't match");
        assertTrue(inflow.isInflow(), "Inflow should be true");

        inflow.setIdentification("newID");
        inflow.setAmount(200.0f);
        inflow.setStartDate(LocalDate.of(2024, 8, 16));
        inflow.setEndDate(LocalDate.of(2024, 11, 30));
        inflow.setPeriod(15);
        inflow.setDescription("New description");

        assertEquals("newID", inflow.getIdentification(), "Identification doesn't match after set");
        assertEquals(200.0f, inflow.getAmount(), "Amount doesn't match after set");
        assertEquals(LocalDate.of(2024, 8, 16), inflow.getStartDate(), "Start date doesn't match after set");
        assertEquals(LocalDate.of(2024, 11, 30), inflow.getEndDate(), "End date doesn't match after set");
        assertEquals(15, inflow.getPeriod(), "Period doesn't match after set");
        assertEquals("New description", inflow.getDescription(), "Description doesn't match after set");
        assertTrue(inflow.isInflow(), "Inflow should be true after setting positive amount");
    }
}

