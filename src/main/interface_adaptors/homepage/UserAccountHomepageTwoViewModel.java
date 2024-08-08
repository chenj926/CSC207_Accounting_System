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

    /**
     * Notifies listeners that the signup state has changed.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

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

    /**
     * Gets the current state of the homepage.
     *
     * @return the current state
     */
    public UserAccountHomepageTwoState getState() {
        return this.state;
    }

    // setters
    /**
     * Sets the current state of the homepage and updates the basic user information.
     *
     * @param state the new state
     */
    public void setState(UserAccountHomepageTwoState state) {
        this.state = state;
        this.basicUserInfo = state.getBasicUserInfo();
    }

    /**
     * Resets the state to a new default state.
     */
    public void resetState() {
        UserAccountHomepageTwoState newState = new UserAccountHomepageTwoState();
        setState(newState);
    }

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
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
