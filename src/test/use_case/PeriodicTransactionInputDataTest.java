package use_case;

import org.junit.jupiter.api.Test;
import use_case.Transaction.PeriodicTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeriodicTransactionInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        float transactionAmount = 150.0f;
        String transactionAmountString = Float.toString(transactionAmount);
        String transactionStartDate = "2024-01-01";
        String transactionDescription = "Monthly Subscription";
        String transactionPeriod = "Monthly";
        String transactionEndDate = "2024-12-31";

        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                transactionAmountString,
                transactionStartDate,
                transactionDescription,
                transactionPeriod,
                transactionEndDate
        );

        assertEquals(transactionAmountString, inputData.getTransactionAmount());
        assertEquals(transactionStartDate, inputData.getTransactionStartDate());
        assertEquals(transactionDescription, inputData.getTransactionDescription());
        assertEquals(transactionPeriod, inputData.getTransactionPeriod());
        assertEquals(transactionEndDate, inputData.getTransactionEndDate());
    }
}

