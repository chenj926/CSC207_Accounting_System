package interface_adaptors.login;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The SharedAccountLoginViewModel class extends LoginViewModel to manage the state and labels
 * for the shared account login view. It adds support for shared account-specific information.
 */
public class SharedAccountLoginViewModel extends ViewModel {

    private final String titleLabel = "SHARED ACCOUNT LOGIN";
    private final String sharedAccountIdLabel = "Enter shared account ID";
    private final String sharedAccountPasswordLabel = "Enter shared account password";


    private final String loginButtonLabel = "Login";
    private final String cancelButtonLabel = "Cancel";

    private SharedAccountLoginState state = new SharedAccountLoginState();

    /**
     * Constructs a SharedAccountLoginViewModel object with the view name set to "shared account login".
     */
    public SharedAccountLoginViewModel() {
        super("shared account log in");
    }

    /**
     * Gets the shared account ID label.
     *
     * @return the shared account ID label
     */
    public String getSharedAccountIdLabel() {
        return this.sharedAccountIdLabel;
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTitleLabel(){
        return this.titleLabel;
    }

    public String getSharedAccountPasswordLabel() {return this.sharedAccountPasswordLabel;}

    /**
     * Gets the current shared account login state.
     *
     * @return the current shared account login state
     */
    public SharedAccountLoginState getState() {
        return this.state;
    }

    /**
     * Sets the current shared account login state.
     *
     * @param state the new shared account login state
     */
    public void setState(SharedAccountLoginState state) {
        this.state = state;
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


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies listeners that the shared account login state has changed.
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
