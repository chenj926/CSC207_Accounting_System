package interface_adaptors.FinancialReport;

import interface_adaptors.ViewModel;
import interface_adaptors.signup.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FinancialReportViewModel extends ViewModel {
    private final String TITLE_LABEL = "Financial Report";

    private FinancialReportState state = new FinancialReportState();

    public FinancialReportViewModel() {
        super("Financial Report");
    }

    // getters
    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }
    public FinancialReportState getState() {
        return this.state; }

    // setters
    public void setState(FinancialReportState state) {
        this.state = state;
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
