package use_case;

import data_access.account.CSVUserAccountDataAccessObject;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import entity.transaction.periodic.PeriodicTransaction;
import interface_adaptors.financial_report.FinancialReportPresenter;
import interface_adaptors.financial_report.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.financial_report.FinancialReportInputData;
import use_case.financial_report.FinancialReportInteractor;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the FinancialReportInteractor class.
 * Author: Chi Fong Huang
 */
public class FinancialReportInteractorTest {

    private UserAccount account;
    private FinancialReportPresenter presenter;
    private FinancialReportInteractor interactor;
    private CSVUserAccountDataAccessObject userAccountDataAccessObject = new CSVUserAccountDataAccessObject();
    private FinancialReportViewModel viewModel = new FinancialReportViewModel();
    private ViewManagerModel viewManagerModel = new ViewManagerModel();

    @BeforeEach
    public void setUp() {
        account = userAccountDataAccessObject.getById("id007");
        presenter = new FinancialReportPresenter(viewModel, viewManagerModel);
        userAccountDataAccessObject = new CSVUserAccountDataAccessObject();
        interactor = new FinancialReportInteractor(account, presenter, userAccountDataAccessObject);

        // test for one time
        Transaction t1 = new OneTimeTransaction("t1", 100.0f, LocalDate.of(2024, 7, 1), "Salary", "Income") {};
        Transaction t2 = new OneTimeTransaction("t2", -50.0f, LocalDate.of(2024, 7, 2), "Groceries", "Expense") {};
        account.addTransaction(t1);
        account.addTransaction(t2);

        // test Periodic transactions
        Transaction p1 = new PeriodicTransaction("p1", 500.0f, LocalDate.of(2023, 7, 1), "Rent", LocalDate.of(2024, 7, 1), 30, "food") {};
        account.addTransaction(p1);
    }

    @Test
    public void testexecute() {
        FinancialReportInputData inputData = new FinancialReportInputData("","id0", new Date(), new Date());
        interactor.execute();

        String reportContent = presenter.getReportContent();

        // Assert that the report contains the data for One-Time transactions
        assertTrue(reportContent.contains("Jessica"));
        assertTrue(reportContent.contains("Total Income: 600.0"));
        assertTrue(reportContent.contains("Total Outflow: -50.0"));
        assertTrue(reportContent.contains("Total Balance: 550.0"));
//        assertTrue(reportContent.contains("Salary"));
//        assertTrue(reportContent.contains("Groceries"));
//        assertTrue(reportContent.contains("food"));
//        assertTrue(reportContent.contains("Income"));

        // Assert that the report contains the data for Periodic transactions
        assertTrue(reportContent.contains("personal"));
        assertTrue(reportContent.contains("888.88"));
    }
}

