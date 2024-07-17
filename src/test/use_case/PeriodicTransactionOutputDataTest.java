package use_case;

import entity.PeriodicInflow;
import entity.PeriodicOutflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PeriodicTransactionOutputDataTest {

    private PeriodicInflow periodicInflow;
    private PeriodicOutflow periodicOutflow;
    private float newBalance;

    @BeforeEach
    public void setUp() {
        periodicInflow = new PeriodicInflow("inflow1", 100.0f, LocalDate.of(2024, 1, 1), "Salary", LocalDate.of(2024, 12, 31), 30);
        periodicOutflow = new PeriodicOutflow("outflow1", 50.0f, LocalDate.of(2024, 1, 1), "Rent", LocalDate.of(2024, 12, 31), 30);
        newBalance = 1000.0f;
    }

    @Test
    public void testPeriodicTransactionOutputDataInflow() {
        PeriodicTransactionOutputData data = new PeriodicTransactionOutputData(periodicInflow, newBalance);
        assertNotNull(data);
        assertEquals(100.0f, data.getTransactionAmount());
        assertEquals(LocalDate.of(2024, 1, 1), data.getTransactionStartDate());
        assertEquals("Salary", data.getTransactionDescription());
        assertEquals(LocalDate.of(2024, 12, 31), data.getTransactionEndDate());
        assertEquals(30, data.getTransactionPeriod());
        assertEquals(newBalance, data.getNewBalance());
    }

    @Test
    public void testPeriodicTransactionOutputDataOutflow() {
        PeriodicTransactionOutputData data = new PeriodicTransactionOutputData(periodicOutflow, newBalance);
        assertNotNull(data);
        assertEquals(50.0f, data.getTransactionAmount());
        assertEquals(LocalDate.of(2024, 1, 1), data.getTransactionStartDate());
        assertEquals("Rent", data.getTransactionDescription());
        assertEquals(LocalDate.of(2024, 12, 31), data.getTransactionEndDate());
        assertEquals(30, data.getTransactionPeriod());
        assertEquals(newBalance, data.getNewBalance());
    }
}


