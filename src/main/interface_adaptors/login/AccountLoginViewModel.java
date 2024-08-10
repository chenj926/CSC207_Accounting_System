package interface_adaptors.login;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AccountLoginViewModel<S extends AccountLoginState> extends ViewModel {
    protected final String titleLabel = "ACCOUNT LOGIN";
    protected final String identificationLabel = "Enter account ID";
    protected final String passwordLabel = "Enter account password";

    protected final String loginButtonLabel = "Login";
    protected final String cancelButtonLabel = "Cancel";

    protected S state;

    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a LoginViewModel object with the view name set to "login".
     */
    public AccountLoginViewModel(String viewModelName) {
        super(viewModelName);
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
    public S getState() {
        return this.state;
    }

    /**
     * Sets the current login state.
     *
     * @param state the new login state
     */
    public void setState(S state) {
        this.state = state;
    }

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
