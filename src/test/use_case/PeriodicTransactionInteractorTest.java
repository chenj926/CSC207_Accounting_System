package use_case;

import data_access.UserAccountDataAccessInterface;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PeriodicTransactionInteractorTest {

    private UserAccountDataAccessInterface userDataAccessObject;
    private PeriodicTransactionOutputBoundary presenter;
    private UserAccount userAccount;
    private PeriodicTransactionInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccessObject = new UserAccountDataAccessInterface() {
            private UserAccount account;

            @Override
            public boolean existById(String identification) {
                return account != null && account.getIdentification().equals(identification);
            }

            @Override
            public UserAccount getById(String identification) {
                return account;
            }

            @Override
            public void update(UserAccount userAccount) {
                this.account = userAccount;
            }

            @Override
            public void deleteById(String identifier) {
                if (account != null && account.getIdentification().equals(identifier)) {
                    account = null;
                }
            }

            @Override
            public void save(UserAccount userAccount) {
                this.account = userAccount;
            }
        };

        presenter = new PeriodicTransactionOutputBoundary() {
            @Override
            public void prepareSuccessView(PeriodicTransactionOutputData transactions) {

            }

            @Override
            public void prepareFailView(String message) {
                fail(message);
            }
        };

        userAccount = new UserAccount("username", "password", "id123");
        userDataAccessObject.save(userAccount);

        interactor = new PeriodicTransactionInteractor(userDataAccessObject, presenter, userAccount);
    }

    @Test
    void testValidInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Monthly salary", "01-01-2025", "month"
        );
        interactor.execute(inputData);

        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", -50.0f, "01-01-2024", "Monthly expense", "01-01-2025", "month"
        );
        interactor.execute(inputData);

        assertEquals(-50.0f, userAccount.getTotalOutflow());
        assertEquals(-50.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormat() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "invalid-date", "Monthly salary", "01-01-2025", "month"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2025", "Monthly salary", "01-01-2024", "month"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testPeriodLongerThanDateRange() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "01-02-2024", "year"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "01-02-2024", "10"
        );
        interactor.execute(inputData);

        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNegativeBalance() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", -150.0f, "01-01-2024", "Expense", "01-02-2024", "month"
        );
        interactor.execute(inputData);

        assertEquals(-150.0f, userAccount.getTotalOutflow());
        assertEquals(-150.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidCustomPeriodFormat() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "01-02-2024", "invalid"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }
}


