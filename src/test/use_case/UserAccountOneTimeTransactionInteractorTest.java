package use_case;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.UserAccountOneTimeTransactionInputData;
import use_case.transaction.one_time.UserAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.UserAccountOneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.UserAccountPeriodicTransactionOutputData;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountOneTimeTransactionInteractorTest {

    private UserAccountOneTimeTransactionInteractor interactor;
    private UserAccountDataAccessInterface userAccountDataAccessObject;
    private UserAccountOneTimeTransactionOutputBoundary presenter;
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
            public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData, UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isOneTimeTransaction) {
                // Implementation for saveTransaction
            }
        };

        presenter = new UserAccountOneTimeTransactionOutputBoundary() {
            @Override
            public void prepareSuccessView(UserAccountOneTimeTransactionOutputData outputData) {
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid date format! Plz enter again", error);
            }
        };

        interactor = new UserAccountOneTimeTransactionInteractor(userAccountDataAccessObject, presenter, userAccount);
    }

    @Test
    void testExecuteInflow() {
        UserAccountOneTimeTransactionInputData inputData = new UserAccountOneTimeTransactionInputData("100.0", "01-01-2024", "Salary", "Income");
        interactor.execute(inputData);
        assertEquals(100.0f, userAccount.getTotalIncome());
        assertEquals(100.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExecuteOutflow() {
        UserAccountOneTimeTransactionInputData inputData = new UserAccountOneTimeTransactionInputData("-50.0", "01-01-2024", "Rent", "Expense");
        interactor.execute(inputData);
        assertEquals(-50.0f, userAccount.getTotalOutflow());
        assertEquals(-50.0f, userAccount.getTotalBalance());
    }

    @Test
    void testExecuteInvalidDate() {
        UserAccountOneTimeTransactionInputData inputData = new UserAccountOneTimeTransactionInputData("100.0f", "invalid-date", "Salary", "Income");
        interactor.execute(inputData);

    }

    @Test
    void testExecuteInvalidDate1() {
        UserAccountOneTimeTransactionInputData inputData = new UserAccountOneTimeTransactionInputData("100.0f", "29-02-2023", "Salary", "Income");
        interactor.execute(inputData);
    }
    @Test
    void testExecuteInvalidDate2() {
        UserAccountOneTimeTransactionInputData inputData = new UserAccountOneTimeTransactionInputData("100.0f", "32-12-2024", "Salary", "Income");
        interactor.execute(inputData);
    }
    @Test
    void testExecuteInvalidDate3() {
        UserAccountOneTimeTransactionInputData inputData = new UserAccountOneTimeTransactionInputData("100.0f", "00-02-2024", "Salary", "Income");
        interactor.execute(inputData);
    }



}
