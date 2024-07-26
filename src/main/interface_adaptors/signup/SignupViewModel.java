package interface_adaptors.signup;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The SignupViewModel class extends the ViewModel class and manages the state and labels for the signup view.
 * It provides getters for various labels used in the signup view and supports property change notifications.
 *
 * @author Rita
 * @author Jessica
 * @author Eric
 */

public class SignupViewModel extends ViewModel {

    private final String TITLE_LABEL = "Sign Up";
    private final String USERNAME_LABEL = "Set username";
    private final String PASSWORD_LABEL = "Set password";
    private final String ID_LABEL = "Set identification";

    private final String SIGNUP_BUTTON_LABEL = "Sign up";
    private final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupState state = new SignupState();

    /**
     * Constructs a SignupViewModel object with the view name set to "sign up".
     */
    public SignupViewModel() {
        super("sign up");
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
    public String getUsernameLabel() { return this.USERNAME_LABEL; }

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
    public String getID_LABEL() { return this.ID_LABEL; }

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
    public SignupState getState() { return state; }

    /**
     * Sets the current signup state.
     *
             * @param state the new signup state
     */
    public void setState(SignupState state) {
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