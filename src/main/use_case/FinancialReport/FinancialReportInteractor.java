package use_case.FinancialReport;

import entity.account.Account;
import entity.transaction.Transaction;

import java.util.List;

/**
 * The FinancialReportInteractor class handles the logic for generating financial reports.
 * It uses the input and output boundaries to perform the required actions.
 *
 * Author: Chi Fong Huang
 */
public class FinancialReportInteractor implements FinancialReportInputBoundary {
    private final FinancialReportOutputBoundary outputBoundary;
    private final Account account;

    public FinancialReportInteractor(Account account, FinancialReportOutputBoundary outputBoundary) {
        this.account = account;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void generateReport(FinancialReportInputData inputData) {
        String reportContent = generateReportContent();
        FinancialReportOutputData outputData = new FinancialReportOutputData(reportContent);
        outputBoundary.presentReport(outputData);
    }

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
                    .append(transaction.getDescription()).append(" - ")
                    .append(transaction.getAmount()).append("\n");
        }

        return report.toString();
    }
}








