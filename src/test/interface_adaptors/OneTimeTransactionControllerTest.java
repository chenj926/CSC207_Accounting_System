package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.OneTimeTransactionInputBoundary;
import use_case.OneTimeTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneTimeTransactionControllerTest {

    private OneTimeTransactionController controller;
    private MockOneTimeTransactionInputBoundary mockInteractor;

    @BeforeEach
    void setUp() {
        mockInteractor = new MockOneTimeTransactionInputBoundary();
        controller = new OneTimeTransactionController(mockInteractor);
    }

    @Test
    void testExecute() {
        // Arrange
        float amount = 100.0f;
        String identification = "12345";
        String transactionDate = "2024-07-18";
        String transactionDescription = "Grocery Shopping";
        String transactionCategory = "Food";

        OneTimeTransactionInputData expectedData = new OneTimeTransactionInputData(
                amount, identification, transactionDate, transactionDescription, transactionCategory
        );

        // Act
        controller.execute(amount, identification, transactionDate, transactionDescription, transactionCategory);

        // Assert
        assertEquals(expectedData.getTransactionAmount(), mockInteractor.receivedData.getTransactionAmount());
        assertEquals(expectedData.getIdentification(), mockInteractor.receivedData.getIdentification());
        assertEquals(expectedData.getTransactionDate(), mockInteractor.receivedData.getTransactionDate());
        assertEquals(expectedData.getTransactionDescription(), mockInteractor.receivedData.getTransactionDescription());
        assertEquals(expectedData.getTransactionCategory(), mockInteractor.receivedData.getTransactionCategory());
    }

    private class MockOneTimeTransactionInputBoundary implements OneTimeTransactionInputBoundary {
        private OneTimeTransactionInputData receivedData;

        @Override
        public void execute(OneTimeTransactionInputData inputData) {
            this.receivedData = inputData;
        }
    }
}




