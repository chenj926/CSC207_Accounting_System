package interface_adaptors.financial_report;

import use_case.financial_report.FinancialReportInputBoundary;
import use_case.financial_report.FinancialReportInputData;

public abstract class FinancialReportController<
        IB extends FinancialReportInputBoundary<I>,
        V extends FinancialReportViewModel<S>,
        I extends FinancialReportInputData,
        S extends FinancialReportState> {

    protected final IB financialReportInteractor;
    protected final V viewModel;

    public FinancialReportController (IB financialReportInteractor, V viewModel){
        this.financialReportInteractor = financialReportInteractor;
        this.viewModel = viewModel;
    }
}
