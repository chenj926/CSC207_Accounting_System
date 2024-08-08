package use_case.financial_report;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import entity.transaction.Transaction;


import java.util.List;

/**
 * Handles the logic for generating financial reports.
 *<p>
 * Creates a financial report based on account transactions, income, outflow, and balance.
 * Formats the report and sends it to the output boundary for presentation.
 *</p>
 * @see UserAccountFinancialReportInputBoundary
 * @see UserAccountFinancialReportOutputBoundary
 * @see UserAccountDataAccessInterface
 * @see UserAccount
 * @see Transaction
 *
 * @author Chi Fong, Eric
 */
public class UserAccountFinancialReportInteractor implements UserAccountFinancialReportInputBoundary {
    private final UserAccountDataAccessInterface userDataAccessObject;
    private final UserAccountFinancialReportOutputBoundary presenter;
    private UserAccount account;

    /**
     * Constructs a FinancialReportInteractor with the specified account, output boundary, and data access interface.
     *
     * @param account the account for which the financial report is to be generated
     * @param outputBoundary the output boundary to which the generated report will be presented
     * @param userAccountDataAccessInterface the data access interface for retrieving user account data
     */
    public UserAccountFinancialReportInteractor(UserAccount account,
                                                UserAccountFinancialReportOutputBoundary outputBoundary,
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
    public void execute(UserAccountFinancialReportInputData inputData) {
        String reportContent = generateReportContent(inputData);
        // if there is at least 1 transaction
        if (!reportContent.equals("No transactions yet!")) {
            UserAccountFinancialReportOutputData outputData = new UserAccountFinancialReportOutputData(reportContent);
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
    private String generateReportContent(UserAccountFinancialReportInputData inputData) {
        String id = inputData.getIdentification();
        //debug
        this.account = userDataAccessObject.getById(id);
        String username = this.account.getUsername();
        String totalIncome = String.valueOf(this.account.getTotalIncome());
        String totalOutflow = String.valueOf(this.account.getTotalOutflow());
        String totalBalance = String.valueOf(this.account.getTotalBalance());

        StringBuilder report = new StringBuilder();
        report.append("Financial Report for Account: ").append(username + ": " + id).append("\n");
        report.append("\tTotal Income: ").append(totalIncome).append("\n");
        report.append("\tTotal Outflow: ").append(totalOutflow).append("\n");
        report.append("\tTotal Balance: ").append(totalBalance).append("\n");
        report.append("Transactions: \n");
        report.append("--------------------------------------------------------\n");
        report.append("| Date | Amount | Category | Description |\n");
        report.append("--------------------------------------------------------\n");

        List<Transaction> transactions = this.userDataAccessObject.readTransactions(id);
        if (transactions.isEmpty()) {
            this.presenter.prepareFailView(report.toString()+"\nNo transactions yet!");
            return "No transactions yet!";
        }

        for (Transaction transaction : transactions) {
            report.append(transaction.getDate()).append(" | ")
                    .append(transaction.getAmount()).append(" -- ")
                    .append(transaction.getTransactionCategory()).append(" : ")
                    .append(transaction.getDescription()).append("\n");
        }

        return report.toString();
    }
}









