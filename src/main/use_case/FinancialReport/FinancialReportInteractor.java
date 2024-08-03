package use_case.FinancialReport;

import data_access.account.CSVUserAccountDataAccessObject;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.Account;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import use_case.transaction.one_time.OneTimeTransactionOutputBoundary;


import java.util.List;

/**
 * Handles the logic for generating financial reports.
 *<p>
 * Creates a financial report based on account transactions, income, outflow, and balance.
 * Formats the report and sends it to the output boundary for presentation.
 *</p>
 * @see FinancialReportInputBoundary
 * @see FinancialReportOutputBoundary
 * @see UserAccountDataAccessInterface
 * @see UserAccount
 * @see Transaction
 *
 * @author Chi Fong, Eric
 */
public class FinancialReportInteractor implements FinancialReportInputBoundary {
    private final UserAccountDataAccessInterface userDataAccessObject;
    private final FinancialReportOutputBoundary presenter;
    private final UserAccount account;

    /**
     * Constructs a FinancialReportInteractor with the specified account, output boundary, and data access interface.
     *
     * @param account the account for which the financial report is to be generated
     * @param outputBoundary the output boundary to which the generated report will be presented
     * @param userAccountDataAccessInterface the data access interface for retrieving user account data
     */
    public FinancialReportInteractor(UserAccount account,
                                     FinancialReportOutputBoundary outputBoundary,
                                     UserAccountDataAccessInterface userAccountDataAccessInterface) {
        this.account = account;
        this.presenter = outputBoundary;
        this.userDataAccessObject = userAccountDataAccessInterface;
    }

    /**
     * Generates a financial report based on the provided input data and sends it to the output boundary.
     *
     *
     */
    @Override
    public void execute() {
        String reportContent = generateReportContent();
        // if there is at least 1 transaction
        if (!reportContent.equals("No transactions yet!")) {
            FinancialReportOutputData outputData = new FinancialReportOutputData(reportContent);
            this.presenter.prepareSuccessView(outputData);
        }

    }

    /**
     * Generates the content of the financial report as a string.
     *
     * This method collects the account's username, total income, total outflow, total balance,
     * and all transactions to create a comprehensive financial report.
     *
     * @return the content of the financial report
     */
    private String generateReportContent() {
        StringBuilder report = new StringBuilder();
        report.append("Financial Report for Account: ").append(account.getUsername()).append("\n");
        report.append("Total Income: ").append(account.getTotalIncome()).append("\n");
        report.append("Total Outflow: ").append(account.getTotalOutflow()).append("\n");
        report.append("Total Balance: ").append(account.getTotalBalance()).append("\n");
        report.append("Transactions: \n");

        List<Transaction> transactions = userDataAccessObject.readTransactions(account.getIdentification());
        if (transactions.isEmpty()) {
            this.presenter.prepareFailView(report.toString()+"\nNo transactions yet!");
            return "No transactions yet!";
        }

        for (Transaction transaction : transactions) {
            report.append(transaction.getDate()).append(" - ")
                    .append(transaction.getTransactionCategory()).append(" : ")
                    .append(transaction.getDescription()).append(" - ")
                    .append(transaction.getAmount()).append("\n");
        }

        return report.toString();
    }
}









