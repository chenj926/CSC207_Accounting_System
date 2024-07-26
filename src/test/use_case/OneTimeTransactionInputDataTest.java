package use_case;

import org.junit.jupiter.api.Test;
import use_case.transaction.OneTimeTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneTimeTransactionInputDataTest {

    @Test
    public void testOneTimeTransactionInputData() {
        String transactionAmount = "100.0";
//        String identification = "user123";
        String transactionDate = "2024-07-17";
        String transactionDescription = "Grocery Shopping";
        String transactionCategory = "Food";

        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData(
                transactionAmount,
                transactionDate,
                transactionDescription,
                transactionCategory
        );

        assertEquals(transactionAmount, inputData.getTransactionAmount());
//        assertEquals(identification, inputData.getIdentification());
        assertEquals(transactionDate, inputData.getTransactionDate());
        assertEquals(transactionDescription, inputData.getTransactionDescription());
        assertEquals(transactionCategory, inputData.getTransactionCategory());
    }
}

