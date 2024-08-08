package interface_adaptors.financial_report;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class FinancialReportViewModel<S extends FinancialReportState> extends ViewModel {
    protected S state;
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    protected final String TITLE_LABEL = "Financial Report";
    protected String reportContent;

    public FinancialReportViewModel(String viewName) {
        super(viewName);
    }

    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }

    public String getReportContent() {
        return this.reportContent;
    }

    public abstract S getState();

    public abstract void setState(S state);

    public abstract void resetState();

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }
    public void firePropertyChange(){
        support.firePropertyChange("state", null, this.state);
    }

}
