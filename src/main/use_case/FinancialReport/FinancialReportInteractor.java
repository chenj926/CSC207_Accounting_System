package use_case.FinancialReport;

import entity.account.Account;
import entity.transaction.Transaction;

import java.util.List;

/**
 * Handles the logic for generating financial reports.
 *
 * Creates a financial report based on account transactions, income, outflow, and balance.
 * Formats the report and sends it to the output boundary for presentation.
 *
 * @author :Chi Fong
 */
public class FinancialReportInteractor implements FinancialReportInputBoundary {
    private final FinancialReportOutputBoundary outputBoundary;
    private final Account account;

    /**
     * Constructs a FinancialReportInteractor with the specified account and output boundary.
     *
     * @param account the account for which the financial report is to be generated
     * @param outputBoundary the output boundary to which the generated report will be presented
     */
    public FinancialReportInteractor(Account account, FinancialReportOutputBoundary outputBoundary) {
        this.account = account;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Generates a financial report based on the provided input data and sends it to the output boundary.
     *
     * @param inputData the input data required to generate the financial report
     */
    @Override
    public void execute(FinancialReportInputData inputData) {
        String reportContent = generateReportContent();
        FinancialReportOutputData outputData = new FinancialReportOutputData(reportContent);
        outputBoundary.prepareSuccessView(outputData);
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

        List<Transaction> transactions = account.getTransactions();
        for (Transaction transaction : transactions) {
            report.append(transaction.getDate()).append(" - ")
                    .append(transaction.getTransactionCategory()).append(" : ")
                    .append(transaction.getDescription()).append(" - ")
                    .append(transaction.getAmount()).append("\n");
        }

        return report.toString();
    }
}









