package use_case;

import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.OneTimeTransactionOutputData;

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

        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeOutflow);

        assertEquals(date, outputData.getTransactionDate());
        assertEquals(description, outputData.getTransactionDescription());
        assertEquals(category, outputData.getTransactionCategory());
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

        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeInflow);

        assertEquals(date, outputData.getTransactionDate());
        assertEquals(description, outputData.getTransactionDescription());
        assertEquals(category, outputData.getTransactionCategory());
    }

}

