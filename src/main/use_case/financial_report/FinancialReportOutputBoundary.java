package use_case.financial_report;

/**
 * Output boundary interface for presenting financial reports.
 *
 * @author :Dana
 */

public interface FinancialReportOutputBoundary<T> {

    void prepareSuccessView(T reportOutputData);

    void prepareFailView(String error);
}
