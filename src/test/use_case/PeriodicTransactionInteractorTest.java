package use_case;

import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.*;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class PeriodicTransactionInteractorTest {

    private UserAccountDataAccessInterface userDataAccessObject;
    private PeriodicTransactionOutputBoundary presenter;
    private UserAccount userAccount;
    private PeriodicTransactionInteractor interactor;

    private class ConcretePeriodicTransactionInputData extends PeriodicTransactionInputData {
        public ConcretePeriodicTransactionInputData(String transactionIdentification, String transactionAmount, String transactionStartDate, String transactionDescription, String transactionPeriod, String transactionEndDate, String transactionCategory, String transactionDate) {
            super(transactionIdentification, transactionAmount, transactionStartDate, transactionDescription, transactionPeriod, transactionEndDate, transactionCategory, transactionDate);
        }
    }

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
            public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData, UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {

            }

            @Override
            public List<Transaction> readTransactions(String identification) {
                List<Transaction> transactions = new ArrayList<>();
                return transactions;
            }
        };

        presenter = new PeriodicTransactionOutputBoundary() {

            @Override
            public void prepareSuccessView(Object transactions) {

            }

            @Override
            public void prepareFailView(String message) {
                fail(message);
            }
        };

        userAccount = new UserAccount("username", "password", "100.0");
        userDataAccessObject.save(userAccount);

        interactor = new PeriodicTransactionInteractor(userDataAccessObject, presenter, userAccount) {
            @Override
            protected PeriodicTransactionOutputData createOutputData(PeriodicTransaction transaction) {
                return null;
            }
        };
    }


    @Test
    void testValidInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Monthly salary",
                "month", "01-12-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1200.0f, userAccount.getTotalIncome());
        assertEquals(1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Monthly expense",
                "month", "01-12-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(-1200.0f, userAccount.getTotalOutflow());
        assertEquals(-1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormatInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "invalid-date", "Monthly salary",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "invalid-date", "Monthly expense",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2024", "Monthly salary",
                "month", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2024", "Monthly expense",
                "month", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testPeriodLongerThanDateRangeInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "year", "01-02-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testPeriodLongerThanDateRangeOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Costco expense",
                "year", "01-02-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "10", "01-02-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(400.0f, userAccount.getTotalIncome());
        assertEquals(400.0f, userAccount.getTotalBalance());
    }

    @Test
    void testCustomPeriodOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Costco expense",
                "10", "01-02-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-400.0f, userAccount.getTotalOutflow());
        assertEquals(-400.0f, userAccount.getTotalBalance());
    }

    @Test
    void testPositiveBalance() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "300.0", "01-01-2023", "Expense",
                "month", "01-02-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(600.0f, userAccount.getTotalIncome());
        assertEquals(600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNegativeBalance() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-300.0", "01-01-2023", "Expense",
                "month", "01-02-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-600.0f, userAccount.getTotalOutflow());
        assertEquals(-600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidCustomPeriodFormatInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "invalid", "01-02-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidCustomPeriodOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "invalid", "01-02-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testZeroCustomPeriodFormatInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "0", "01-02-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testZeroCustomPeriodFormatOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "0", "01-02-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testExactPeriodEndDateInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExactPeriodEndDateOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Salary",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleDayPeriodInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Daily allowance",
                "day", "05-01-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(500.0f, userAccount.getTotalIncome());
        assertEquals(500.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleDayPeriodOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Daily allowance",
                "day", "05-01-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(-500.0f, userAccount.getTotalOutflow());
        assertEquals(-500.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLeapYearPeriodInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Leap year test",
                "month", "01-03-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(300.0f, userAccount.getTotalIncome());
        assertEquals(300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLeapYearPeriodOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Leap year test",
                "month", "01-03-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-300.0f, userAccount.getTotalOutflow());
        assertEquals(-300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testNoTransactions() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "0.0", "01-01-2023", "No transactions",
                "month", "01-12-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(0.0f, userAccount.getTotalIncome());
        assertEquals(0.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeTransactions() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "10000.0", "01-01-2023", "Large Inflow",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        assertEquals(130000.0f, userAccount.getTotalIncome());
        assertEquals(130000.0f, userAccount.getTotalBalance());

        inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-10000.0", "01-01-2023", "Large Outflow",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);
        assertEquals(-130000.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleTransactions1() {
        // inflow then outflow
        PeriodicTransactionInputData inputData1 = new ConcretePeriodicTransactionInputData(
                "id999", "200.0", "01-01-2023", "Salary",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Rent",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData2);

        assertEquals(2600.0f, userAccount.getTotalIncome());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleTransactions2() {
        // outflow then inflow
        PeriodicTransactionInputData inputData1 = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new ConcretePeriodicTransactionInputData(
                "id999", "-200.0", "01-01-2023", "Rent",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData2);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(-2600.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testOverlappingPeriodsInflow() {
        PeriodicTransactionInputData inputData1 = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "month", "01-06-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-04-2023", "Bonus",
                "month", "01-09-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData2);
        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1200.0f, userAccount.getTotalIncome());
        assertEquals(1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testOverlappingPeriodsOutflow() {
        PeriodicTransactionInputData inputData1 = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Salary",
                "month", "01-06-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData1);

        PeriodicTransactionInputData inputData2 = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-04-2023", "Bonus",
                "month", "01-09-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData2);
        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1200.0f, userAccount.getTotalOutflow());
        assertEquals(-1200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidPeriodTypesInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Period",
                "bi-monthly", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidPeriodTypesOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Period",
                "bi-monthly", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodZeroInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Custom Period",
                "0", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testCustomPeriodZeroOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Custom Period",
                "0", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateWithDayPeriodInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "05-01-2023", "Daily Expense",
                "day", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testEndDateBeforeStartDateWithDayPeriodOutflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "05-01-2023", "Daily Expense",
                "day", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInflowAndOutflowSamePeriod() {
        PeriodicTransactionInputData inflowData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inflowData);

        PeriodicTransactionInputData outflowData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Expense",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(outflowData);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(0.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeCustomPeriodInflow() {
        PeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Salary",
                "365", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testLargeCustomPeriodOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Salary",
                "365", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(-200.0f, userAccount.getTotalOutflow());
        assertEquals(-200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testStartDateAndEndDateSameDayInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "One-day Transaction",
                "day", "01-01-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testStartDateAndEndDateSameDayOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "One-day Transaction",
                "day", "01-01-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(-100.0f, userAccount.getTotalOutflow());
        assertEquals(-100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidAmountZero() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Zero Period",
                "0", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testSingleTransactionInThePastInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2022", "Past Salary",
                "month", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        interactor.execute(inputData);

        assertEquals(1300.0f, userAccount.getTotalIncome());
        assertEquals(1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleTransactionInThePastOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2022", "Past Salary",
                "month", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        interactor.execute(inputData);

        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSameStartAndEndDateWithNonDayPeriodInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Same Date",
                "month", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testSameStartAndEndDateWithNonDayPeriodOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Same Date",
                "month", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNegativeTransactionAmount() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Negative Transaction",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        interactor.execute(inputData);

        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidPeriodValueInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Period",
                "9999", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidPeriodValueOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Period",
                "9999", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testBoundaryPeriodInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Boundary Period",
                "365", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testBoundaryPeriodOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Boundary Period",
                "365", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        interactor.execute(inputData);

        assertEquals(-200.0f, userAccount.getTotalOutflow());
        assertEquals(-200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testMultipleDifferentTransactionsInflow() {
        // month
        ConcretePeriodicTransactionInputData inputData1 = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Job Salary",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData1);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(1300.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(1300.0f, userAccount.getTotalBalance(), 0.01);

        // week
        ConcretePeriodicTransactionInputData inputData2 = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Freelance",
                "week", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData2);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(5300.0f + 1300.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(5300.0f + 1300.0f, userAccount.getTotalBalance(), 0.01);

        // year
        ConcretePeriodicTransactionInputData inputData3 = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Yearly salary",
                "year", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData3);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(6800.0f, userAccount.getTotalIncome(), 0.01);
        assertEquals(6800.0f, userAccount.getTotalBalance(), 0.01);

        // day
        ConcretePeriodicTransactionInputData inputData4 = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Yearly salary",
                "day", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData4);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(43400.0f, userAccount.getTotalIncome(), 0.01);  // since 2024 is a leap year, it has 366 days +1 in Jan. 1 2025
        assertEquals(43400.0f, userAccount.getTotalBalance(), 0.01);

        // custom
        ConcretePeriodicTransactionInputData inputData5 = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Yearly salary",
                "2", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData5);

        System.out.println(userAccount.getTotalIncome());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(61700.0f, userAccount.getTotalIncome(), 0.01); // since 2024 is a leap year, it has 188 two-day periods +1 in Jan. 1 2025
        assertEquals(61700.0f, userAccount.getTotalBalance(), 0.01);
    }

    @Test
    void testMultipleDifferentTransactionsOutflow() {
        // month
        ConcretePeriodicTransactionInputData inputData1 = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Job Salary",
                "month", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData1);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-1300.0f, userAccount.getTotalOutflow());
        assertEquals(-1300.0f, userAccount.getTotalBalance());

        // week
        ConcretePeriodicTransactionInputData inputData2 = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Freelance",
                "week", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData2);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-6600.0f, userAccount.getTotalOutflow());
        assertEquals(-6600.0f, userAccount.getTotalBalance());

        // year
        ConcretePeriodicTransactionInputData inputData3 = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Yearly salary",
                "year", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData3);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-6800.0f, userAccount.getTotalOutflow());
        assertEquals(-6800.0f, userAccount.getTotalBalance());

        // day
        ConcretePeriodicTransactionInputData inputData4 = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Yearly salary",
                "day", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData4);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-43400.0f, userAccount.getTotalOutflow());  // since 2024 is a leap year, it have 366 days -1 in Jan. 1 2025
        assertEquals(-43400.0f, userAccount.getTotalBalance());

        // custom
        ConcretePeriodicTransactionInputData inputData5 = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Yearly salary",
                "2", "01-01-2024", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData5);

        System.out.println(userAccount.getTotalOutflow());
        System.out.println(userAccount.getTotalBalance());
        assertEquals(-61700.0f, userAccount.getTotalOutflow()); // since 2024 is a leap year, it have 188 two days -$2 in Jan. 1 2025
        assertEquals(-61700.0f, userAccount.getTotalBalance());
    }

//
////    @Test
////    void testTransactionWithoutEndDateOutflow() {
////        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
////                "100.0", "01-01-2023", "Salary", "month", null
////        );
////
////        assertThrows(NullPointerException.class, () -> interactor.execute(inputData));
////    }

    @Test
    void testSingleTransactionExactPeriodInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Exact Period Transaction",
                "year", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        interactor.execute(inputData);

        assertEquals(200.0f, userAccount.getTotalIncome());
        assertEquals(200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testSingleTransactionExactPeriodOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Exact Period Transaction",
                "year", "01-01-2024", "Auto", LocalDate.now().toString()
        );

        interactor.execute(inputData);

        assertEquals(-200.0f, userAccount.getTotalOutflow());
        assertEquals(-200.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidWeeklyInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Weekly Allowance",
                "week", "11-02-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(600.0f, userAccount.getTotalIncome()); // Assuming 6 weeks between the given dates
        assertEquals(600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testValidWeeklyOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "-100.0", "01-01-2023", "Weekly Expense",
                "week", "11-02-2023", "Auto", LocalDate.now().toString()
        );
        interactor.execute(inputData);

        assertEquals(-600.0f, userAccount.getTotalOutflow()); // Assuming 6 weeks between the given dates
        assertEquals(-600.0f, userAccount.getTotalBalance());
    }

    @Test
    void testInvalidDateFormatInStartDateInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "32-01-2023", "Invalid Start Date",
                "week", "01-1-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInStartDateOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "32-01-2023", "Invalid Start Date",
                "week", "01-1-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInEndDateInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid End Date",
                "week", "32-12-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDateFormatInEndDateOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid End Date",
                "week", "32-12-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInStartDateInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-13-2023", "Invalid Month Start Date",
                "week", "01-12-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInStartDateOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-13-2023", "Invalid Month Start Date",
                "week", "01-12-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInEndDateInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Month End Date",
                "week", "01-13-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidMonthInEndDateOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Month End Date",
                "week", "01-13-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInStartDateInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "31-04-2023", "Invalid Day Start Date",
                "week", "01-12-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInStartDateOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "31-04-2023", "Invalid Day Start Date",
                "week", "01-12-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInEndDateInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Day End Date",
                "week", "31-11-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testInvalidDayInEndDateOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-01-2023", "Invalid Day End Date",
                "week", "31-11-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNonLeapYearDateInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "29-02-2023", "Non-Leap Year Date",
                "week", "01-12-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testNonLeapYearDateOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "29-02-2023", "Non-Leap Year Date",
                "week", "01-12-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testWeeklyTransactionWithStartDateAfterEndDateInflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-12-2023", "Weekly Transaction",
                "week", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }

    @Test
    void testWeeklyTransactionWithStartDateAfterEndDateOutflow() {
        ConcretePeriodicTransactionInputData inputData = new ConcretePeriodicTransactionInputData(
                "id999", "100.0", "01-12-2023", "Weekly Transaction",
                "week", "01-01-2023", "Auto", LocalDate.now().toString()
        );

        assertThrows(AssertionError.class, () -> interactor.execute(inputData));
    }


}



