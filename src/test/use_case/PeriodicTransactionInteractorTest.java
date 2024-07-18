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
    @Test
    void testLargeTransactions() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 1000000.0f, "01-01-2024", "Large Inflow", "month", "01-01-2025"
        );
        interactor.execute(inputData);
        assertEquals(13000000.0f, userAccount.getTotalIncome());
        assertEquals(13000000.0f, userAccount.getTotalBalance());

        inputData = new PeriodicTransactionInputData(
                "id123", -500000.0f, "01-01-2024", "Large Outflow", "month", "01-01-2025"
        );
        interactor.execute(inputData);
        assertEquals(-6500000.0f, userAccount.getTotalOutflow());
        assertEquals(6500000.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleTransactions() {
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "id123", 200.0f, "01-01-2024", "Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "id123", -100.0f, "01-01-2024", "Rent", "month", "01-01-2025"
        );
        interactor.execute(inputData2);

        assertEquals(2600.0f, userAccount.getTotalIncome());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testOverlappingPeriods() {
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "month", "01-06-2024"
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "id123", 50.0f, "01-04-2024", "Bonus", "month", "01-09-2024"
        );
        interactor.execute(inputData2);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(900.0f, userAccount.getTotalIncome());
        assertEquals(900.0f, userAccount.getTotalBalance());
    }


    @Test
    void testInvalidPeriodTypes() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Invalid Period", "bi-monthly", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodZero() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Invalid Custom Period", "0", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateWithDayPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "05-01-2024", "Daily Expense", "day", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInflowAndOutflowSamePeriod() {
        PeriodicTransactionInputData inflowData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "month", "01-01-2025"
        );
        interactor.execute(inflowData);

        PeriodicTransactionInputData outflowData = new PeriodicTransactionInputData(
                "id123", -50.0f, "01-01-2024", "Expense", "month", "01-01-2025"
        );
        interactor.execute(outflowData);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(-650.0f, userAccount.getTotalOutflow());
        assertEquals(650.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeCustomPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "366", "01-01-2025"
        );
        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testStartDateAndEndDateSameDay() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "One-day Transaction", "day", "01-01-2024"
        );
        interactor.execute(inputData);

        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }
    @Test
    void testInvalidAmountZero() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 0.0f, "01-01-2024", "Zero Period", "0", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testSingleTransactionInThePast() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2023", "Past Salary", "month", "01-01-2024"
        );

        interactor.execute(inputData);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSameStartAndEndDateWithNonDayPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Same Date", "month", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test

    void testNegativeTransactionAmount() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", -200.0f, "01-01-2024", "Negative Transaction", "month", "01-01-2025"
        );

        interactor.execute(inputData);

        assertEquals(-2600.0f, userAccount.getTotalOutflow());
        assertEquals(-2600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidPeriodValue() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Invalid Period", "9999", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testBoundaryPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Boundary Period", "365", "01-01-2025"
        );

        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleDifferentTransactions() {
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "id123", 200.0f, "01-01-2024", "Job Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "id123", 150.0f, "01-01-2024", "Freelance", "week", "01-01-2025"
        );
        interactor.execute(inputData2);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(10550.0f, userAccount.getTotalIncome());
        assertEquals(10550.0f, userAccount.getTotalBalance());
    }

    @Test
    void testTransactionWithoutEndDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Salary", "month", null
        );

        assertThrows(NullPointerException.class, () -> interactor.execute(inputData));
    }

    @Test
    void testSingleTransactionExactPeriod() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Exact Period Transaction", "year", "01-01-2025"
        );

        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }
    @Test
    void testValidWeeklyInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Weekly Allowance", "week", "01-12-2024"
        );
        interactor.execute(inputData);

        assertEquals(500.0f, userAccount.getTotalIncome()); // Assuming 5 weeks between the given dates
        assertEquals(500.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidWeeklyOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", -50.0f, "01-01-2024", "Weekly Expense", "week", "01-12-2024"
        );
        interactor.execute(inputData);

        assertEquals(-250.0f, userAccount.getTotalOutflow()); // Assuming 5 weeks between the given dates
        assertEquals(-250.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormatInStartDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "32-01-2024", "Invalid Start Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInEndDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Invalid End Date", "week", "32-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInStartDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-13-2024", "Invalid Month Start Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInEndDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Invalid Month End Date", "week", "01-13-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInStartDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "31-04-2024", "Invalid Day Start Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInEndDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-01-2024", "Invalid Day End Date", "week", "31-11-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNonLeapYearDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "29-02-2023", "Non-Leap Year Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testWeeklyTransactionWithStartDateAfterEndDate() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "id123", 100.0f, "01-12-2024", "Weekly Transaction", "week", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }



}




