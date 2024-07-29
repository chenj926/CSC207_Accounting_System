package use_case;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionInputData;
import use_case.transaction.periodic.PeriodicTransactionInteractor;
import use_case.transaction.periodic.PeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

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

            @Override
            public void saveTransaction(OneTimeTransactionOutputData oneTimeOutputData, PeriodicTransactionOutputData periodicOutputData, boolean isOneTimeTransaction) {
                // Implementation for saveTransaction
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

        userAccount = new UserAccount("username", "password", "100.0");
        userDataAccessObject.save(userAccount);

        interactor = new PeriodicTransactionInteractor(userDataAccessObject, presenter, userAccount);
    }

    @Test
    void testValidInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Monthly salary", "month", "01-12-2024"
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
                "-100.0", "01-01-2024", "Monthly expense", "month", "01-12-2024"
        );
        interactor.execute(inputData);

        assertEquals(-1200.0f, userAccount.getTotalOutflow());
        assertEquals(-1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormatInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "invalid-date", "Monthly salary", "month", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "invalid-date", "Monthly expense", "month", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2025", "Monthly salary", "month", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2025", "Monthly expense", "month", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testPeriodLongerThanDateRangeInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "year", "01-02-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testPeriodLongerThanDateRangeOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Costco expense", "year", "01-02-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "10", "01-02-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(400.0f, userAccount.getTotalIncome());
        assertEquals(400.0f, userAccount.getTotalBalance());
    }

    @Test
    void testCustomPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Costco expense", "10", "01-02-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-400.0f, userAccount.getTotalOutflow());
        assertEquals(-400.0f, userAccount.getTotalBalance());
    }

    @Test
    void testPositiveBalance() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "300.0", "01-01-2024", "Expense", "month", "01-02-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(600.0f, userAccount.getTotalIncome());
        assertEquals(600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNegativeBalance() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-300.0", "01-01-2024", "Expense", "month", "01-02-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-600.0f, userAccount.getTotalOutflow());
        assertEquals(-600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidCustomPeriodFormatInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "invalid", "01-02-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidCustomPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "invalid", "01-02-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testZeroCustomPeriodFormatInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "0", "01-02-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testZeroCustomPeriodFormatOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "0", "01-02-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testExactPeriodEndDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExactPeriodEndDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleDayPeriodInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Daily allowance", "day", "05-01-2024"
        );
        interactor.execute(inputData);

        assertEquals(500.0f, userAccount.getTotalIncome());
        assertEquals(500.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleDayPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Daily allowance", "day", "05-01-2024"
        );
        interactor.execute(inputData);

        assertEquals(-500.0f, userAccount.getTotalOutflow());
        assertEquals(-500.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLeapYearPeriodInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Leap year test", "month", "01-03-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(300.0f, userAccount.getTotalIncome());
        assertEquals(300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLeapYearPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Leap year test", "month", "01-03-2024"
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-300.0f, userAccount.getTotalOutflow());
        assertEquals(-300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNoTransactions() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "0.0", "01-01-2024", "No transactions", "month", "01-12-2024"
        );
        interactor.execute(inputData);

        assertEquals(0.0f, userAccount.getTotalIncome());
        assertEquals(0.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeTransactions() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "10000.0", "01-01-2024", "Large Inflow", "month", "01-01-2025"
        );
        interactor.execute(inputData);
        assertEquals(130000.0f, userAccount.getTotalIncome());
        assertEquals(130000.0f, userAccount.getTotalBalance());

        inputData = new PeriodicTransactionInputData(
                "-10000.0", "01-01-2024", "Large Outflow", "month", "01-01-2025"
        );
        interactor.execute(inputData);
        assertEquals(-130000.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleTransactions1() {
        // inflow then outflow
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "200.0", "01-01-2024", "Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Rent", "month", "01-01-2025"
        );
        interactor.execute(inputData2);

        assertEquals(2600.0f, userAccount.getTotalIncome());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleTransactions2() {
        // outflow then inflow
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "-200.0", "01-01-2024", "Rent", "month", "01-01-2025"
        );
        interactor.execute(inputData2);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(-2600.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testOverlappingPeriodsInflow() {
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "month", "01-06-2024"
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "100.0", "01-04-2024", "Bonus", "month", "01-09-2024"
        );
        interactor.execute(inputData2);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1200.0f, userAccount.getTotalIncome());
        assertEquals(1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testOverlappingPeriodsOutflow() {
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Salary", "month", "01-06-2024"
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "-100.0", "01-04-2024", "Bonus", "month", "01-09-2024"
        );
        interactor.execute(inputData2);
        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1200.0f, userAccount.getTotalOutflow());
        assertEquals(-1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidPeriodTypesInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Period", "bi-monthly", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidPeriodTypesOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Period", "bi-monthly", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodZeroInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Custom Period", "0", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodZeroOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Custom Period", "0", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateWithDayPeriodInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "05-01-2024", "Daily Expense", "day", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateWithDayPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "05-01-2024", "Daily Expense", "day", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInflowAndOutflowSamePeriod() {
        PeriodicTransactionInputData inflowData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "month", "01-01-2025"
        );
        interactor.execute(inflowData);

        PeriodicTransactionInputData outflowData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Expense", "month", "01-01-2025"
        );
        interactor.execute(outflowData);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeCustomPeriodInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Salary", "366", "01-01-2025"
        );
        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeCustomPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Salary", "366", "01-01-2025"
        );
        interactor.execute(inputData);

        assertEquals(-200.0f, userAccount.getTotalOutflow());
        assertEquals(-200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testStartDateAndEndDateSameDayInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "One-day Transaction", "day", "01-01-2024"
        );
        interactor.execute(inputData);

        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testStartDateAndEndDateSameDayOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "One-day Transaction", "day", "01-01-2024"
        );
        interactor.execute(inputData);

        assertEquals(-100.0f, userAccount.getTotalOutflow());
        assertEquals(-100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidAmountZero() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Zero Period", "0", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testSingleTransactionInThePastInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2023", "Past Salary", "month", "01-01-2024"
        );

        interactor.execute(inputData);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleTransactionInThePastOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2023", "Past Salary", "month", "01-01-2024"
        );

        interactor.execute(inputData);

        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSameStartAndEndDateWithNonDayPeriodInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Same Date", "month", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testSameStartAndEndDateWithNonDayPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Same Date", "month", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNegativeTransactionAmount() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Negative Transaction", "month", "01-01-2025"
        );

        interactor.execute(inputData);

        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidPeriodValueInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Period", "9999", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidPeriodValueOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Period", "9999", "01-01-2025"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testBoundaryPeriodInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Boundary Period", "365", "01-01-2025"
        );

        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testBoundaryPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Boundary Period", "365", "01-01-2025"
        );

        interactor.execute(inputData);

        assertEquals(-200.0f, userAccount.getTotalOutflow());
        assertEquals(-200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleDifferentTransactionsInflow() {
        // month
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Job Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData1);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1300.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(1300.0f, userAccount.getTotalBalance(), 0.01);

        // week
        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Freelance", "week", "01-01-2025"
        );
        interactor.execute(inputData2);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(6600.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(6600.0f, userAccount.getTotalBalance(), 0.01);

        // year
        PeriodicTransactionInputData inputData3 = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Yearly salary", "year", "01-01-2025"
        );
        interactor.execute(inputData3);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(6800.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(6800.0f, userAccount.getTotalBalance(), 0.01);

        // day
        PeriodicTransactionInputData inputData4 = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Yearly salary", "day", "01-01-2025"
        );
        interactor.execute(inputData4);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(43500.0f, userAccount.getTotalIncome(), 0.01);  // since 2024 is a leap year, it has 366 days +1 in Jan. 1 2025
        assertEquals(43500.0f, userAccount.getTotalBalance(), 0.01);

        // custom
        PeriodicTransactionInputData inputData5 = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Yearly salary", "2", "01-01-2025"
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
        PeriodicTransactionInputData inputData1 = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Job Salary", "month", "01-01-2025"
        );
        interactor.execute(inputData1);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());

        // week
        PeriodicTransactionInputData inputData2 = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Freelance", "week", "01-01-2025"
        );
        interactor.execute(inputData2);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-6600.0f, userAccount.getTotalOutflow());
        assertEquals(-6600.0f, userAccount.getTotalBalance());

        // year
        PeriodicTransactionInputData inputData3 = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Yearly salary", "year", "01-01-2025"
        );
        interactor.execute(inputData3);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-6800.0f, userAccount.getTotalOutflow());
        assertEquals(-6800.0f, userAccount.getTotalBalance());

        // day
        PeriodicTransactionInputData inputData4 = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Yearly salary", "day", "01-01-2025"
        );
        interactor.execute(inputData4);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-43500.0f, userAccount.getTotalOutflow());  // since 2024 is a leap year, it have 366 days -1 in Jan. 1 2025
        assertEquals(-43500.0f, userAccount.getTotalBalance());

        // custom
        PeriodicTransactionInputData inputData5 = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Yearly salary", "2", "01-01-2025"
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
//        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
//                "100.0", "01-01-2024", "Salary", "month", null
//        );
//
//        assertThrows(NullPointerException.class, () -> interactor.execute(inputData));
//    }

    @Test
    void testSingleTransactionExactPeriodInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Exact Period Transaction", "year", "31-12-2024"
        );

        interactor.execute(inputData);

        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleTransactionExactPeriodOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Exact Period Transaction", "year", "31-12-2024"
        );

        interactor.execute(inputData);

        assertEquals(-100.0f, userAccount.getTotalOutflow());
        assertEquals(-100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidWeeklyInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Weekly Allowance", "week", "11-02-2024"
        );
        interactor.execute(inputData);

        assertEquals(600.0f, userAccount.getTotalIncome()); // Assuming 6 weeks between the given dates
        assertEquals(600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidWeeklyOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "-100.0", "01-01-2024", "Weekly Expense", "week", "11-02-2024"
        );
        interactor.execute(inputData);

        assertEquals(-600.0f, userAccount.getTotalOutflow()); // Assuming 6 weeks between the given dates
        assertEquals(-600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormatInStartDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "32-01-2024", "Invalid Start Date", "week", "01-1-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInStartDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "32-01-2024", "Invalid Start Date", "week", "01-1-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInEndDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid End Date", "week", "32-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInEndDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid End Date", "week", "32-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInStartDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-13-2024", "Invalid Month Start Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInStartDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-13-2024", "Invalid Month Start Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInEndDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Month End Date", "week", "01-13-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInEndDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Month End Date", "week", "01-13-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInStartDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "31-04-2024", "Invalid Day Start Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInStartDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "31-04-2024", "Invalid Day Start Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInEndDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Day End Date", "week", "31-11-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInEndDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-01-2024", "Invalid Day End Date", "week", "31-11-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNonLeapYearDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "29-02-2023", "Non-Leap Year Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNonLeapYearDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "29-02-2023", "Non-Leap Year Date", "week", "01-12-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testWeeklyTransactionWithStartDateAfterEndDateInflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-12-2024", "Weekly Transaction", "week", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testWeeklyTransactionWithStartDateAfterEndDateOutflow() {
        PeriodicTransactionInputData inputData = new PeriodicTransactionInputData(
                "100.0", "01-12-2024", "Weekly Transaction", "week", "01-01-2024"
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }
}
