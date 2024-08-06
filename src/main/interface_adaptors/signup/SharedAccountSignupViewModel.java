package interface_adaptors.signup;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * The SharedAccountSignupViewModel class extends SignupViewModel to add properties and methods specific to shared account signup.
 * It provides additional labels and state management for the shared account signup view.
 */
public class SharedAccountSignupViewModel extends ViewModel {

    private final String TITLE_LABEL = "Shared Account Sign Up";
    private final String SHARED_ACCOUNT_LABEL = "Set shared account id";
    private final String PASSWORD_LABEL = "Set shared account password";
    private final String USER_IDS_LABEL = "Add users";
//    private final String USER2_ID_LABEL = "Add user2 id";

    private final String SIGNUP_BUTTON_LABEL = "Sign up share account";
    private final String CANCEL_BUTTON_LABEL = "Cancel";

    // List to hold additional user labels
    private final List<String> additionalUserLabels;

    private SharedAccountSignupState state = new SharedAccountSignupState();

    /**
     * Constructs a SharedAccountSignupViewModel object with the view name set to "shared account sign up".
     */
    public SharedAccountSignupViewModel() {
        super("shared account sign up");
        this.additionalUserLabels = new ArrayList<>();
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTitleLabel() { return this.TITLE_LABEL; }

    /**
     * Gets the username label.
     *
     * @return the username label
     */
    public String getSHARED_ACCOUNT_LABEL() { return this.SHARED_ACCOUNT_LABEL; }

    /**
     * Gets the password label.
     *
     * @return the password label
     */
    public String getPasswordLabel() { return this.PASSWORD_LABEL; }

    /**
     * Gets the identification label.
     *
     * @return the identification label
     */
    public String getUserIdsLabel() { return this.USER_IDS_LABEL; }

    /**
     * Gets the signup button label.
     *
     * @return the signup button label
     */
    public String getSignupButtonLabel(){ return this.SIGNUP_BUTTON_LABEL; }

    /**
     * Gets the cancel button label.
     *
     * @return the cancel button label
     */
    public String getCancelButtonLabel() { return this.CANCEL_BUTTON_LABEL; }

    /**
     * Gets the current signup state.
     *
     * @return the current signup state
     */
    public SharedAccountSignupState getState() { return this.state; }

    /**
     * Sets the current signup state.
     *
     * @param state the new signup state
     */
    public void setState(SharedAccountSignupState state) {
        this.state = state;
    }

    public void addMoreUserLabel() {
        int nextUserNumber = this.getState().getUserId().size();
        String newLabel = "Add user" + nextUserNumber + " ID";
        additionalUserLabels.add(newLabel);
    }

    /**
     * Removes the last additional user label if there are any.
     */
    public void removeLastUserLabel() {
        if (!additionalUserLabels.isEmpty()) {
            additionalUserLabels.remove(additionalUserLabels.size() - 1);
        }
    }

    /**
     * Gets the list of additional user labels.
     *
     * @return the list of additional user labels
     */
    public List<String> getAdditionalUserLabels() {
        return new ArrayList<>(additionalUserLabels);
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
