package interface_adaptors.financial_report;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for the financial report view. This class is responsible for managing
 * the state and report content of the financial report and notifying listeners of any changes.
 *
 * @author Eric CHen
 */
public class UserAccountFinancialReportViewModel extends ViewModel {
    /**
     * The title label for the financial report view.
     */
    private final String TITLE_LABEL = "Financial Report";
    private String reportContent;

    private UserAccountFinancialReportState state = new UserAccountFinancialReportState();

    /**
     * Constructs a FinancialReportViewModel object with the view name set to "Financial Report".
     */
    public UserAccountFinancialReportViewModel() {
        super("Financial Report");
        this.reportContent = state.getReportContent();
    }

    // getters
    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }
    /**
     * Gets the current state of the financial report.
     *
     * @return the current state
     */
    public UserAccountFinancialReportState getState() {
        return this.state;
    }
    /**
     * Gets the current state of the financial report.
     *
     * @return the current state
     */
    public String getReportContent() {
        return this.reportContent;
    }

    // setters
    /**
     * Sets the current state of the financial report and updates the report content.
     *
     * @param state the new state
     */
    public void setState(UserAccountFinancialReportState state) {
        this.state = state;
        this.reportContent = state.getReportContent();
    }

    /**
     * Sets the report content.
     *
     * @param reportContent the report content to set
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    /**
     * Resets the state to a new default state.
     */
    public void resetState() {
        UserAccountFinancialReportState newState = new UserAccountFinancialReportState();
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
