package use_case;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.UserAccountPeriodicTransactionInputData;
import use_case.transaction.periodic.UserAccountPeriodicTransactionInteractor;
import use_case.transaction.periodic.UserAccountPeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.UserAccountPeriodicTransactionOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class PeriodicTransactionInteractorTest {

    private UserAccountDataAccessInterface userDataAccessObject;
    private UserAccountPeriodicTransactionOutputBoundary presenter;
    private UserAccount userAccount;
    private UserAccountPeriodicTransactionInteractor interactor;

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

            @Override
            public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData, UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isOneTimeTransaction) {
                // Implementation for saveTransaction
            }
        };

        presenter = new UserAccountPeriodicTransactionOutputBoundary() {
            @Override
            public void prepareSuccessView(UserAccountPeriodicTransactionOutputData transactions) {

            }

            @Override
            public void prepareFailView(String message) {
                fail(message);
            }
        };

        userAccount = new UserAccount("username", "password", "100.0");
        userDataAccessObject.save(userAccount);

        interactor = new UserAccountPeriodicTransactionInteractor(userDataAccessObject, presenter, userAccount);
    }

    @Test
    void testValidInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Monthly salary",
                "month", "01-12-2024", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1200.0f, userAccount.getTotalIncome());
        assertEquals(1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Monthly expense",
                "month", "01-12-2024", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(-1200.0f, userAccount.getTotalOutflow());
        assertEquals(-1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormatInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "invalid-date", "Monthly salary",
                "month", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "invalid-date", "Monthly expense",
                "month", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2025", "Monthly salary",
                "month", "01-01-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2025", "Monthly expense",
                "month", "01-01-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testPeriodLongerThanDateRangeInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "year", "01-02-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testPeriodLongerThanDateRangeOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Costco expense",
                "year", "01-02-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "10", "01-02-2024", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(400.0f, userAccount.getTotalIncome());
        assertEquals(400.0f, userAccount.getTotalBalance());
    }

    @Test
    void testCustomPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Costco expense",
                "10", "01-02-2024", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-400.0f, userAccount.getTotalOutflow());
        assertEquals(-400.0f, userAccount.getTotalBalance());
    }

    @Test
    void testPositiveBalance() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "300.0", "01-01-2024", "Expense",
                "month", "01-02-2024", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(600.0f, userAccount.getTotalIncome());
        assertEquals(600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNegativeBalance() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-300.0", "01-01-2024", "Expense",
                "month", "01-02-2024", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-600.0f, userAccount.getTotalOutflow());
        assertEquals(-600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidCustomPeriodFormatInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "invalid", "01-02-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidCustomPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "invalid", "01-02-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testZeroCustomPeriodFormatInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "0", "01-02-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testZeroCustomPeriodFormatOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "0", "01-02-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testExactPeriodEndDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExactPeriodEndDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Salary",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleDayPeriodInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Daily allowance",
                "day", "05-01-2024", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(500.0f, userAccount.getTotalIncome());
        assertEquals(500.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleDayPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Daily allowance",
                "day", "05-01-2024", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(-500.0f, userAccount.getTotalOutflow());
        assertEquals(-500.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLeapYearPeriodInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Leap year test",
                "month", "01-03-2024", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(300.0f, userAccount.getTotalIncome());
        assertEquals(300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLeapYearPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Leap year test",
                "month", "01-03-2024", "Auto"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-300.0f, userAccount.getTotalOutflow());
        assertEquals(-300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNoTransactions() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "0.0", "01-01-2024", "No transactions",
                "month", "01-12-2024", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(0.0f, userAccount.getTotalIncome());
        assertEquals(0.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeTransactions() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "10000.0", "01-01-2024", "Large Inflow",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData);
        assertEquals(130000.0f, userAccount.getTotalIncome());
        assertEquals(130000.0f, userAccount.getTotalBalance());

        inputData = new UserAccountPeriodicTransactionInputData(
                "-10000.0", "01-01-2024", "Large Outflow",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData);
        assertEquals(-130000.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleTransactions1() {
        // inflow then outflow
        UserAccountPeriodicTransactionInputData inputData1 = new UserAccountPeriodicTransactionInputData(
                "200.0", "01-01-2024", "Salary",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData1);

        UserAccountPeriodicTransactionInputData inputData2 = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Rent",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData2);

        assertEquals(2600.0f, userAccount.getTotalIncome());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleTransactions2() {
        // outflow then inflow
        UserAccountPeriodicTransactionInputData inputData1 = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData1);

        UserAccountPeriodicTransactionInputData inputData2 = new UserAccountPeriodicTransactionInputData(
                "-200.0", "01-01-2024", "Rent",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData2);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(-2600.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testOverlappingPeriodsInflow() {
        UserAccountPeriodicTransactionInputData inputData1 = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "month", "01-06-2024", "Auto"
        );
        interactor.execute(inputData1);

        UserAccountPeriodicTransactionInputData inputData2 = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-04-2024", "Bonus",
                "month", "01-09-2024", "Auto"
        );
        interactor.execute(inputData2);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1200.0f, userAccount.getTotalIncome());
        assertEquals(1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testOverlappingPeriodsOutflow() {
        UserAccountPeriodicTransactionInputData inputData1 = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Salary",
                "month", "01-06-2024", "Auto"
        );
        interactor.execute(inputData1);

        UserAccountPeriodicTransactionInputData inputData2 = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-04-2024", "Bonus",
                "month", "01-09-2024", "Auto"
        );
        interactor.execute(inputData2);
        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1200.0f, userAccount.getTotalOutflow());
        assertEquals(-1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidPeriodTypesInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Period",
                "bi-monthly", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidPeriodTypesOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Period",
                "bi-monthly", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodZeroInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Custom Period",
                "0", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodZeroOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Custom Period",
                "0", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateWithDayPeriodInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "05-01-2024", "Daily Expense",
                "day", "01-01-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateWithDayPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "05-01-2024", "Daily Expense",
                "day", "01-01-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInflowAndOutflowSamePeriod() {
        UserAccountPeriodicTransactionInputData inflowData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inflowData);

        UserAccountPeriodicTransactionInputData outflowData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Expense",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(outflowData);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeCustomPeriodInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary",
                "366", "01-01-2025", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeCustomPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Salary",
                "366", "01-01-2025", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(-200.0f, userAccount.getTotalOutflow());
        assertEquals(-200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testStartDateAndEndDateSameDayInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "One-day Transaction",
                "day", "01-01-2024", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testStartDateAndEndDateSameDayOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "One-day Transaction",
                "day", "01-01-2024", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(-100.0f, userAccount.getTotalOutflow());
        assertEquals(-100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidAmountZero() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Zero Period",
                "0", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testSingleTransactionInThePastInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2023", "Past Salary",
                "month", "01-01-2024", "Auto"
        );

        interactor.execute(inputData);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleTransactionInThePastOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2023", "Past Salary",
                "month", "01-01-2024", "Auto"
        );

        interactor.execute(inputData);

        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSameStartAndEndDateWithNonDayPeriodInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Same Date",
                "month", "01-01-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testSameStartAndEndDateWithNonDayPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Same Date",
                "month", "01-01-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNegativeTransactionAmount() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Negative Transaction",
                "month", "01-01-2025", "Auto"
        );

        interactor.execute(inputData);

        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidPeriodValueInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Period",
                "9999", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidPeriodValueOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Period",
                "9999", "01-01-2025", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testBoundaryPeriodInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Boundary Period",
                "365", "01-01-2025", "Auto"
        );

        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testBoundaryPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Boundary Period",
                "365", "01-01-2025", "Auto"
        );

        interactor.execute(inputData);

        assertEquals(-200.0f, userAccount.getTotalOutflow());
        assertEquals(-200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleDifferentTransactionsInflow() {
        // month
        UserAccountPeriodicTransactionInputData inputData1 = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Job Salary",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData1);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1300.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(1300.0f, userAccount.getTotalBalance(), 0.01);

        // week
        UserAccountPeriodicTransactionInputData inputData2 = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Freelance",
                "week", "01-01-2025", "Auto"
        );
        interactor.execute(inputData2);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(6600.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(6600.0f, userAccount.getTotalBalance(), 0.01);

        // year
        UserAccountPeriodicTransactionInputData inputData3 = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Yearly salary",
                "year", "01-01-2025", "Auto"
        );
        interactor.execute(inputData3);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(6800.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(6800.0f, userAccount.getTotalBalance(), 0.01);

        // day
        UserAccountPeriodicTransactionInputData inputData4 = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Yearly salary",
                "day", "01-01-2025", "Auto"
        );
        interactor.execute(inputData4);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(43500.0f, userAccount.getTotalIncome(), 0.01);  // since 2024 is a leap year, it has 366 days +1 in Jan. 1 2025
        assertEquals(43500.0f, userAccount.getTotalBalance(), 0.01);

        // custom
        UserAccountPeriodicTransactionInputData inputData5 = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Yearly salary",
                "2", "01-01-2025", "Auto"
        );
        interactor.execute(inputData5);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(61900.0f, userAccount.getTotalIncome(), 0.01); // since 2024 is a leap year, it has 188 two-day periods +1 in Jan. 1 2025
        assertEquals(61900.0f, userAccount.getTotalBalance(), 0.01);
    }

    @Test
    void testMultipleDifferentTransactionsOutflow() {
        // month
        UserAccountPeriodicTransactionInputData inputData1 = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Job Salary",
                "month", "01-01-2025", "Auto"
        );
        interactor.execute(inputData1);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());

        // week
        UserAccountPeriodicTransactionInputData inputData2 = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Freelance",
                "week", "01-01-2025", "Auto"
        );
        interactor.execute(inputData2);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-6600.0f, userAccount.getTotalOutflow());
        assertEquals(-6600.0f, userAccount.getTotalBalance());

        // year
        UserAccountPeriodicTransactionInputData inputData3 = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Yearly salary",
                "year", "01-01-2025", "Auto"
        );
        interactor.execute(inputData3);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-6800.0f, userAccount.getTotalOutflow());
        assertEquals(-6800.0f, userAccount.getTotalBalance());

        // day
        UserAccountPeriodicTransactionInputData inputData4 = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Yearly salary",
                "day", "01-01-2025", "Auto"
        );
        interactor.execute(inputData4);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-43500.0f, userAccount.getTotalOutflow());  // since 2024 is a leap year, it have 366 days -1 in Jan. 1 2025
        assertEquals(-43500.0f, userAccount.getTotalBalance());

        // custom
        UserAccountPeriodicTransactionInputData inputData5 = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Yearly salary",
                "2", "01-01-2025", "Auto"
        );
        interactor.execute(inputData5);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-61900.0f, userAccount.getTotalOutflow()); // since 2024 is a leap year, it have 188 two days -$2 in Jan. 1 2025
        assertEquals(-61900.0f, userAccount.getTotalBalance());
    }

//
//    @Test
//    void testTransactionWithoutEndDateOutflow() {
//        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
//                "100.0", "01-01-2024", "Salary", "month", null
//        );
//
//        assertThrows(NullPointerException.class, () -> interactor.execute(inputData));
//    }

    @Test
    void testSingleTransactionExactPeriodInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Exact Period Transaction",
                "year", "31-12-2024", "Auto"
        );

        interactor.execute(inputData);

        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleTransactionExactPeriodOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Exact Period Transaction",
                "year", "31-12-2024", "Auto"
        );

        interactor.execute(inputData);

        assertEquals(-100.0f, userAccount.getTotalOutflow());
        assertEquals(-100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidWeeklyInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Weekly Allowance",
                "week", "11-02-2024", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(600.0f, userAccount.getTotalIncome()); // Assuming 6 weeks between the given dates
        assertEquals(600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidWeeklyOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Weekly Expense",
                "week", "11-02-2024", "Auto"
        );
        interactor.execute(inputData);

        assertEquals(-600.0f, userAccount.getTotalOutflow()); // Assuming 6 weeks between the given dates
        assertEquals(-600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormatInStartDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "32-01-2024", "Invalid Start Date",
                "week", "01-1-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInStartDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "32-01-2024", "Invalid Start Date",
                "week", "01-1-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInEndDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid End Date",
                "week", "32-12-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInEndDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid End Date",
                "week", "32-12-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInStartDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-13-2024", "Invalid Month Start Date",
                "week", "01-12-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInStartDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-13-2024", "Invalid Month Start Date",
                "week", "01-12-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInEndDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Month End Date",
                "week", "01-13-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInEndDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Month End Date",
                "week", "01-13-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInStartDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "31-04-2024", "Invalid Day Start Date",
                "week", "01-12-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInStartDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "31-04-2024", "Invalid Day Start Date",
                "week", "01-12-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInEndDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Day End Date",
                "week", "31-11-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInEndDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Day End Date",
                "week", "31-11-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNonLeapYearDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "29-02-2023", "Non-Leap Year Date",
                "week", "01-12-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNonLeapYearDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "29-02-2023", "Non-Leap Year Date",
                "week", "01-12-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testWeeklyTransactionWithStartDateAfterEndDateInflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-12-2024", "Weekly Transaction",
                "week", "01-01-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testWeeklyTransactionWithStartDateAfterEndDateOutflow() {
        UserAccountPeriodicTransactionInputData inputData = new UserAccountPeriodicTransactionInputData(
                "100.0", "01-12-2024", "Weekly Transaction",
                "week", "01-01-2024", "Auto"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }
}
