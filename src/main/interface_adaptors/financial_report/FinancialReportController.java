package interface_adaptors.financial_report;

import use_case.financial_report.FinancialReportInputBoundary;

public abstract class FinancialReportController<
        I extends FinancialReportInputBoundary,
        V extends FinancialReportViewModel> {

    protected final I financialReportInteractor;
    protected final V viewModel;

    public FinancialReportController (I financialReportInteractor, V viewModel){
        this.financialReportInteractor = financialReportInteractor;
        this.viewModel = viewModel;
    }
}
