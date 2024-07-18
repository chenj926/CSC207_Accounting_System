package use_case;

import data_access.UserAccountDataAccessInterface;
import entity.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OneTimeTransactionInteractorTest {

    private OneTimeTransactionInteractor interactor;
    private UserAccountDataAccessInterface userAccountDataAccessObject;
    private OneTimeTransactionOutputBoundary presenter;
    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        userAccount = new UserAccount("user1", "pass1", "id1");
        userAccountDataAccessObject = new UserAccountDataAccessInterface() {
            @Override
            public boolean existById(String identification) {
                return identification.equals(userAccount.getIdentification());
            }

            @Override
            public UserAccount getById(String identification) {
                return userAccount;
            }

            @Override
            public void update(UserAccount userAccount) {
                // No implementation needed for this test
            }

            @Override
            public void deleteById(String identifier) {
                // No implementation needed for this test
            }

            @Override
            public void save(UserAccount userAccount) {
                // No implementation needed for this test
            }
        };

        presenter = new OneTimeTransactionOutputBoundary() {
            @Override
            public void prepareSuccessView(OneTimeTransactionOutputData outputData) {
                assertTrue(outputData.getNewBalance() >= 0);
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid date format! Plz enter again", error);
            }
        };

        interactor = new OneTimeTransactionInteractor(userAccountDataAccessObject, presenter, userAccount);
    }

    @Test
    void testExecuteInflow() {
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData(100.0f, "id1", "01-01-2024", "Salary", "Income");
        interactor.execute(inputData);
        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExecuteOutflow() {
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData(-50.0f, "id2", "01-01-2024", "Rent", "Expense");
        interactor.execute(inputData);
        assertEquals(-50.0f, userAccount.getTotalOutflow());
        assertEquals(-50.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExecuteInvalidDate() {
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData(100.0f, "id3", "invalid-date", "Salary", "Income");
        interactor.execute(inputData);
    }
}
