package interface_adaptors.financial_report;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FinancialReportViewModel extends ViewModel {
    private final String TITLE_LABEL = "Financial Report";
    private String reportContent;

    private FinancialReportState state = new FinancialReportState();

    public FinancialReportViewModel() {
        super("Financial Report");
        this.reportContent = state.getReportContent();
    }

    // getters
    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }
    public FinancialReportState getState() {
        return this.state;
    }
    public String getReportContent() {
        return this.reportContent;
    }

    // setters
    public void setState(FinancialReportState state) {
        this.state = state;
        //
        this.reportContent = state.getReportContent();
        System.out.println("view model"+this.reportContent);

    }
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public void resetState() {
        FinancialReportState newState = new FinancialReportState();
        setState(newState);
    }

    /**
     * Notifies listeners that the signup state has changed.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies listeners that the signup state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
