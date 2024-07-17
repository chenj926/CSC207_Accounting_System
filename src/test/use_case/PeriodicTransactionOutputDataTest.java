package use_case;

import entity.PeriodicInflow;
import entity.PeriodicOutflow;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeriodicTransactionOutputDataTest {

    @Test
    public void testConstructorForInflow() {
        String identification = "user123";
        float amount = 200.0f;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        String description = "Monthly Salary";
        int period = 30;
        float newBalance = 1200.0f;

        PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, startDate, description, endDate, period);

        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicInflow, newBalance);

        assertEquals(amount, outputData.getTransactionAmount());
        assertEquals(startDate, outputData.getTransactionStartDate());
        assertEquals(endDate, outputData.getTransactionEndDate());
        assertEquals(description, outputData.getTransactionDescription());
        assertEquals(period, outputData.getTransactionPeriod());
        assertEquals(newBalance, outputData.getNewBalance());
    }

    @Test
    public void testConstructorForOutflow() {
        String identification = "user123";
        float amount = -100.0f;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        String description = "Monthly Rent";
        int period = 30;
        float newBalance = 900.0f;

        PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, startDate, description, endDate, period);

        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicOutflow, newBalance);

        assertEquals(amount, outputData.getTransactionAmount());
        assertEquals(startDate, outputData.getTransactionStartDate());
        assertEquals(endDate, outputData.getTransactionEndDate());
        assertEquals(description, outputData.getTransactionDescription());
        assertEquals(period, outputData.getTransactionPeriod());
        assertEquals(newBalance, outputData.getNewBalance());
    }

    @Test
    public void testSetters() {
        String identification = "user123";
        float amount = 200.0f;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        String description = "Monthly Salary";
        int period = 30;
        float newBalance = 1200.0f;

        PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, startDate, description, endDate, period);
        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicInflow, newBalance);

        outputData.setTransactionAmount(300.0f);
        outputData.setTransactionStartDate(LocalDate.of(2024, 2, 1));
        outputData.setTransactionEndDate(LocalDate.of(2024, 11, 30));
        outputData.setTransactionDescription("Updated Description");
        outputData.setTransactionPeriod(60);
        outputData.setNewBalance(1300.0f);

        assertEquals(300.0f, outputData.getTransactionAmount());
        assertEquals(LocalDate.of(2024, 2, 1), outputData.getTransactionStartDate());
        assertEquals(LocalDate.of(2024, 11, 30), outputData.getTransactionEndDate());
        assertEquals("Updated Description", outputData.getTransactionDescription());
        assertEquals(60, outputData.getTransactionPeriod());
        assertEquals(1300.0f, outputData.getNewBalance());
    }
}

