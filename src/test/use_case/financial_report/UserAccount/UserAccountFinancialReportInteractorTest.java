package use_case.financial_report.UserAccount;

import data_access.account.user_account.InMemoryUserAccountDataAccessObject;
import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.financial_report.user_account.UserAccountFinancialReportInputData;
import use_case.financial_report.user_account.UserAccountFinancialReportInteractor;
import use_case.financial_report.user_account.UserAccountFinancialReportOutputBoundary;
import use_case.financial_report.user_account.UserAccountFinancialReportOutputData;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountFinancialReportInteractorTest {
    private UserAccountDataAccessInterface userAccountDataAccessObject;
    private UserAccountFinancialReportInteractor interactor;
    private SimplePresenter simplePresenter;
    private UserAccount userAccount;

    @BeforeEach
    public void setUp() {
        userAccountDataAccessObject = new InMemoryUserAccountDataAccessObject();
        simplePresenter = new SimplePresenter();
        userAccount = new UserAccount("TestUser", "1111", "userAccount1", 0, 0, 0);
        userAccountDataAccessObject.save(userAccount);
        interactor = new UserAccountFinancialReportInteractor(userAccount, simplePresenter, userAccountDataAccessObject);
    }

    @Test
    public void testNoTransactions() {
        String userAccountId = "userAccount1";
        UserAccountFinancialReportInputData inputData = new UserAccountFinancialReportInputData(userAccountId);

        interactor.execute(inputData);
        simplePresenter.getMessage();

        assertEquals("Financial Report for Account: TestUser: userAccount1\n" +
                "\tTotal Income: 0.0\n" +
                "\tTotal Outflow: 0.0\n" +
                "\tTotal Balance: 0.0\n" +
                "Transactions: \n" +
                "--------------------------------------------------------\n" +
                "| Date | Amount | Category | Description |\n" +
                "--------------------------------------------------------\n" +
                "\n" +
                "No transactions yet!", simplePresenter.getMessage());
    }

    @Test
    public void testFinancialReportWithTransactions() {
        String userAccountId = "userAccount1";
        String dateString = "2024-08-20";
        LocalDate date = LocalDate.parse(dateString);
        userAccount.setTotalIncome(1000);
        userAccount.setTotalOutflow(500);
        userAccount.setTotalBalance(500);

        OneTimeTransaction transaction = new OneTimeTransaction("userAccount1",100,date, "Food", "Lunch");
        UserAccountOneTimeTransactionOutputData outputData = new UserAccountOneTimeTransactionOutputData(transaction);
        ((InMemoryUserAccountDataAccessObject) userAccountDataAccessObject).saveTransaction(transaction);

        UserAccountFinancialReportInputData inputData = new UserAccountFinancialReportInputData(userAccountId);
        interactor.execute(inputData);

        String expectedReport = "Financial Report for Account: TestUser: userAccount1\n" +
                "\tTotal Income: 1000.0\n" +
                "\tTotal Outflow: 500.0\n" +
                "\tTotal Balance: 500.0\n" +
                "Transactions: \n" +
                "--------------------------------------------------------\n" +
                "| Date | Amount | Category | Description |\n" +
                "--------------------------------------------------------\n" +
                "2024-08-20 | 100.0 -- Lunch : Food\n";

        assertEquals(expectedReport, simplePresenter.getMessage());
    }

    private static class SimplePresenter implements UserAccountFinancialReportOutputBoundary {
        private String message;
        private UserAccountFinancialReportOutputData data;

        @Override
        public void prepareFailView(String err) {
            this.message = err;
        }

        @Override
        public void prepareSuccessView(UserAccountFinancialReportOutputData data) {
            this.data = data;
            this.message = data.getReportContent();
        }

        public String getMessage() {
            return message;
        }

        public UserAccountFinancialReportOutputData getData() {
            return data;
        }
    }


}
