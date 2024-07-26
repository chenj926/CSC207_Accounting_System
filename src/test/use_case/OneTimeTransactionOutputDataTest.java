package use_case;

import entity.OneTimeInflow;
import entity.OneTimeOutflow;
import org.junit.jupiter.api.Test;
import use_case.transaction.OneTimeTransactionOutputData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OneTimeTransactionOutputDataTest {

    @Test
    public void testConstructorForOutflow() {

        float amount = -100.0f;
        String identification = "user123";
        LocalDate date = LocalDate.of(2024, 7, 17);
        String description = "Grocery Shopping";
        String category = "Food";
        float newBalance = 900.0f;

        OneTimeOutflow oneTimeOutflow = new OneTimeOutflow(identification, amount, date, description, category);

        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeOutflow, newBalance);

        assertEquals(newBalance, outputData.getNewBalance());
        assertEquals(date, outputData.getTransactionDate());
        assertEquals(description, outputData.getTransactionDescription());
        assertEquals(category, outputData.getTransactionCategory());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    public void testConstructorForInflow() {
        float amount = 100.0f;
        String identification = "user123";
        LocalDate date = LocalDate.of(2024, 7, 17);
        String description = "Salary";
        String category = "Income";
        float newBalance = 1100.0f;

        OneTimeInflow oneTimeInflow = new OneTimeInflow(identification, amount, date, description, category);

        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeInflow, newBalance);

        assertEquals(newBalance, outputData.getNewBalance());
        assertEquals(date, outputData.getTransactionDate());
        assertEquals(description, outputData.getTransactionDescription());
        assertEquals(category, outputData.getTransactionCategory());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    public void testSetUseCaseFailed() {
        float amount = 100.0f;
        String identification = "user123";
        LocalDate date = LocalDate.of(2024, 7, 17);
        String description = "Salary";
        String category = "Income";
        float newBalance = 1100.0f;

        OneTimeInflow oneTimeInflow = new OneTimeInflow(identification, amount, date, description, category);
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeInflow, newBalance);

        outputData.setUseCaseFailed(true);

        assertTrue(outputData.isUseCaseFailed());
    }
}

