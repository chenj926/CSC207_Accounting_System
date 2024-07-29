package use_case.FinancialReport;

public class FinancialReportInteractor {
    private final FinancialReportInputBoundary inputBoundary;
    private final FinancialReportOutputBoundary outputBoundary;

    public FinancialReportInteractor(FinancialReportInputBoundary inputBoundary, FinancialReportOutputBoundary outputBoundary) {
        this.inputBoundary = inputBoundary;
        this.outputBoundary = outputBoundary;
    }

    public void generateReport(FinancialReportInputData inputData) {
        // Logic to generate the financial report

        FinancialReportOutputData outputData = new FinancialReportOutputData(/* parameters */);
        outputBoundary.presentReport(outputData);
    }
}

