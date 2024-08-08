package interface_adaptors.homepage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SharedAccountHomepageTwoViewModel extends HomepageTwoViewModel<SharedAccountHomepageTwoState>{
    private final String TITLE_LABEL = "Shared Account";

    /**
     * Constructs a TransactionViewModel object with the view name set to "Transaction".
     */
    public SharedAccountHomepageTwoViewModel() {
        super("Shared Account Homepage Two");
        this.state = new SharedAccountHomepageTwoState();
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
    @Override
    public SharedAccountHomepageTwoState getState() {
        return this.state;
    }

    // setters
    /**
     * Sets the current state of the homepage and updates the basic user information.
     *
     * @param state the new state
     */
    @Override
    public void setState(SharedAccountHomepageTwoState state) {
        this.state = state;
        this.basicUserInfo = state.getBasicUserInfo();
    }

    /**
     * Resets the state to a new default state.
     */
    @Override
    public void resetState() {
        SharedAccountHomepageTwoState newState = new SharedAccountHomepageTwoState();
        setState(newState);
    }

}
