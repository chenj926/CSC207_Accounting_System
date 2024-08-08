package use_case.financial_report;

import data_access.account.AccountDataAccessInterface;
import entity.account.Account;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

public abstract class FinancialReportInteractor<
        DAO extends AccountDataAccessInterface<A, O, P>,
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P extends PeriodicTransactionOutputData,
        I extends FinancialReportInputData,
        PRE extends FinancialReportOutputBoundary<FO>,
        FO extends FinancialReportOutputData> {

    protected final DAO userDataAccessObject;
    protected final PRE presenter;
    protected A account;

    public FinancialReportInteractor(A account,
                                     PRE presenter,
                                     DAO userDataAccessObject) {
        this.account = account;
        this.presenter = presenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(I inputData) {
        String reportContent = generateReportContent(inputData);
        // if there is at least 1 transaction
        if (!reportContent.equals("No transactions yet!")) {
            FO outputData = this.createOutputData(reportContent);
            this.presenter.prepareSuccessView(outputData);
        }
    }

    protected abstract String generateReportContent(I inputData);

    protected abstract FO createOutputData(String reportContent);
}
