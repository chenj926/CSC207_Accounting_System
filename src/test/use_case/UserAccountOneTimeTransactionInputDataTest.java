package use_case;

import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.UserAccountOneTimeTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountOneTimeTransactionInputDataTest {

    @Test
    public void testOneTimeTransactionInputData() {
        String transactionAmount = "100.0";
//        String identification = "user123";
        String transactionDate = "2024-07-17";
        String transactionDescription = "Grocery Shopping";
        String transactionCategory = "Food";

        UserAccountOneTimeTransactionInputData inputData = new UserAccountOneTimeTransactionInputData(
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

