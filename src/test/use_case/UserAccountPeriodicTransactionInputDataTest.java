package use_case;

import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountPeriodicTransactionInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String transactionId = "transactionId";
        float transactionAmount = 150.0f;
        String transactionAmountString = Float.toString(transactionAmount);
        String transactionStartDate = "2024-01-01";
        String transactionDescription = "Monthly Subscription";
        String transactionPeriod = "Monthly";
        String transactionEndDate = "2024-12-31";
        String transactionCategory = "Auto";
        String transactionDate = "2024-01-01";;

        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                transactionId,
                transactionAmountString,
                transactionStartDate,
                transactionDescription,
                transactionPeriod,
                transactionEndDate,
                transactionCategory,
                transactionDate
        );

        assertEquals(transactionAmountString, inputData.getTransactionAmount());
        assertEquals(transactionStartDate, inputData.getTransactionStartDate());
        assertEquals(transactionDescription, inputData.getTransactionDescription());
        assertEquals(transactionPeriod, inputData.getTransactionPeriod());
        assertEquals(transactionEndDate, inputData.getTransactionEndDate());
    }
}

