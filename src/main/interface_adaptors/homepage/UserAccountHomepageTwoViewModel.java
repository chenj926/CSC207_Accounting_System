package interface_adaptors.homepage;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for the second homepage view. This class is responsible for
 * managing the state and labels of the homepage and notifying listeners of any changes.
 *
 * @author Eric
 */
public class UserAccountHomepageTwoViewModel extends HomepageTwoViewModel<UserAccountHomepageTwoState> {
    // labels
    private final String TITLE_LABEL = "User Account";
    private final String SHAREDIDS_LABEL = "Related Shared Account Ids: ";

    /**
     * Constructs a TransactionViewModel object with the view name set to "Transaction".
     */
    public UserAccountHomepageTwoViewModel() {
        super("Homepage Two");
        this.state = new UserAccountHomepageTwoState();
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTITLE_LABEL() {
        return this.TITLE_LABEL;
    }

    public String getSHAREDIDS_LABEL() {
        return this.SHAREDIDS_LABEL;
    }

    /**
     * Gets the current state of the homepage.
     *
     * @return the current state
     */
    @Override
    public UserAccountHomepageTwoState getState() {
        return this.state;
    }

    // setters
    /**
     * Sets the current state of the homepage and updates the basic user information.
     *
     * @param state the new state
     */
    @Override
    public void setState(UserAccountHomepageTwoState state) {
        this.state = state;
        this.basicUserInfo = state.getBasicUserInfo();
    }

    /**
     * Resets the state to a new default state.
     */
    @Override
    public void resetState() {
        UserAccountHomepageTwoState newState = new UserAccountHomepageTwoState();
        setState(newState);
    }

}
