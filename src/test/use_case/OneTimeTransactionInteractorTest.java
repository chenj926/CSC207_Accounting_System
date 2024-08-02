package use_case;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.OneTimeTransactionInputData;
import use_case.transaction.one_time.OneTimeTransactionInteractor;
import use_case.transaction.one_time.OneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
            }

            @Override
            public void deleteById(String identifier) {
            }

            @Override
            public void save(UserAccount userAccount) {
            }

            @Override
            public void saveTransaction(OneTimeTransactionOutputData oneTimeOutputData, PeriodicTransactionOutputData periodicOutputData, boolean isOneTimeTransaction) {
                // Implementation for saveTransaction
            }

            @Override
            public List<Transaction> readTransactions(String identification) {
                List<Transaction> transactions = new ArrayList<>();
                return transactions;
            }
        };

        presenter = new OneTimeTransactionOutputBoundary() {
            @Override
            public void prepareSuccessView(OneTimeTransactionOutputData outputData) {
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
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData("100.0", "01-01-2024", "Salary", "Income");
        interactor.execute(inputData);
        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExecuteOutflow() {
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData("-50.0", "01-01-2024", "Rent", "Expense");
        interactor.execute(inputData);
        assertEquals(-50.0f, userAccount.getTotalOutflow());
        assertEquals(-50.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExecuteInvalidDate() {
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData("100.0f", "invalid-date", "Salary", "Income");
        interactor.execute(inputData);

    }

    @Test
    void testExecuteInvalidDate1() {
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData("100.0f", "29-02-2023", "Salary", "Income");
        interactor.execute(inputData);
    }
    @Test
    void testExecuteInvalidDate2() {
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData("100.0f", "32-12-2024", "Salary", "Income");
        interactor.execute(inputData);
    }
    @Test
    void testExecuteInvalidDate3() {
        OneTimeTransactionInputData inputData = new OneTimeTransactionInputData("100.0f", "00-02-2024", "Salary", "Income");
        interactor.execute(inputData);
    }



}
