package interface_adaptors.login;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * The LoginViewModel class extends the ViewModel class and manages the state and labels for the login view.
 * It provides getters for various labels used in the login view and supports property change notifications.
 *
 * @author Rita
 * @author Eric
 * @author Dana
 */
public class LoginViewModel extends ViewModel {

    private final String titleLabel = "LOGIN";
    private final String identificationLabel = "Enter identification";
    private final String passwordLabel = "Enter password";

    private final String loginButtonLabel = "Login";
    private final String cancelButtonLabel = "Cancel";

    private LoginState state = new LoginState();

    /**
     * Constructs a LoginViewModel object with the view name set to "login".
     */
    public LoginViewModel() {
        super("login");
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTitleLabel(){
        return this.titleLabel;
    }

    /**
     * Gets the identification label.
     *
     * @return the identification label
     */
    public String getIdentificationLabel(){
        return this.identificationLabel;
    }

    /**
     * Gets the password label.
     *
     * @return the password label
     */
    public String getPasswordLabel(){
        return this.passwordLabel;
    }

    /**
     * Gets the login button label.
     *
     * @return the login button label
     */
    public String getLoginButtonLabel(){
        return this.loginButtonLabel;
    }

    /**
     * Gets the cancel button label.
     *
     * @return the cancel button label
     */
    public String getCancelButtonLabel(){
        return this.cancelButtonLabel;
    }

    /**
     * Gets the current login state.
     *
     * @return the current login state
     */
    public LoginState getState() {
        return this.state;
    }

    /**
     * Sets the current login state.
     *
     * @param state the new login state
     */
    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies listeners that the login state has changed.
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