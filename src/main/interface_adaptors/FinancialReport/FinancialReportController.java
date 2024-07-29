package interface_adaptors.FinancialReport;

import use_case.FinancialReport.FinancialReportInputBoundary;
import use_case.FinancialReport.FinancialReportInputData;

import java.util.Date;

public class FinancialReportController {
    private final FinancialReportInputBoundary interactor;

    public FinancialReportController(FinancialReportInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void generateReport(String accountId, Date startDate, Date endDate) {
        FinancialReportInputData inputData = new FinancialReportInputData(accountId, startDate, endDate);
        interactor.generateReport(inputData);
    }
}

