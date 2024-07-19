package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.PeriodicTransactionInputBoundary;
import use_case.PeriodicTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeriodicTransactionControllerTest {

    private PeriodicTransactionController controller;
    private StubPeriodicTransactionInputBoundary stubInputBoundary;

    @BeforeEach
    void setUp() {
        stubInputBoundary = new StubPeriodicTransactionInputBoundary();
        controller = new PeriodicTransactionController(stubInputBoundary);
    }

    @Test
    void testExecute() {
        // Arrange
        String identification = "12345";
        float amount = 100.0f;
        String startDate = "2024-07-18";
        String description = "Monthly Subscription";
        String period = "Monthly";
        String endDate = "2025-07-18";

        PeriodicTransactionInputData expectedData = new PeriodicTransactionInputData(
                identification, amount, startDate, description, period, endDate
        );

        // Act
        controller.execute(identification, amount, startDate, description, period, endDate);

        // Assert
        assertEquals(expectedData.getIdentification(), stubInputBoundary.receivedData.getIdentification());
        assertEquals(expectedData.getTransactionAmount(), stubInputBoundary.receivedData.getTransactionAmount());
        assertEquals(expectedData.getTransactionStartDate(), stubInputBoundary.receivedData.getTransactionStartDate());
        assertEquals(expectedData.getTransactionDescription(), stubInputBoundary.receivedData.getTransactionDescription());
        assertEquals(expectedData.getTransactionPeriod(), stubInputBoundary.receivedData.getTransactionPeriod());
        assertEquals(expectedData.getTransactionEndDate(), stubInputBoundary.receivedData.getTransactionEndDate());
    }

    private static class StubPeriodicTransactionInputBoundary implements PeriodicTransactionInputBoundary {
        private PeriodicTransactionInputData receivedData;

        @Override
        public void execute(PeriodicTransactionInputData inputData) {
            this.receivedData = inputData;
        }
    }
}



