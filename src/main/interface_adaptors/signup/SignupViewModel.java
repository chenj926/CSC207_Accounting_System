package interface_adaptors.signup;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class SignupViewModel<S extends SignupState> extends ViewModel {
    protected final String TITLE_LABEL = "Shared Account Sign Up";
    protected final String PASSWORD_LABEL = "Set shared account password";
    protected final String ID_LABEL = "Set shared account identification";

    protected final String SIGNUP_BUTTON_LABEL = "Sign up shared account";
    protected final String CANCEL_BUTTON_LABEL = "Cancel";

    protected S state;

    /**
     * Constructs a ViewModel object with the specified view name.
     *
     * @param viewName the name of the view
     */
    public SignupViewModel(String viewName) {
        super(viewName);
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
    public String getID_LABEL() { return this.ID_LABEL; }

    /**
     * Gets the password label.
     *
     * @return the password label
     */
    public String getPasswordLabel() { return this.PASSWORD_LABEL; }

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
    public S getState() { return this.state; }

    /**
     * Sets the current signup state.
     *
     * @param state the new signup state
     */
    public void setState(S state) {
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
