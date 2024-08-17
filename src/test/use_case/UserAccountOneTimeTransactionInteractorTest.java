package use_case;

import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.*;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountOneTimeTransactionInteractorTest {

    private class ConcreteOneTimeTransactionInputData extends OneTimeTransactionInputData {
        /**
         * Constructs a TransactionInputData object with the specified details.
         *
         * @param id
         * @param transactionAmount      the amount of the transaction
         * @param transactionDescription the description of the transaction
         * @param transactionCategory    the category of the transaction
         * @param transactionDate
         */
        public ConcreteOneTimeTransactionInputData(String id, String transactionAmount, String transactionDescription, String transactionCategory, String transactionDate) {
            super(id, transactionAmount, transactionDescription, transactionCategory, transactionDate);
        }
    }

    private OneTimeTransactionInteractor interactor;
    private UserAccountDataAccessInterface userAccountDataAccessObject;
    private OneTimeTransactionOutputBoundary presenter;
    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        userAccount = new UserAccount("user1", "pass1", "id999");
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
            public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData, UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {

            }


            @Override
            public List<Transaction> readTransactions(String identification) {
                List<Transaction> transactions = new ArrayList<>();
                return transactions;
            }
        };

        presenter = new OneTimeTransactionOutputBoundary() {


            @Override
            public void prepareSuccessView(Object transactions) {

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid date format! Please enter again.", error);
            }
        };

        interactor = new OneTimeTransactionInteractor(userAccountDataAccessObject, presenter, userAccount) {
            @Override
            protected OneTimeTransactionOutputData createOutputData(OneTimeTransaction transaction) {
                return null;
            }
        };
    }
//
//    @Test
//    void testExecuteInflow() {
//        OneTimeTransactionInputData inputData = new ConcreteOneTimeTransactionInputData("id999","100.0", "01-01-2024", "Salary", "Income");
//        interactor.execute(inputData);
//        assertEquals(100.0f, userAccount.getTotalIncome());
//        assertEquals(100.0f, userAccount.getTotalBalance());
//    }
//
//    @Test
//    void testExecuteOutflow() {
//        OneTimeTransactionInputData inputData = new ConcreteOneTimeTransactionInputData("id999","-50.0", "01-01-2024", "Rent", "Expense");
//        interactor.execute(inputData);
//        assertEquals(-50.0f, userAccount.getTotalOutflow());
//        assertEquals(-50.0f, userAccount.getTotalBalance());
//    }

    @Test
    void testExecuteInvalidDate() {
        OneTimeTransactionInputData inputData = new ConcreteOneTimeTransactionInputData("id999","100.0f", "invalid-date", "Salary", "Income");
        interactor.execute(inputData);

    }

    @Test
    void testExecuteInvalidDate1() {
        OneTimeTransactionInputData inputData = new ConcreteOneTimeTransactionInputData("id999","100.0f", "29-02-2023", "Salary", "Income");
        interactor.execute(inputData);
    }
    @Test
    void testExecuteInvalidDate2() {
        OneTimeTransactionInputData inputData = new ConcreteOneTimeTransactionInputData("id999","100.0f", "32-12-2024", "Salary", "Income");
        interactor.execute(inputData);
    }
    @Test
    void testExecuteInvalidDate3() {
        OneTimeTransactionInputData inputData = new ConcreteOneTimeTransactionInputData("id999","100.0f", "00-02-2024", "Salary", "Income");
        interactor.execute(inputData);
    }



}
