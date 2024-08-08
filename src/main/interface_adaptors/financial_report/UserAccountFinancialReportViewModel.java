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
public class UserAccountFinancialReportViewModel extends FinancialReportViewModel<UserAccountFinancialReportState> {

    private UserAccountFinancialReportState state = new UserAccountFinancialReportState();

    /**
     * Constructs a FinancialReportViewModel object with the view name set to "Financial Report".
     */
    public UserAccountFinancialReportViewModel() {
        super("Financial Report");
    }

    /**
     * Gets the current state of the financial report.
     *
     * @return the current state
     */
    public UserAccountFinancialReportState getState() {
        return this.state;
    }

    // setters
    /**
     * Sets the current state of the financial report and updates the report content.
     *
     * @param state the new state
     */
    public void setState(UserAccountFinancialReportState state) {
        this.state = state;
    }

    /**
     * Resets the state to a new default state.
     */
    public void resetState() {
        UserAccountFinancialReportState newState = new UserAccountFinancialReportState();
        setState(newState);
    }
}
