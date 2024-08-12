package interface_adaptors.financial_report.shared_account;

import interface_adaptors.financial_report.FinancialReportViewModel;

public class SharedAccountFinancialReportViewModel extends FinancialReportViewModel<SharedAccountFinancialReportState> {

    public SharedAccountFinancialReportViewModel() {
        super("Shared Account Financial Report");
        this.reportContent = "";
        this.state = new SharedAccountFinancialReportState();
    }

    @Override
    public SharedAccountFinancialReportState getState(){
        return this.state;
    }

    @Override
    public void setState(SharedAccountFinancialReportState state){
        this.state = state;
    }

    @Override
    public void resetState() {
        setState(new SharedAccountFinancialReportState());
    }
}

