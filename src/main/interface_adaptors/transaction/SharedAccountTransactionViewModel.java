package interface_adaptors.transaction;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeSupport;

/**
 * The SharedAccountTransactionViewModel class extends the ViewModel class and manages the state and labels for shared account transaction-related views.
 * It provides getters for various labels used in the shared account transaction view and supports property change notifications.
 *
 */
public class SharedAccountTransactionViewModel extends ViewModel {
    // Labels
    private final String TITLE_LABEL = "Shared Account";
    private final String BALANCE_LABEL = "Total Balance";
    private final String INCOME_LABEL = "Total Income";
    private final String OUTFLOW_LABEL = "Total Outflow";

    private final String ONE_TIME_BUTTON_LABEL = "One Time Transaction";
    private final String PERIODIC_BUTTON_LABEL = "Periodic Transaction";
    private final String ADD_USER_BUTTON_LABEL = "Add User to Shared Account"; // New label for adding a user
    private final String CANCEL_BUTTON_LABEL = "Log out";
    private final String FINANCIAL_REPORT_BUTTON_LABEL = "Transaction Report";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a SharedAccountTransactionViewModel object with the view name set to "Shared Account Transaction".
     */
    public SharedAccountTransactionViewModel() {
        super("Shared Account Transaction");
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
     * Gets the label for the add user button.
     *
     * @return the label for the add user button
     */
    public String getADD_USER_BUTTON_LABEL() {
        return this.ADD_USER_BUTTON_LABEL;
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
     * Gets the label for the financial report button.
     *
     * @return the label for the financial report button
     */
    public String getFINANCIAL_REPORT_BUTTON_LABEL() {
        return this.FINANCIAL_REPORT_BUTTON_LABEL;
    }
}

