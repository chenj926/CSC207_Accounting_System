package use_case.financial_report.shared_account;

import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import entity.transaction.Transaction;
import use_case.financial_report.FinancialReportInteractor;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

import java.util.List;
import java.util.Set;

public class SharedAccountFinancialReportInteractor extends FinancialReportInteractor<
        SharedAccountDataAccessInterface,
        SharedAccount,
        SharedAccountOneTimeTransactionOutputData,
        SharedAccountPeriodicTransactionOutputData,
        SharedAccountFinancialReportInputData,
        SharedAccountFinancialReportOutputBoundary,
        SharedAccountFinancialReportOutputData
        > implements SharedAccountFinancialReportInputBoundary{

    public SharedAccountFinancialReportInteractor(SharedAccount account,
                                                  SharedAccountFinancialReportOutputBoundary presenter,
                                                  SharedAccountDataAccessInterface userDataAccessObject) {
        super(account, presenter, userDataAccessObject);
    }

    @Override
    protected String generateReportContent(SharedAccountFinancialReportInputData inputData) {
        String id = inputData.getIdentification();
        //debug
        this.account = userDataAccessObject.getById(id);
        String shareId = this.account.getIdentification();
        Set<String> userIds = this.account.getSharedUserIdentifications();
        String totalIncome = String.valueOf(this.account.getTotalIncome());
        String totalOutflow = String.valueOf(this.account.getTotalOutflow());
        String totalBalance = String.valueOf(this.account.getTotalBalance());

        StringBuilder report = new StringBuilder();
        report.append("Financial Report for Account: ").append(shareId + ": \n" + userIds.toString()).append("\n");
        report.append("\tTotal Income: ").append(totalIncome).append("\n");
        report.append("\tTotal Outflow: ").append(totalOutflow).append("\n");
        report.append("\tTotal Balance: ").append(totalBalance).append("\n");
        report.append("Transactions: \n");
        report.append("------------------------------------------------------------------\n");
        report.append("| User(id) | Date | Amount | Category | Description |\n");
        report.append("------------------------------------------------------------------\n");

        List<Transaction> transactions = this.userDataAccessObject.readTransactions(id);
        if (transactions.isEmpty()) {
            this.presenter.prepareFailView(report.toString()+"\nNo transactions yet!");
            return "No transactions yet!";
        }

        for (Transaction transaction : transactions) {
            String[] transactionIds = transaction.getIdentification().split(";");
            report.append(transactionIds[1]).append(" | ")
                    .append(transaction.getDate()).append(" | ")
                    .append(transaction.getAmount()).append(" -- ")
                    .append(transaction.getTransactionCategory()).append(" : ")
                    .append(transaction.getDescription()).append("\n");
        }

        return report.toString();
    }

    @Override
    protected SharedAccountFinancialReportOutputData createOutputData(String reportContent) {
        return new SharedAccountFinancialReportOutputData(reportContent);
    }
}
