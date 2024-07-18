package use_case;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeriodicTransactionInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String identification = "user123";
        float transactionAmount = 150.0f;
        String transactionStartDate = "2024-01-01";
        String transactionDescription = "Monthly Subscription";
        String transactionPeriod = "Monthly";
        String transactionEndDate = "2024-12-31";

        // Act
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                identification,
                transactionAmount,
                transactionStartDate,
                transactionDescription,
                transactionPeriod,
                transactionEndDate
        );

        // Assert
        assertEquals(identification, inputData.getIdentification());
        assertEquals(transactionAmount, inputData.getTransactionAmount());
        assertEquals(transactionStartDate, inputData.getTransactionStartDate());
        assertEquals(transactionDescription, inputData.getTransactionDescription());
        assertEquals(transactionPeriod, inputData.getTransactionPeriod());
        assertEquals(transactionEndDate, inputData.getTransactionEndDate());
    }
}

