package use_case.financial_report;

public interface FinancialReportInputBoundary<T> {
    void execute(T inputData);
}
