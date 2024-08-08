package interface_adaptors.financial_report;

public class SharedAccountFinancialReportViewModel extends FinancialReportViewModel<SharedAccountFinancialReportState> {

    public SharedAccountFinancialReportViewModel() {
        super("Shared Account Financial Report");
        this.reportContent = "";
        this.state = new SharedAccountFinancialReportState();
    }


    public SharedAccountFinancialReportState getState(){
        return this.state;
    }

    public void setState(SharedAccountFinancialReportState state){
        this.state = state;
    }

    @Override
    public void resetState() {
        setState(new SharedAccountFinancialReportState());
    }
}

