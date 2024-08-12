package interface_adaptors.financial_report.shared_account;

import interface_adaptors.financial_report.FinancialReportController;
import use_case.financial_report.shared_account.SharedAccountFinancialReportInputBoundary;
import use_case.financial_report.shared_account.SharedAccountFinancialReportInputData;

public class SharedAccountFinancialReportController extends FinancialReportController<
        SharedAccountFinancialReportInputBoundary,
        SharedAccountFinancialReportViewModel,
        SharedAccountFinancialReportInputData,
        SharedAccountFinancialReportState> {

    public SharedAccountFinancialReportController(SharedAccountFinancialReportInputBoundary
                                                          sharedAccountFinancialReportInteractor,
                                                  SharedAccountFinancialReportViewModel viewModel) {
        super(sharedAccountFinancialReportInteractor, viewModel);
    }

    public void execute(String identification){
        SharedAccountFinancialReportInputData sharedAccountFinancialReportInputData = new SharedAccountFinancialReportInputData(
                identification
        );
        financialReportInteractor.execute(sharedAccountFinancialReportInputData);
        this.viewModel.resetState();
    }

}
