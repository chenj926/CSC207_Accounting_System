package interface_adaptors.financial_report;

import interface_adaptors.ViewManagerModel;
import use_case.financial_report.UserAccountFinancialReportOutputData;


/**
 * Implements the output boundary to present the financial report.
 *
 * @author Dana
 */

public abstract class FinancialReportPresenter<
        V extends FinancialReportViewModel,
        S extends FinancialReportState> {
    protected final V viewModel;

    protected String reportContent;

    public FinancialReportPresenter(V viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
    }


    /**
     * Presents the financial report by storing and printing its content.
     *
     * @param outputData the output data containing the report content
     */
    public void prepareSuccessView(FinancialReportOutputData outputData) {
        this.reportContent = outputData.getReportContent();
        V state = (V) viewModel.getState();


}
