package use_case;

import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.PeriodicTransactionOutputData;

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

    @Test
    public void testSetters() {
        PeriodicTransactionOutputData data = new PeriodicTransactionOutputData(periodicInflow, newBalance);

        data.setTransactionAmount(200.0f);
        assertEquals(200.0f, data.getTransactionAmount());

        LocalDate newStartDate = LocalDate.of(2025, 1, 1);
        data.setTransactionStartDate(newStartDate);
        assertEquals(newStartDate, data.getTransactionStartDate());

        String newDescription = "Bonus";
        data.setTransactionDescription(newDescription);
        assertEquals(newDescription, data.getTransactionDescription());

        LocalDate newEndDate = LocalDate.of(2025, 12, 31);
        data.setTransactionEndDate(newEndDate);
        assertEquals(newEndDate, data.getTransactionEndDate());

        int newPeriod = 15;
        data.setTransactionPeriod(newPeriod);
        assertEquals(newPeriod, data.getTransactionPeriod());

        LocalDate newDate = LocalDate.of(2024, 7, 17);
        data.setDate(newDate);
        assertEquals(newDate, data.getDate());

        float newNewBalance = 1500.0f;
        data.setNewBalance(newNewBalance);
        assertEquals(newNewBalance, data.getNewBalance());
    }
}



