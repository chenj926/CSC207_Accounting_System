package interface_adaptors.homepage;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

public class HomepageTwoViewModel extends ViewModel {
    // labels
    private final String TITLE_LABEL = "Account";

    private final String USERNAME_LABEL = "User:";
    private final String BALANCE_LABEL = "Total Balance:";
    private final String INCOME_LABEL = "Total Income:";
    private final String OUTFLOW_LABEL = "Total Outflow:";

    private final String ONE_TIME_BUTTON_LABEL = "One Time Transaction";
    private final String PERIODIC_BUTTON_LABEL = "Periodic Transaction";
    private final String CANCEL_BUTTON_LABEL = "Log out";
    private final String FINANCIAL_REPORT_BUTTON_LABEL = "Transaction Report";

    private String[] basicUserInfo;

    private HomepageTwoState state = new HomepageTwoState();
    /**
     * Notifies listeners that the signup state has changed.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a TransactionViewModel object with the view name set to "Transaction".
     */
    public HomepageTwoViewModel() {
        super("Homepage Two");
    }

    public String getUSERNAME_LABEL() {
        return this.USERNAME_LABEL;
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTITLE_LABEL() {
        return this.TITLE_LABEL;
    }

    /**
     * Gets the balance label.
     *
     * @return the balance label
     */
    public String getBALANCE_LABEL() {
        return this.BALANCE_LABEL;
    }

    /**
     * Gets the income label.
     *
     * @return the income label
     */
    public String getINCOME_LABEL() {
        return this.INCOME_LABEL;
    }

    /**
     * Gets the outflow label.
     *
     * @return the outflow label
     */
    public String getOUTFLOW_LABEL() {
        return this.OUTFLOW_LABEL;
    }

    /**
     * Gets the label for the one-time transaction button.
     *
     * @return the label for the one-time transaction button
     */
    public String getONE_TIME_BUTTON_LABEL() {
        return this.ONE_TIME_BUTTON_LABEL;
    }

    /**
     * Gets the label for the periodic transaction button.
     *
     * @return the label for the periodic transaction button
     */
    public String getPERIODIC_BUTTON_LABEL() {
        return this.PERIODIC_BUTTON_LABEL;
    }

    /**
     * Gets the label for the cancel button.
     *
     * @return the label for the cancel button
     */
    public String getCANCEL_BUTTON_LABEL() {
        return this.CANCEL_BUTTON_LABEL;
    }

    /**
     * Gets the label for the history button.
     *
     * @return the label for the history button
     */
    public String getFINANCIAL_REPORT_BUTTON_LABEL() {return this.FINANCIAL_REPORT_BUTTON_LABEL;}

    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }

    public HomepageTwoState getState() {
        return this.state;
    }

    // setters
    public void setState(HomepageTwoState state) {
        this.state = state;
        this.basicUserInfo = state.getBasicUserInfo();
        System.out.println("view model\n"+ Arrays.toString(this.basicUserInfo));
    }

    public void setBasicUserInfo(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }

    public void resetState() {
        HomepageTwoState newState = new HomepageTwoState();
        setState(newState);
    }

    /**
     * Notifies listeners that the signup state has changed.
     */
    public void firePropertyChanged() {
        System.out.println("Firing property change event");
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        System.out.println("Adding PropertyChangeListener");
        support.addPropertyChangeListener(listener);
    }
}
