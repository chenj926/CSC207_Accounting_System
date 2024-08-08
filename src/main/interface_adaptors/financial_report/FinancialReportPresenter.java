package interface_adaptors.financial_report;

import interface_adaptors.ViewManagerModel;
import use_case.financial_report.UserAccountFinancialReportOutputData;

/**
 * Implements the output boundary to present the financial report.
 *
 * @author Dana
 */

public abstract class FinancialReportPresenter {
    private String reportContent;
    private final ViewManagerModel viewManager;


    public FinancialReportPresenter(ViewManagerModel viewManager) {
        this.viewManager = viewManager;
    }


    /**
     * Presents the financial report by storing and printing its content.
     *
     * @param outputData the output data containing the report content
     */
    public void prepareSuccessView(UserAccountFinancialReportOutputData outputData) {
        this.reportContent = outputData.getReportContent();
}
