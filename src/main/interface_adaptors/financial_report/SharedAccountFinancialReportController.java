package interface_adaptors.financial_report;

import use_case.financial_report.SharedAccountFinancialReportInputBoundary;
import use_case.financial_report.SharedAccountFinancialReportInputData;

public class SharedAccountFinancialReportController extends FinancialReportController<
        SharedAccountFinancialReportInputBoundary,
        SharedAccountFinancialReportViewModel> {

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
