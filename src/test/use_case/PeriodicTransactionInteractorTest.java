package use_case;

import data_access.UserAccountDataAccessInterface;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
                "id123", 100.0f, "01-01-2024", "Monthly salary", "month", "01-12-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1200.0f, userAccount.getTotalIncome());
        assertEquals(1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", -50.0f, "01-01-2024", "Monthly expense", "month", "01-12-2024"
        );
        interactor.execute(inputData);

        assertEquals(-600.0f, userAccount.getTotalOutflow());
        assertEquals(-600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormat() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "invalid-date", "Monthly salary", "month", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2025", "Monthly salary", "month", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testPeriodLongerThanDateRange() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "year", "01-02-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "10", "01-02-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(400.0f, userAccount.getTotalIncome());
        assertEquals(400.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNegativeBalance() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", -150.0f, "01-01-2024", "Expense", "month", "01-02-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-300.0f, userAccount.getTotalOutflow());
        assertEquals(-300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidCustomPeriodFormat() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "invalid", "01-02-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testExactPeriodEndDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleDayPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Daily allowance", "day", "05-01-2024"
        );
        interactor.execute(inputData);

        assertEquals(500.0f, userAccount.getTotalIncome());
        assertEquals(500.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLeapYearPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Leap year test", "month", "01-03-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(300.0f, userAccount.getTotalIncome());
        assertEquals(300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNoTransactions() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 0.0f, "01-01-2024", "No transactions", "month", "01-12-2024"
        );
        interactor.execute(inputData);

        assertEquals(0.0f, userAccount.getTotalIncome());
        assertEquals(0.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }
}




