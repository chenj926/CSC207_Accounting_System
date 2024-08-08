package interface_adaptors.signup;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The UserAccountSignupViewModel class extends the ViewModel class and manages the state and labels for the signup view.
 * It provides getters for various labels used in the signup view and supports property change notifications.
 *
 * @author Rita
 * @author Jessica
 * @author Eric
 */

public class UserAccountSignupViewModel extends AccountSignupViewModel<UserAccountSignupState> {

    private final String TITLE_LABEL = "User Account Sign Up";
    private final String PASSWORD_LABEL = "Set user account password";
    private final String ID_LABEL = "Set user account identification";

    private final String USERNAME_LABEL = "Set username";

    /**
     * Constructs a UserAccountSignupViewModel object with a specified view name.
     */
    public UserAccountSignupViewModel() {
        super("sign up");
        this.state = new UserAccountSignupState();
    }

    /**
     * Gets the username label.
     *
     * @return the username label
     */
    public String getUsernameLabel() { return this.USERNAME_LABEL; }
}